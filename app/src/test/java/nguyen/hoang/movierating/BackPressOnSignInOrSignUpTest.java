package nguyen.hoang.movierating;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import nguyen.hoang.movierating.ui.authentication.fragment.LaunchFragment;
import nguyen.hoang.movierating.ui.authentication.fragment.SignInFragment;
import nguyen.hoang.movierating.ui.authentication.fragment.SignUpFragment;
import nguyen.hoang.movierating.ui.authentication.activity.LaunchActivity;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by smartdev on 02/12/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = TestParseApplication.class)
public class BackPressOnSignInOrSignUpTest {
    private LaunchActivity mLaunchActivity;

    @Before
    public void initLaunchActivity() {
        mLaunchActivity = Robolectric.setupActivity(LaunchActivity.class);
    }

    @Test
    public void pressBackButtonOnSignInOrSignUpScreen_shouldShowLaunchScreen() {
        pressBackButtonOnSignInOrSignUpScreen_shouldShowLaunchScreen(1);
        pressBackButtonOnSignInOrSignUpScreen_shouldShowLaunchScreen(2);
    }

    public void pressBackButtonOnSignInOrSignUpScreen_shouldShowLaunchScreen(int mode) {
        // Simple steps
        mLaunchActivity.findViewById(R.id.btn_sign_in).performClick(); // load sign in screen
        if (mode == 1) {
            mLaunchActivity.onBackPressed(); // Emulate press back button
        } else {
            mLaunchActivity.findViewById(R.id.btn_cancel).performClick();
        }
        LaunchFragment launchFragment = (LaunchFragment) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(LaunchFragment.TAG);
        assertNotNull(launchFragment);
        assertTrue("Fragment launch shoud be visible after press back when on Sign In screen", launchFragment.isVisible());

        mLaunchActivity.findViewById(R.id.btn_sign_up).performClick(); // load sign up screen
        if (mode == 1) {
            mLaunchActivity.onBackPressed(); // Emulate press back button
        } else {
            mLaunchActivity.findViewById(R.id.btn_cancel).performClick();
        }
        launchFragment = (LaunchFragment) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(LaunchFragment.TAG);
        assertNotNull(launchFragment);
        assertTrue("Fragment launch shoud be visible after press back when on Sign Up screen", launchFragment.isVisible());

        // Complex steps
        mLaunchActivity.findViewById(R.id.btn_sign_in).performClick(); // load sign in screen
        SignInFragment signInFragment = (SignInFragment) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(SignInFragment.TAG);
        signInFragment.getView().findViewById(R.id.tv_sign_up).performClick(); // continue load sign up screen
        if (mode == 1) {
            mLaunchActivity.onBackPressed(); // Emulate press back button
        } else {
            mLaunchActivity.findViewById(R.id.btn_cancel).performClick();
        }
        launchFragment = (LaunchFragment) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(LaunchFragment.TAG);
        assertNotNull(launchFragment);
        assertTrue("Fragment launch shoud be visible after press back when on Sign Up screen", launchFragment.isVisible());

        launchFragment.getView().findViewById(R.id.btn_sign_up).performClick(); // load sign up screen
        SignUpFragment signUpFragment = (SignUpFragment) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(SignUpFragment.TAG);
        signUpFragment.getView().findViewById(R.id.tv_sign_in).performClick();  // continue load sign in screen
        if (mode == 1) {
            mLaunchActivity.onBackPressed(); // Emulate press back button
        } else {
            mLaunchActivity.findViewById(R.id.btn_cancel).performClick();
        }
        launchFragment = (LaunchFragment) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(LaunchFragment.TAG);
        assertNotNull(launchFragment);
        assertTrue("Fragment launch shoud be visible after press back when on Sign In screen", launchFragment.isVisible());
    }
}
