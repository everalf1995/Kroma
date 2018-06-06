package kronikle.project.Landing;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;

import java.util.Objects;

import kronikle.project.R;

public class SignInForgotPasswordDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        View view = getActivity().getLayoutInflater().inflate(R.layout.sign_in_forgot_password_dialog, null);
        builder.setView(view);

        return  builder.create();
    }
}
