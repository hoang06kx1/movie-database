package nguyen.hoang.movierating.AccountManagement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSignUp extends Fragment implements View.OnClickListener{
    @Bind(R.id.tv_sign_in) TextView mTvSignIn;
    @Bind(R.id.btn_sign_up) Button mBtSignUp;
    @Bind(R.id.edt_email) EditText mEdtEmail;
    @Bind(R.id.edt_password) EditText mEdtPassword;
    @Bind(R.id.tv_wrong_validation) TextView mTvWrongValidation;
    @Bind(R.id.btn_cancel) Button mBtnCancel;

    public static String TAG = "FragmentSignUp";
    public FragmentSignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, v);
        mTvSignIn.setOnClickListener(this);
        mBtSignUp.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view == mTvSignIn) {
            FragmentSignIn fragmentSignIn = new FragmentSignIn();
            Utils.replaceFragmentInAccountManagement(getActivity(), fragmentSignIn, fragmentSignIn.TAG, false);
        } else if (view == mBtSignUp) {
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
            }
        } else if (view == mBtnCancel) {
            getActivity().onBackPressed();
        }
    }
}
