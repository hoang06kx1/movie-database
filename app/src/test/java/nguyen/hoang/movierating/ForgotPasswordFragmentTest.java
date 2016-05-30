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

import nguyen.hoang.movierating.ui.authentication.fragment.ForgotPasswordFragment;
import nguyen.hoang.movierating.ui.authentication.fragment.SignInFragment;
import nguyen.hoang.movierating.ui.authentication.activity.LaunchActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


/**
 * Created by Administrator on 11/26/2015.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = TestParseApplication.class)
public class ForgotPasswordFragmentTest {

    LaunchActivity mLaunchActivity;
    ForgotPasswordFragment mForgotPasswordFragment;
    TextView mTvWrongValidation;
    EditText mEdtEmail;
    EditText mEdtPassword;
    View mBtnSubmit;

    @Before
    public void loadForgotPasswordFragment() {
        mLaunchActivity = Robolectric.setupActivity(LaunchActivity.class);
        mLaunchActivity.findViewById(R.id.btn_sign_in).performClick();
        mLaunchActivity.getSupportFragmentManager().findFragmentByTag(SignInFragment.TAG).getView().findViewById(R.id.tv_forget_password).performClick();
        mForgotPasswordFragment = (ForgotPasswordFragment) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(ForgotPasswordFragment.TAG);
        assertNotNull(mForgotPasswordFragment);
        assertTrue(mForgotPasswordFragment.isVisible());
        mTvWrongValidation = (TextView) mForgotPasswordFragment.getView().findViewById(R.id.tv_wrong_validation);
        assertTrue(mTvWrongValidation.getVisibility() == View.GONE);
        mEdtEmail = (EditText) mForgotPasswordFragment.getView().findViewById(R.id.edt_email);
        mEdtPassword = (EditText) mForgotPasswordFragment.getView().findViewById(R.id.edt_password);
        mBtnSubmit = mForgotPasswordFragment.getView().findViewById(R.id.btn_submit);
    }

    @Test
    public void inputWrongFormatEmail_shouldShowValidationText() {
        mEdtEmail.setText("th@thkdf");
        mBtnSubmit.performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mLaunchActivity.getResources().getString(R.string.email_invalid), mTvWrongValidation.getText().toString());
    }

    @Test
    public void inputCorrectEmail_shouldNotShowValidationText() {
        mEdtEmail.setText("hoang06kx1@gmail.com");
        mBtnSubmit.performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.GONE);
    }

    @Test
    public void inputMissingEmail_shouldShowError() {
        mEdtEmail.setText("    ");
        mBtnSubmit.performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mTvWrongValidation.getText().toString(), mLaunchActivity.getResources().getString(R.string.missing_email));
    }

    @Test
    public void clickButtonCancel_shouldBackToLoginScreen() {
        mForgotPasswordFragment.getView().findViewById(R.id.btn_cancel).performClick();
        assertTrue(mLaunchActivity.getSupportFragmentManager().findFragmentByTag(SignInFragment.TAG).isVisible());
    }
}
