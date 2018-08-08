package kronikle.project.Landing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.Objects;

import kronikle.project.Main.MainActivity;
import kronikle.project.R;

public class SignInFragment extends Fragment {

    private View view;
    private ConstraintLayout constraintLayout;
    private TextInputLayout textInputLayoutEmail;
    private TextInputEditText editTextEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText editTextPassword;
    private Button buttonSignIn;
    private Button buttonForgotPassword;
    private SignInForgotPasswordDialog signInForgotPasswordDialog ;
    private Button buttonContinueGuest;
    private SignInContinueGuestDialog signInContinueGuestDialog;
    private FloatingActionButton buttonFacebook;
    private FloatingActionButton buttonGoogle;

    private String email;
    private String password;

    public SignInFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sign_in_fragment, container, false);

        initializer();
        layoutFocus();
        buttonSignInListener();
        buttonForgotPasswordListener();
        buttonContinueGuestListener();

        return view;
    }

    private void initializer() {
        constraintLayout = view.findViewById(R.id.constraint_layout_SIF);
        textInputLayoutEmail = view.findViewById(R.id.text_input_layout_email_SIF);
        editTextEmail = view.findViewById(R.id.edit_text_email_SIF);
        textInputLayoutPassword = view.findViewById(R.id.text_input_layout_password_SIF);
        editTextPassword = view.findViewById(R.id.edit_text_password_SIF);
        buttonSignIn = view.findViewById(R.id.button_sign_in_SIF);
        buttonForgotPassword = view.findViewById(R.id.button_forgot_password_SIF);
        buttonContinueGuest = view.findViewById(R.id.button_continue_guest_SIF);
        buttonFacebook = view.findViewById(R.id.button_facebook_SIF);
        buttonGoogle = view.findViewById(R.id.button_google_SIF);
    }

    // Hides Keyboard when switching fragments when open
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            try {
                InputMethodManager inputMethod = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethod != null;
                inputMethod.hideSoftInputFromWindow(Objects.requireNonNull(getView()).getWindowToken(), 0);
                inputMethod.hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(), 0);
            } catch (Exception e) {
                Log.e("Keyboard fragment", "Changing fragment might cause crash");
            }
        }
    }

    // Hides Keyboard when user clicks outside EditText
    @SuppressLint("ClickableViewAccessibility")
    private void layoutFocus() {
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethod = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethod != null;
                Objects.requireNonNull(inputMethod).hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(), 0);
                editTextEmail.clearFocus();
                editTextPassword.clearFocus();
                return true;
            }
        });
    }

    // Modify when having a working database
    private void buttonSignInListener() {
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail()) {
                    focusEditText(editTextEmail);
                    editTextEmail.startAnimation(shakeAnimation());
                }

                else if (!validatePassword()) {
                    focusEditText(editTextPassword);
                    editTextPassword.startAnimation(shakeAnimation());
                }

                else {
                    //Code to check if the given email is in the database
                    new StyleableToast
                            .Builder(Objects.requireNonNull(getContext()))
                            .text(getString(R.string.welcome_back))
                            .textColor(getResources().getColor(R.color.colorTextLight))
                            .backgroundColor(getResources().getColor(R.color.colorBackground))
                            .iconStart(R.drawable.icon_user_accepted)
                            .cornerRadius(2)
                            .length(6000)
                            .show();

                    Intent MainActivityIntent = new Intent(getActivity(), MainActivity.class);
                    startActivity(MainActivityIntent);
                    Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.enter_in_up, R.anim.exit_out_up);
                    getActivity().finish();
                }
            }
        });
    }

    private boolean validateEmail() {
        email = editTextEmail.getText().toString().trim();

        return !(email.isEmpty() || !(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()));
    }

    private boolean validatePassword() {
        password = editTextPassword.getText().toString().trim();

        return !(password.isEmpty() || password.length() < 8);
    }

    private void focusEditText(View view) {
        view.requestFocus();
        InputMethodManager inputMethod = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
        assert inputMethod != null;
        Objects.requireNonNull(inputMethod).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    private void buttonForgotPasswordListener() {
        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonForgotPassword.setClickable(false);
                signInForgotPasswordDialog = new SignInForgotPasswordDialog();
                signInForgotPasswordDialog.show(getChildFragmentManager(), getString(R.string.forgot_password));
                signInForgotPasswordDialog.setCancelable(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buttonForgotPassword.setClickable(true);
                    }
                }, 500);
            }
        });
    }

    private void buttonContinueGuestListener() {
        buttonContinueGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonContinueGuest.setClickable(false);

                signInContinueGuestDialog = new SignInContinueGuestDialog();
                signInContinueGuestDialog.show(getChildFragmentManager(), getString(R.string.continue_guest));
                signInContinueGuestDialog.setCancelable(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buttonContinueGuest.setClickable(true);
                    }
                }, 500);
            }
        });
    }

    private TranslateAnimation shakeAnimation() {
        TranslateAnimation shake = new TranslateAnimation(0, 15, 0, 0);
        shake.setInterpolator(new CycleInterpolator(4));
        shake.setDuration(500);
        return shake;
    }
}
