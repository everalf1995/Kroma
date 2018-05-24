package kronikle.project.Landing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import java.util.Objects;

import kronikle.project.MainActivity;
import kronikle.project.R;

public class SignInFragment extends Fragment {

    View view;

    Button buttonSignIn;

    public SignInFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sign_in_fragment, container, false);

        buttonSignIn = view.findViewById(R.id.button_sign_in_SIF);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivityIntent = new Intent(getContext(), MainActivity.class);
                startActivity(MainActivityIntent);
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.enter_in_up, R.anim.exit_out_up);
                getActivity().finish();
            }
        });





        return view;
    }
}