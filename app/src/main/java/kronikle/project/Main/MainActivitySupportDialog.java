package kronikle.project.Main;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.Objects;

import kronikle.project.R;

public class MainActivitySupportDialog extends DialogFragment {

    private View view;
    private LinearLayout linearLayout;
    private ImageView imageViewClose;
    private Button buttonWatchAd;
    private Button buttonDonate;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        view = getActivity().getLayoutInflater().inflate(R.layout.main_activity_support_dialog, null);
        builder.setView(view);

        initializer();
        imageViewCloseListener();
        buttonWatchAdListener();
        buttonDonateListener();

        return builder.create();
    }

    // Dialog animation
    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Objects.requireNonNull(getDialog().getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    private void initializer() {
        linearLayout = view.findViewById(R.id.linear_layout_SIFPD);
        imageViewClose = view.findViewById(R.id.image_view_close_MASD);
        buttonWatchAd = view.findViewById(R.id.button_watch_add_MASD);
        buttonDonate = view.findViewById(R.id.button_donate_MASD);
    }

    private void imageViewCloseListener() {
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    private void buttonWatchAdListener() {
        buttonWatchAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDialog().dismiss();

                new StyleableToast
                        .Builder(Objects.requireNonNull(getActivity()))
                        .text(getString(R.string.watch_add_thanks))
                        .textColor(getResources().getColor(R.color.colorTextLight))
                        .backgroundColor(getResources().getColor(R.color.colorBackground))
                        .iconStart(R.drawable.icon_thanks)
                        .cornerRadius(2)
                        .length(6000)
                        .show();
            }
        });
    }

    private void buttonDonateListener() {
        buttonDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDialog().dismiss();

                new StyleableToast
                        .Builder(Objects.requireNonNull(getActivity()))
                        .text(getString(R.string.donate_thanks))
                        .textColor(getResources().getColor(R.color.colorTextLight))
                        .backgroundColor(getResources().getColor(R.color.colorBackground))
                        .iconStart(R.drawable.icon_thanks)
                        .cornerRadius(2)
                        .length(6000)
                        .show();
            }
        });
    }
}
