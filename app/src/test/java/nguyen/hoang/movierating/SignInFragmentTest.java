package nguyen.hoang.movierating;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
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
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowToast;

import javax.inject.Inject;

import nguyen.hoang.movierating.ui.authentication.fragment.ForgotPasswordFragment;
import nguyen.hoang.movierating.ui.authentication.fragment.SignInFragment;
import nguyen.hoang.movierating.ui.authentication.activity.LaunchActivity;
import nguyen.hoang.movierating.ui.main.activity.MainActivity;
import nguyen.hoang.movierating.util.ParseWrapper;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Administrator on 11/26/2015.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = TestParseApplication.class)
public class SignInFragmentTest {
    LaunchActivity mLaunchActivity;
    SignInFragment mSignInFragment;
    TextView mTvWrongValidation;
    EditText mEdtPassword;
    EditText mEdtEmail;
    @Inject
    ParseWrapper mockParseWrapper;
    View mBtnSignIn;

    @Before
    public void loadSignInFragment() {
        mLaunchActivity = Robolectric.setupActivity(LaunchActivity.class);
        mLaunchActivity.findViewById(R.id.btn_sign_in).performClick();
        mSignInFragment = (SignInFragment) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(SignInFragment.TAG);
        assertNotNull(mSignInFragment);
        assertTrue(mSignInFragment.isVisible());
        mTvWrongValidation = (TextView) mSignInFragment.getView().findViewById(R.id.tv_wrong_validation);
        assertTrue(mTvWrongValidation.getVisibility() == View.GONE);
        mEdtPassword = (EditText) mSignInFragment.getView().findViewById(R.id.edt_password);
        mEdtEmail = (EditText) mSignInFragment.getView().findViewById(R.id.edt_email);
        mBtnSignIn = mSignInFragment.getView().findViewById(R.id.btn_sign_in);
        // init Mock injector
        MockitoAnnotations.initMocks(this);
        mockParseWrapper = ((TestParseApplication) mLaunchActivity.getApplication()).getParseWrapperComponent().inject();
    }

    @Test
    public void clickForgotPassword_shouldStartForgotPasswordFragment() {
        mSignInFragment.getView().findViewById(R.id.tv_forget_password).performClick();
        ForgotPasswordFragment forgotPasswordFragment = (ForgotPasswordFragment) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(ForgotPasswordFragment.TAG);
        assertNotNull(forgotPasswordFragment);
        assertTrue("Fragment Forgot Password should be shown", forgotPasswordFragment.isVisible());
        mLaunchActivity.onBackPressed();
        mSignInFragment = (SignInFragment) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(SignInFragment.TAG);
        assertTrue("Should show fragment SignIn when press back button on forget password screen", mSignInFragment.isVisible());
    }

    @Test
    public void inputWrongFormatPassword_shouldShowValidationText() {
        mEdtPassword.setText("$T_t");
        mEdtEmail.setText("hoang06kx1@gmail.com");
        mBtnSignIn.performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mLaunchActivity.getResources().getString(R.string.password_invalid), mTvWrongValidation.getText().toString());
    }

    @Test
    public void inputWrongFormatEmail_shouldShowValidationText() {
        mEdtEmail.setText("th@thkdf");
        mEdtPassword.setText("Goin@123");
        mBtnSignIn.performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mLaunchActivity.getResources().getString(R.string.email_invalid), mTvWrongValidation.getText().toString());
    }

    @Test
    public void inputCorrectEmailPassword_shouldNotShowValidationText() {
        inputWrongFormatEmail_shouldShowValidationText();
        mEdtEmail.setText("hoang06kx1@gmail.com");
        mEdtPassword.setText("Goin@123");
        mBtnSignIn.performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.GONE);
    }

    @Captor
    ArgumentCaptor<LogInCallback> logInCallbackCaptor;

    @Test
    public void loginSuccessful_shouldShowMainActivity() {
        mEdtEmail.setText("hoang06kx1@gmail.com");
        mEdtPassword.setText("Goin@123");

        ParseUser mockUser = Mockito.mock(ParseUser.class);
        mBtnSignIn.performClick();

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
        mBtnSignIn.performClick();
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
        mBtnSignIn.performClick();
        assertTrue(mTvWrongValidation.getVisibility() == View.VISIBLE);
        assertEquals(mTvWrongValidation.getText().toString(), mLaunchActivity.getResources().getString(R.string.missing_email_password));
        inputCorrectEmailPassword_shouldNotShowValidationText();
    }
            
}
