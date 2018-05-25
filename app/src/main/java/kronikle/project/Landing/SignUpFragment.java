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

public class SignUpFragment extends Fragment {

    View view;
    TextInputLayout textInputLayoutFirstName;
    TextInputEditText editTextFirstName;
    TextInputLayout textInputLayoutLastName;
    TextInputEditText editTextLastName;
    TextInputLayout textInputLayoutEmail;
    TextInputEditText editTextEmail;
    TextInputLayout textInputLayoutPassword;
    TextInputEditText editTextPassword;
    TextInputLayout textInputLayoutConfirmPassword;
    TextInputEditText editTextConfirmPassword;
    Button buttonSignUp;

    public SignUpFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sign_up_fragment, container, false);

        initializer();
        textChangeListener();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInformation();
            }
        });

        return view;
    }

    private void initializer() {
        textInputLayoutFirstName = view.findViewById(R.id.text_input_layout_first_name_SUF);
        editTextFirstName = view.findViewById(R.id.edit_text_first_name_SUF);
        textInputLayoutLastName = view.findViewById(R.id.text_input_layout_last_name_SUF);
        editTextLastName = view.findViewById(R.id.edit_text_last_name_SUF);
        textInputLayoutEmail = view.findViewById(R.id.text_input_layout_email_SUF);
        editTextEmail = view.findViewById(R.id.edit_text_email_SUF);
        textInputLayoutPassword = view.findViewById(R.id.text_input_layout_password_SUF);
        editTextPassword = view.findViewById(R.id.edit_text_password_SUF);
        textInputLayoutConfirmPassword = view.findViewById(R.id.text_input_layout_confirm_password_SUF);
        editTextConfirmPassword = view.findViewById(R.id.edit_text_confirm_password_SUF);
        buttonSignUp = view.findViewById(R.id.button_sign_up_SUF);
    }

    public void textChangeListener() {
        editTextFirstName.addTextChangedListener(new SignUpFragment.textWatcher(editTextFirstName));
        editTextLastName.addTextChangedListener(new SignUpFragment.textWatcher(editTextLastName));
        editTextEmail.addTextChangedListener(new SignUpFragment.textWatcher(editTextEmail));
        editTextPassword.addTextChangedListener(new SignUpFragment.textWatcher(editTextPassword));
        editTextConfirmPassword.addTextChangedListener(new SignUpFragment.textWatcher(editTextConfirmPassword));
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
                case R.id.edit_text_first_name_SUF:
                    validateFirstName();
                    break;
                case R.id.edit_text_last_name_SUF:
                    validateLastName();
                    break;
                case R.id.edit_text_email_SUF:
                    validateEmail();
                    break;
                case R.id.edit_text_password_SUF:
                    validatePassword();
                    break;
                case R.id.edit_text_confirm_password_SUF:
                    validateConfirmPassword();
                    break;
            }
        }
    }

    private boolean validateFirstName() {
        String firstName = editTextFirstName.getText().toString().trim();

        if (firstName.isEmpty() || !firstName.matches("^[ A-Za-z'-]+$")) {
            textInputLayoutFirstName.setError(getString(R.string.error_first_name_suf));
            return false;
        }

        else {
            textInputLayoutFirstName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateLastName() {
        String lastName = editTextLastName.getText().toString().trim();

        if (lastName.isEmpty() || !lastName.matches("^[ A-Za-z'-]+$")) {
            textInputLayoutLastName.setError(getString(R.string.error_last_name_suf));
            return false;
        }

        else {
            textInputLayoutLastName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty() || !(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            textInputLayoutEmail.setError(getString(R.string.error_email_suf));
            return false;
        }

        else {
            textInputLayoutEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = editTextPassword.getText().toString();

        if (password.isEmpty() || password.length() < 8) {
            textInputLayoutPassword.setError(getString(R.string.error_password_suf));
            return false;
        }

        else {
            textInputLayoutPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if (!confirmPassword.equals(password)) {
            textInputLayoutConfirmPassword.setError(getString(R.string.error_confirm_password_suf));
            return false;
        }

        else {
            textInputLayoutConfirmPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void validateInformation() {

        if (!validateFirstName()) {
            focusEditText(editTextFirstName);
        }

        else if (!validateLastName()) {
            focusEditText(editTextLastName);
        }

        else if (!validateEmail()) {
            focusEditText(editTextEmail);
        }

        else if (!validatePassword()) {
            focusEditText(editTextPassword);
        }

        else if (!validateConfirmPassword()) {
            focusEditText(editTextConfirmPassword);
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
