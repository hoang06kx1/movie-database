package nguyen.hoang.movierating;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import nguyen.hoang.movierating.AccountManagement.FragmentSignUp;
import nguyen.hoang.movierating.AccountManagement.LaunchActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Administrator on 11/26/2015.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class FragmentSignUpTest {
    LaunchActivity mLaunchActivity;
    FragmentSignUp mFragmentSignUp;
    TextView mTvWrongValidation;
    TextView mEdtEmail, mEdtPassword;
    View mBtnSignUp;

    @Before
    public void loadSignUpFragment() {
        mLaunchActivity = Robolectric.setupActivity(LaunchActivity.class);
        mLaunchActivity.findViewById(R.id.btn_sign_up).performClick();
        mFragmentSignUp = (FragmentSignUp) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignUp.TAG);
        assertNotNull(mFragmentSignUp);
        assertTrue(mFragmentSignUp.isVisible());
        mTvWrongValidation = (TextView) mFragmentSignUp.getView().findViewById(R.id.tv_wrong_validation);
        assertTrue(mTvWrongValidation.getVisibility() == View.GONE);
        mEdtEmail = (EditText) mFragmentSignUp.getView().findViewById(R.id.edt_email);
        mEdtPassword = (EditText) mFragmentSignUp.getView().findViewById(R.id.edt_password);
        mBtnSignUp = mFragmentSignUp.getView().findViewById(R.id.btn_sign_up);
    }

    @Test
    public void inputWrongFormatPassword_shouldShowValidationText() {
        mEdtPassword.setText("$T_t");
        mEdtEmail.setText("hoang06kx1@gmail.com");
        mBtnSignUp.performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mLaunchActivity.getResources().getString(R.string.password_invalid), mTvWrongValidation.getText().toString());
    }

    @Test
    public void inputWrongFormatEmail_shouldShowValidationText() {
        mEdtEmail.setText("th@thkdf");
        mEdtPassword.setText("Goin@123");
        mBtnSignUp.performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mLaunchActivity.getResources().getString(R.string.email_invalid), mTvWrongValidation.getText().toString());
    }

    @Test
    public void inputCorrectEmailPassword_shouldNotShowValidationText() {
        inputWrongFormatEmail_shouldShowValidationText();
        mEdtEmail.setText("hoang06kx1@gmail.com");
        mEdtPassword.setText("Goin@123");
        mBtnSignUp.performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.GONE);
    }

    @Test
    public void inputMissingEmail_shouldShowError() {
        mEdtPassword.setText("Goin@123");
        mEdtEmail.setText("    ");
        mBtnSignUp.performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mTvWrongValidation.getText().toString(), mLaunchActivity.getResources().getString(R.string.missing_email_password));
        inputCorrectEmailPassword_shouldNotShowValidationText();
    }
}
