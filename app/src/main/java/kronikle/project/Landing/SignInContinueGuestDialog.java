package kronikle.project.Landing;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import kronikle.project.R;

public class SignInContinueGuestDialog extends AppCompatDialogFragment {

    private View view;
    private CardView cardView;
    private ImageView imageViewClose;
    private TextView textViewTitle;
    private TextView textViewMessage;
    private Button buttonContinue;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        view = getActivity().getLayoutInflater().inflate(R.layout.sign_in_continue_guest_dialog, null);
        builder.setView(view);

        initializer();
        imageViewCloseListener();
        buttonContinueListener();

        return builder.create();
    }

    // Dialog animation
    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Objects.requireNonNull(getDialog().getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    private void initializer() {
        cardView = view.findViewById(R.id.card_view_SICGD);
        imageViewClose = view.findViewById(R.id.image_view_close_SICGD);
        textViewTitle = view.findViewById(R.id.text_view_title_SICGD);
        textViewMessage = view.findViewById(R.id.text_view_message_SICGD);
        buttonContinue = view.findViewById(R.id.button_continue_SICGD);
    }

    private void imageViewCloseListener() {
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    private void buttonContinueListener() {
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code to check if the given email is in the database
                textViewTitle.setText(R.string.welcome);
                textViewMessage.setText(R.string.welcome_dialogue);
                imageViewClose.setVisibility(View.GONE);
                buttonContinue.setVisibility(View.GONE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (getDialog() != null && getDialog().isShowing()) {
                            getDialog().dismiss();
                        }
                    }
                }, 6000);            }
        });
    }
}
