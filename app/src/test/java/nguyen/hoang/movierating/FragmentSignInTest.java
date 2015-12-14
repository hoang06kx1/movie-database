package nguyen.hoang.movierating;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import nguyen.hoang.movierating.AccountManagement.FragmentForgotPassword;
import nguyen.hoang.movierating.AccountManagement.FragmentLaunch;
import nguyen.hoang.movierating.AccountManagement.FragmentSignIn;
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
public class FragmentSignInTest {
    LaunchActivity mLaunchActivity;
    FragmentSignIn mFragmentSignIn;
    TextView mTvWrongValidation;
    @Before
    public void loadSignInFragment() {
        mLaunchActivity = Robolectric.setupActivity(LaunchActivity.class);
        mLaunchActivity.findViewById(R.id.btn_sign_in).performClick();
        mFragmentSignIn = (FragmentSignIn) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignIn.TAG);
        assertNotNull(mFragmentSignIn);
        assertTrue(mFragmentSignIn.isVisible());
        mTvWrongValidation = (TextView) mFragmentSignIn.getView().findViewById(R.id.tv_wrong_validation);
        assertTrue(mTvWrongValidation.getVisibility() == View.GONE);
    }

    @Test
    public void clickForgotPassword_shouldStartForgotPasswordFragment() {
        mFragmentSignIn.getView().findViewById(R.id.tv_forget_password).performClick();
        FragmentForgotPassword fragmentForgotPassword = (FragmentForgotPassword) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentForgotPassword.TAG);
        assertNotNull(fragmentForgotPassword);
        assertTrue("Fragment Forgot Password should be shown", fragmentForgotPassword.isVisible());
        mLaunchActivity.onBackPressed();
        mFragmentSignIn = (FragmentSignIn) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignIn.TAG);
        assertTrue("Should show fragment SignIn when press back button on forget password screen", mFragmentSignIn.isVisible());
    }

    @Test
    public void inputWrongFormatPassword_shouldShowValidationText() {
        EditText edtPassword = (EditText) mFragmentSignIn.getView().findViewById(R.id.edt_password);
        EditText edtEmail = (EditText) mFragmentSignIn.getView().findViewById(R.id.edt_email);
        edtPassword.setText("$T_t");
        edtEmail.setText("hoang06kx1@gmail.com");
        mFragmentSignIn.getView().findViewById(R.id.btn_sign_in).performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mLaunchActivity.getResources().getString(R.string.password_invalid), mTvWrongValidation.getText().toString());
    }

    @Test
    public void inputWrongFormatEmail_shouldShowValidationText() {
        EditText edtPassword = (EditText) mFragmentSignIn.getView().findViewById(R.id.edt_password);
        EditText edtEmail = (EditText) mFragmentSignIn.getView().findViewById(R.id.edt_email);
        edtEmail.setText("th@thkdf");
        edtPassword.setText("Goin@123");
        mFragmentSignIn.getView().findViewById(R.id.btn_sign_in).performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mLaunchActivity.getResources().getString(R.string.email_invalid), mTvWrongValidation.getText().toString());
    }

    @Test
    public void inputCorrectEmailPassword_shouldNotShowValidationText() {
        inputWrongFormatEmail_shouldShowValidationText();
        EditText edtPassword = (EditText) mFragmentSignIn.getView().findViewById(R.id.edt_password);
        EditText edtEmail = (EditText) mFragmentSignIn.getView().findViewById(R.id.edt_email);
        edtEmail.setText("hoang06kx1@gmail.com");
        edtPassword.setText("Goin@123");
        mFragmentSignIn.getView().findViewById(R.id.btn_sign_in).performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.GONE);
        // assertEquals(mLaunchActivity.getResources().getString(R.string.password_invalid), mTvWrongValidation.getText().toString());
    }

    @Test
    public void inputMissingEmail_shouldShowError() {
        EditText edtPassword = (EditText) mFragmentSignIn.getView().findViewById(R.id.edt_password);
        EditText edtEmail = (EditText) mFragmentSignIn.getView().findViewById(R.id.edt_email);
        edtPassword.setText("Goin@123");
        edtEmail.setText("    ");
        mFragmentSignIn.getView().findViewById(R.id.btn_sign_in).performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mTvWrongValidation.getText().toString(), mLaunchActivity.getResources().getString(R.string.missing_email_password));
        inputCorrectEmailPassword_shouldNotShowValidationText();
    }
            
}
