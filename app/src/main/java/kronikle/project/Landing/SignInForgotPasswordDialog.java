package kronikle.project.Landing;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import kronikle.project.R;

public class SignInForgotPasswordDialog extends AppCompatDialogFragment {

    private View view;
    private CardView cardView;
    private ImageView imageViewClose;
    private TextView textViewTitle;
    private TextView textViewMessage;
    private TextInputLayout textInputLayoutEmail;
    private TextInputEditText editTextEmail;
    private Button buttonSubmit;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        view = getActivity().getLayoutInflater().inflate(R.layout.sign_in_forgot_password_dialog, null);
        builder.setView(view);

        initializer();
        cardViewFocus();
        imageViewCloseListener();
        buttonSubmitListener();
        ediTextChangeListener();

        return builder.create();
    }

    // Dialog animation
    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Objects.requireNonNull(getDialog().getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    private void initializer() {
        cardView = view.findViewById(R.id.card_view_SIFPD);
        imageViewClose = view.findViewById(R.id.image_view_close_SIFPD);
        textViewTitle = view.findViewById(R.id.text_view_title_SIFPD);
        textViewMessage = view.findViewById(R.id.text_view_message_SIFPD);
        textInputLayoutEmail = view.findViewById(R.id.text_input_layout_email_SIFPD);
        editTextEmail = view.findViewById(R.id.edit_text_email_SIFPD);
        buttonSubmit = view.findViewById(R.id.button_submit_SIFPD);
    }

    // Hides Keyboard when user clicks outside EditText
    @SuppressLint("ClickableViewAccessibility")
    private void cardViewFocus() {
        cardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethod = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethod != null;
                Objects.requireNonNull(inputMethod).hideSoftInputFromWindow(Objects.requireNonNull(getDialog().getCurrentFocus()).getWindowToken(), 0);
                editTextEmail.clearFocus();
                return true;
            }
        });
    }

    private void imageViewCloseListener() {
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    private void buttonSubmitListener() {
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInformation();
            }
        });
    }

    public void ediTextChangeListener() {
        editTextEmail.addTextChangedListener(new textWatcher(editTextEmail));
    }

    private class textWatcher implements TextWatcher {

        private View view;

        private textWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.edit_text_email_SIFPD:
                    validateEmail();
                    break;
            }
        }
    }

    private boolean validateEmail() {
        String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty() || !(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            textInputLayoutEmail.setError(getString(R.string.error_email_sif));
            return false;
        }

        else {
            textInputLayoutEmail.setErrorEnabled(false);
            return true;
        }
    }

    // Modify when having a working database
    public void validateInformation() {

        if (!validateEmail()) {
            focusEditText(editTextEmail);
        }

        else {
            //Code to check if the given email is in the database
            textViewTitle.setText(R.string.email_submitted);
            textViewMessage.setText(R.string.email_submitted_dialog);
            imageViewClose.setVisibility(View.GONE);
            textInputLayoutEmail.setVisibility(View.GONE);
            editTextEmail.setVisibility(View.GONE);
            buttonSubmit.setVisibility(View.GONE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getDialog().dismiss();
                }
            }, 5000) ;

        }
    }

    private void focusEditText(View view) {
        view.requestFocus();
        InputMethodManager inputMethod = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
        assert inputMethod != null;
        Objects.requireNonNull(inputMethod).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
}
