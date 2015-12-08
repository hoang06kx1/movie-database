package nguyen.hoang.movierating;

import android.support.v4.app.Fragment;
import android.widget.Button;

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

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Administrator on 11/26/2015.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class FragmentSignInTest {
    LaunchActivity mLaunchActivity;
    @Before
    public void loadSignInFragment() {
        mLaunchActivity = Robolectric.setupActivity(LaunchActivity.class);
        mLaunchActivity.findViewById(R.id.btn_sign_in).performClick();
    }

    @Test
    public void clickForgotPassword_shouldStartForgotPasswordFragment() {
        FragmentSignIn fragmentSignIn = (FragmentSignIn) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignIn.TAG);
        fragmentSignIn.getView().findViewById(R.id.tv_forget_password).performClick();
        FragmentForgotPassword fragmentForgotPassword = (FragmentForgotPassword) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentForgotPassword.TAG);
        assertNotNull(fragmentForgotPassword);
        assertTrue("Fragment Forgot Password should be shown", fragmentForgotPassword.isVisible());
        mLaunchActivity.onBackPressed();
        fragmentSignIn = (FragmentSignIn) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignIn.TAG);
        assertTrue("Should show fragment SignIn when press back button on forget password screen", fragmentSignIn.isVisible());
    }
}
