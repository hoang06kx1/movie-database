package nguyen.hoang.movierating;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.ParameterizedRobolectricTestRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.Collection;

import nguyen.hoang.movierating.AccountManagement.FragmentLaunch;
import nguyen.hoang.movierating.AccountManagement.FragmentSignIn;
import nguyen.hoang.movierating.AccountManagement.FragmentSignUp;
import nguyen.hoang.movierating.AccountManagement.LaunchActivity;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by smartdev on 02/12/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
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
        FragmentLaunch fragmentLaunch = (FragmentLaunch) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentLaunch.TAG);
        assertNotNull(fragmentLaunch);
        assertTrue("Fragment launch shoud be visible after press back when on Sign In screen", fragmentLaunch.isVisible());

        mLaunchActivity.findViewById(R.id.btn_sign_up).performClick(); // load sign up screen
        if (mode == 1) {
            mLaunchActivity.onBackPressed(); // Emulate press back button
        } else {
            mLaunchActivity.findViewById(R.id.btn_cancel).performClick();
        }
        fragmentLaunch = (FragmentLaunch) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentLaunch.TAG);
        assertNotNull(fragmentLaunch);
        assertTrue("Fragment launch shoud be visible after press back when on Sign Up screen", fragmentLaunch.isVisible());

        // Complex steps
        mLaunchActivity.findViewById(R.id.btn_sign_in).performClick(); // load sign in screen
        FragmentSignIn fragmentSignIn = (FragmentSignIn) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignIn.TAG);
        fragmentSignIn.getView().findViewById(R.id.tv_sign_up).performClick(); // continue load sign up screen
        if (mode == 1) {
            mLaunchActivity.onBackPressed(); // Emulate press back button
        } else {
            mLaunchActivity.findViewById(R.id.btn_cancel).performClick();
        }
        fragmentLaunch = (FragmentLaunch) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentLaunch.TAG);
        assertNotNull(fragmentLaunch);
        assertTrue("Fragment launch shoud be visible after press back when on Sign Up screen", fragmentLaunch.isVisible());

        fragmentLaunch.getView().findViewById(R.id.btn_sign_up).performClick(); // load sign up screen
        FragmentSignUp fragmentSignUp = (FragmentSignUp) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignUp.TAG);
        fragmentSignUp.getView().findViewById(R.id.tv_sign_in).performClick();  // continue load sign in screen
        if (mode == 1) {
            mLaunchActivity.onBackPressed(); // Emulate press back button
        } else {
            mLaunchActivity.findViewById(R.id.btn_cancel).performClick();
        }
        fragmentLaunch = (FragmentLaunch) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentLaunch.TAG);
        assertNotNull(fragmentLaunch);
        assertTrue("Fragment launch shoud be visible after press back when on Sign In screen", fragmentLaunch.isVisible());
    }
}
