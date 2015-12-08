package nguyen.hoang.movierating.AccountManagement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import nguyen.hoang.movierating.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSignIn extends android.support.v4.app.Fragment implements View.OnClickListener {
    @Bind(R.id.tv_forget_password) TextView mTvForgotPassword;
    @Bind(R.id.tv_sign_up) TextView mTvSignUp;
    public static String TAG = "FragmentSignIn";
    public FragmentSignIn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, v);
        mTvForgotPassword.setOnClickListener(this);
        mTvSignUp.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View view) {
        if (view == mTvForgotPassword) {
            FragmentForgotPassword fragmentForgotPassword = new FragmentForgotPassword();
            getFragmentManager().beginTransaction().replace(R.id.frament_container, fragmentForgotPassword, FragmentForgotPassword.TAG).addToBackStack(FragmentForgotPassword.TAG).commit();
        } else if (view == mTvSignUp) {
            FragmentSignUp fragmentSignUp = new FragmentSignUp();
            getFragmentManager().beginTransaction().replace(R.id.frament_container, fragmentSignUp, fragmentSignUp.TAG).commit();
        }
    }
}
