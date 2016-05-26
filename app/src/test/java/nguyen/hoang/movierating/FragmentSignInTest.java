package nguyen.hoang.movierating;

import android.app.RobolectricActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowToast;

import nguyen.hoang.movierating.AccountManagement.FragmentForgotPassword;
import nguyen.hoang.movierating.AccountManagement.FragmentLaunch;
import nguyen.hoang.movierating.AccountManagement.FragmentSignIn;
import nguyen.hoang.movierating.AccountManagement.FragmentSignUp;
import nguyen.hoang.movierating.AccountManagement.LaunchActivity;
import nguyen.hoang.movierating.MovieRating.MainActivity;
import nguyen.hoang.movierating.Utils.ParseWrapper;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Administrator on 11/26/2015.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = TestParseApplication.class)
public class FragmentSignInTest {
    LaunchActivity mLaunchActivity;
    FragmentSignIn mFragmentSignIn;
    TextView mTvWrongValidation;
    EditText mEdtPassword;
    EditText mEdtEmail;
    ParseWrapper mockParseWrapper;

    @Before
    public void loadSignInFragment() {
        mLaunchActivity = Robolectric.setupActivity(LaunchActivity.class);
        mLaunchActivity.findViewById(R.id.btn_sign_in).performClick();
        mFragmentSignIn = (FragmentSignIn) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignIn.TAG);
        assertNotNull(mFragmentSignIn);
        assertTrue(mFragmentSignIn.isVisible());
        mTvWrongValidation = (TextView) mFragmentSignIn.getView().findViewById(R.id.tv_wrong_validation);
        assertTrue(mTvWrongValidation.getVisibility() == View.GONE);
        mEdtPassword = (EditText) mFragmentSignIn.getView().findViewById(R.id.edt_password);
        mEdtEmail = (EditText) mFragmentSignIn.getView().findViewById(R.id.edt_email);
        MockitoAnnotations.initMocks(this);
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
        mEdtPassword.setText("$T_t");
        mEdtEmail.setText("hoang06kx1@gmail.com");
        mFragmentSignIn.getView().findViewById(R.id.btn_sign_in).performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mLaunchActivity.getResources().getString(R.string.password_invalid), mTvWrongValidation.getText().toString());
    }

    @Test
    public void inputWrongFormatEmail_shouldShowValidationText() {
        mEdtEmail.setText("th@thkdf");
        mEdtPassword.setText("Goin@123");
        mFragmentSignIn.getView().findViewById(R.id.btn_sign_in).performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mLaunchActivity.getResources().getString(R.string.email_invalid), mTvWrongValidation.getText().toString());
    }

    @Test
    public void inputCorrectEmailPassword_shouldNotShowValidationText() {
        inputWrongFormatEmail_shouldShowValidationText();
        mEdtEmail.setText("hoang06kx1@gmail.com");
        mEdtPassword.setText("Goin@123");
        mFragmentSignIn.getView().findViewById(R.id.btn_sign_in).performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.GONE);
    }

    @Captor
    ArgumentCaptor<LogInCallback> logInCallbackCaptor;

    @Test
    public void loginSuccessful_shouldShowMainActivity() {
        mEdtEmail.setText("hoang06kx1@gmail.com");
        mEdtPassword.setText("Goin@123");
        mockParseWrapper = ((TestParseApplication) mLaunchActivity.getApplication()).getParseWrapper();
        ParseUser mockUser = Mockito.mock(ParseUser.class);
        mFragmentSignIn.getView().findViewById(R.id.btn_sign_in).performClick();

        // user Argument Captor to capture the callback
        Mockito.verify(mockParseWrapper, Mockito.times(1)).logInInBackground(Mockito.anyString(), Mockito.anyString(),
                logInCallbackCaptor.capture());
        logInCallbackCaptor.getValue().done(mockUser, null);

        // verify MainActivity is called
        ShadowActivity shadowActivity = Shadows.shadowOf(mLaunchActivity);
        Intent intent = shadowActivity.peekNextStartedActivityForResult().intent;
        assertEquals(intent.getComponent(), new ComponentName(mLaunchActivity, MainActivity.class));
    }

    @Test
    public void loginFailed_shouldShowErrorToast() {
        mEdtEmail.setText("hoang06kx1@gmail.com");
        mEdtPassword.setText("Goin@123");
        mockParseWrapper = ((TestParseApplication) mLaunchActivity.getApplication()).getParseWrapper();
        mFragmentSignIn.getView().findViewById(R.id.btn_sign_in).performClick();
        Mockito.verify(mockParseWrapper, Mockito.times(1)).logInInBackground(Mockito.anyString(), Mockito.anyString(),
                logInCallbackCaptor.capture());
        logInCallbackCaptor.getValue().done(null, null);
        // verify error message shown
        assertEquals(ShadowToast.getTextOfLatestToast(), mLaunchActivity.getString(R.string.Unknown_error));
    }

    @Test
    public void inputMissingEmail_shouldShowError() {
        mEdtPassword.setText("Goin@123");
        mEdtEmail.setText("    ");
        mFragmentSignIn.getView().findViewById(R.id.btn_sign_in).performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mTvWrongValidation.getText().toString(), mLaunchActivity.getResources().getString(R.string.missing_email_password));
        inputCorrectEmailPassword_shouldNotShowValidationText();
    }
            
}
