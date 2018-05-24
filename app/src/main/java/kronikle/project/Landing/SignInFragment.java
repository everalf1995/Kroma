package kronikle.project.Landing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import java.util.Objects;

import kronikle.project.R;

public class SignInFragment extends Fragment {

    View view;

    TextInputLayout textInputLayoutEmail;
    TextInputEditText editTextEmail;
    TextInputLayout textInputLayoutPassword;
    TextInputEditText editTextPassword;
    Button buttonSignIn;

    public SignInFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sign_in_fragment, container, false);

        initializer();

        editTextEmail.addTextChangedListener(new textWatcher(editTextEmail));
        editTextPassword.addTextChangedListener(new textWatcher(editTextPassword));

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInformation();
            }
        });

        return view;
    }

    public void initializer() {

        textInputLayoutEmail = view.findViewById(R.id.text_input_layout_email_SIF);
        editTextEmail = view.findViewById(R.id.edit_text_email_SIF);
        textInputLayoutPassword = view.findViewById(R.id.text_input_layout_password_SIF);
        editTextPassword = view.findViewById(R.id.edit_text_password_SIF);
        buttonSignIn = view.findViewById(R.id.button_sign_in_SIF);
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

    public void validateInformation() {

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        Toast.makeText(getContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }


    private boolean validateEmail() {
        String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            textInputLayoutEmail.setError(getString(R.string.error_email));
            requestFocus(editTextEmail);
            return false;
        } else {
            textInputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (editTextPassword.getText().toString().trim().isEmpty()) {
            textInputLayoutPassword.setError(getString(R.string.error_password));
            requestFocus(editTextPassword);
            return false;
        } else {
            textInputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
