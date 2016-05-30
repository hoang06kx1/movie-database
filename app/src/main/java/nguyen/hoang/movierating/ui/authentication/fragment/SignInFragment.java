package nguyen.hoang.movierating.ui.authentication.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import nguyen.hoang.movierating.ui.main.activity.MainActivity;
import nguyen.hoang.movierating.ParseApplication;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.util.ParseWrapper;
import nguyen.hoang.movierating.util.Utils;

/**
 * A simple {@link Fragment} subclass.
 */

public class SignInFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    public static String TAG = "FragmentSignIn";
    @Bind(R.id.tv_forget_password) TextView mTvForgotPassword;
    @Bind(R.id.tv_sign_up) TextView mTvSignUp;
    @Bind(R.id.btn_sign_in) Button mBtSignIn;
    @Bind(R.id.edt_email) EditText mEdtEmail;
    @Bind(R.id.edt_password) EditText mEdtPassword;
    @Bind(R.id.tv_wrong_validation) TextView mTvWrongValidation;
    @Bind(R.id.btn_cancel) Button mBtnCancel;
    @Inject ParseWrapper mParseWrapper;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity) {
            mParseWrapper = ((ParseApplication)((FragmentActivity) context).getApplication()).getParseWrapperComponent().inject();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, v);
        mTvForgotPassword.setOnClickListener(this);
        mTvSignUp.setOnClickListener(this);
        mBtSignIn.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View view) {
        if (view == mTvForgotPassword) {
            ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
            getFragmentManager().beginTransaction().replace(R.id.frament_container, forgotPasswordFragment, ForgotPasswordFragment.TAG).addToBackStack(ForgotPasswordFragment.TAG).commit();
        } else if (view == mTvSignUp) {
            SignUpFragment signUpFragment = new SignUpFragment();
            getFragmentManager().beginTransaction().replace(R.id.frament_container, signUpFragment, signUpFragment.TAG).commit();
        } else if (view == mBtSignIn) {
            if (mEdtEmail.getText().toString().trim().equals("") || mEdtPassword.getText().toString().trim().equals("")) {
                mTvWrongValidation.setText(getString(R.string.missing_email_password));
                mTvWrongValidation.setVisibility(View.VISIBLE);
            } else if (!Utils.validateEmail(mEdtEmail.getText().toString())) {
                mTvWrongValidation.setText(R.string.email_invalid);
                mTvWrongValidation.setVisibility(View.VISIBLE);
            } else if (!Utils.validatePassword(mEdtPassword.getText().toString())) {
                mTvWrongValidation.setText(R.string.password_invalid);
                mTvWrongValidation.setVisibility(View.VISIBLE);
            } else {
                mTvWrongValidation.setVisibility(View.GONE);
                processSignIn(mEdtEmail.getText().toString(), mEdtPassword.getText().toString());
            }
        } else if (view == mBtnCancel) {
            getActivity().onBackPressed();
        }
    }

    private void processSignIn(String email, String password) {
        mParseWrapper.logInInBackground(email, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    signInSuccessfully();
                } else {
                    Utils.showErrorMessage(getActivity(), e);
                }
            }
        });
    }

    private void signInSuccessfully() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}
