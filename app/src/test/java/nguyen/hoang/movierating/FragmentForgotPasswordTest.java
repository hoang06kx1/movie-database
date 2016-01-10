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

import nguyen.hoang.movierating.AccountManagement.FragmentForgotPassword;
import nguyen.hoang.movierating.AccountManagement.FragmentSignIn;
import nguyen.hoang.movierating.AccountManagement.LaunchActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


/**
 * Created by Administrator on 11/26/2015.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = TestParseApplication.class)
public class FragmentForgotPasswordTest {

    LaunchActivity mLaunchActivity;
    FragmentForgotPassword mFragmentForgotPassword;
    TextView mTvWrongValidation;

    @Before
    public void loadForgotPasswordFragment() {
        mLaunchActivity = Robolectric.setupActivity(LaunchActivity.class);
        mLaunchActivity.findViewById(R.id.btn_sign_in).performClick();
        mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignIn.TAG).getView().findViewById(R.id.tv_forget_password).performClick();
        mFragmentForgotPassword = (FragmentForgotPassword) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentForgotPassword.TAG);
        assertNotNull(mFragmentForgotPassword);
        assertTrue(mFragmentForgotPassword.isVisible());
        mTvWrongValidation = (TextView) mFragmentForgotPassword.getView().findViewById(R.id.tv_wrong_validation);
        assertTrue(mTvWrongValidation.getVisibility() == View.GONE);
    }

    @Test
    public void inputWrongFormatEmail_shouldShowValidationText() {
        EditText edtEmail = (EditText) mFragmentForgotPassword.getView().findViewById(R.id.edt_email);
        edtEmail.setText("th@thkdf");
        mFragmentForgotPassword.getView().findViewById(R.id.btn_submit).performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mLaunchActivity.getResources().getString(R.string.email_invalid), mTvWrongValidation.getText().toString());
    }

    @Test
    public void inputCorrectEmail_shouldNotShowValidationText() {

        /*
        EditText edtEmail = (EditText) mFragmentForgotPassword.getView().findViewById(R.id.edt_email);
        edtEmail.setText("hoang06kx1@gmail.com");
        mFragmentForgotPassword.getView().findViewById(R.id.btn_submit).performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.GONE);
        */
        // assertEquals(mLaunchActivity.getResources().getString(R.string.password_invalid), mTvWrongValidation.getText().toString());
    }

    @Test
    public void inputMissingEmail_shouldShowError() {
        EditText edtEmail = (EditText) mFragmentForgotPassword.getView().findViewById(R.id.edt_email);
        edtEmail.setText("    ");
        mFragmentForgotPassword.getView().findViewById(R.id.btn_submit).performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mTvWrongValidation.getText().toString(), mLaunchActivity.getResources().getString(R.string.missing_email));
        // inputCorrectEmail_shouldNotShowValidationText();
    }

    @Test
    public void clickButtonCancel_shouldBackToLoginScreen() {
        mFragmentForgotPassword.getView().findViewById(R.id.btn_cancel).performClick();
        assertTrue(mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignIn.TAG).isVisible());
    }


}
