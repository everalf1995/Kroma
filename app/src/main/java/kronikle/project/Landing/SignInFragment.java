package kronikle.project.Landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

import kronikle.project.MainActivity;
import kronikle.project.R;

public class SignInFragment extends Fragment {

    View view;

    TextInputLayout textInputLayoutEmail;
    TextInputEditText editTextEmail;
    TextInputLayout textInputLayoutPassword;
    TextInputEditText editTextPassword;
    Button buttonSignIn;
    Button forgotPassword;
    Button continueGuest;
    Button facebook;
    Button google;

    public SignInFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sign_in_fragment, container, false);

        initializer();
        textChangeListener();

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInformation();
            }
        });

        return view;
    }

    private void initializer() {
        textInputLayoutEmail = view.findViewById(R.id.text_input_layout_email_SIF);
        editTextEmail = view.findViewById(R.id.edit_text_email_SIF);
        textInputLayoutPassword = view.findViewById(R.id.text_input_layout_password_SIF);
        editTextPassword = view.findViewById(R.id.edit_text_password_SIF);
        buttonSignIn = view.findViewById(R.id.button_sign_in_SIF);
        forgotPassword = view.findViewById(R.id.button_forgot_password_SIF);
        continueGuest = view.findViewById(R.id.button_continue_guest_SIF);
        facebook = view.findViewById(R.id.button_facebook_SIF);
        google = view.findViewById(R.id.button_google_SIF);
    }

    public void textChangeListener() {
        editTextEmail.addTextChangedListener(new textWatcher(editTextEmail));
        editTextPassword.addTextChangedListener(new textWatcher(editTextPassword));
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

                case R.id.edit_text_email_SIF:
                    validateEmail();
                    break;
                case R.id.edit_text_password_SIF:
                    validatePassword();
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

    private boolean validatePassword() {
        if (editTextPassword.getText().toString().trim().isEmpty() || editTextPassword.getText().toString().trim().length() < 8) {
            textInputLayoutPassword.setError(getString(R.string.error_password_sif));
            return false;
        }

        else {
            textInputLayoutPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void validateInformation() {

        if (!validateEmail()) {
            focusEditText(editTextEmail);
        }

        else if (!validatePassword()) {
            focusEditText(editTextPassword);
        }

        else {
            //Code to check if the given email is in the database
            Toast.makeText(getContext(), "Welcome to KORNIKLE!", Toast.LENGTH_SHORT).show();

            Intent MainActivityIntent = new Intent(getActivity(), MainActivity.class);
            startActivity(MainActivityIntent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.enter_in_up, R.anim.exit_out_up);
            getActivity().finish();
        }
    }

    private void focusEditText(View view) {
        view.requestFocus();
        InputMethodManager inputMethod = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
        assert inputMethod != null;
        Objects.requireNonNull(inputMethod).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
}
