package nguyen.hoang.movierating.AccountManagement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.Utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentForgotPassword extends Fragment implements View.OnClickListener {

    public static String TAG = "FragmentForgotPassword";
    @Bind(R.id.edt_email) EditText mEdtEmail;
    @Bind(R.id.btn_submit) Button mBtSubmit;
    @Bind(R.id.tv_wrong_validation) TextView mTvWrongValidation;
    @Bind(R.id.btn_cancel) Button mBtnCancel;

    public FragmentForgotPassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_forgot_pass, container, false);
        ButterKnife.bind(this, v);
        mBtSubmit.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View view) {
        if (view == mBtSubmit) {
            if (mEdtEmail.getText().toString().trim().equals("")) {
                mTvWrongValidation.setText(getString(R.string.missing_email));
                mTvWrongValidation.setVisibility(View.VISIBLE);
            } else if (!Utils.validateEmail(mEdtEmail.getText().toString())){
                mTvWrongValidation.setText(getString(R.string.email_invalid));
                mTvWrongValidation.setVisibility(View.VISIBLE);
            } else {
                mTvWrongValidation.setVisibility(View.GONE);
                resetPassword(mEdtEmail.getText().toString());
            }
        } else if (view == mBtnCancel) {
            getFragmentManager().popBackStack();
        }
    }

    private void resetPassword(String email) {
        ParseUser parseUser = new ParseUser();
        parseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getActivity(), getString(R.string.reset_password_email_sent), Toast.LENGTH_LONG).show();
                } else {
                    Utils.showErrorMessage(getActivity(), e);
                }
            }
        });
    }
}
