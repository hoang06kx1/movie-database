package nguyen.hoang.movierating;

import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

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
    private Button mBtnSignIn;

    @Before
    public void goToSignInFragmentFromLaunchFragment() {
        mLaunchActivity = Robolectric.setupActivity(LaunchActivity.class);
        mBtnSignIn = (Button) mLaunchActivity.findViewById(R.id.btn_sign_in);
        mBtnSignIn.performClick();
    }

    private void clickSignUp() {
        FragmentSignIn fragmentSignIn = (FragmentSignIn) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignIn.TAG);
        TextView tvSignUp = (TextView) fragmentSignIn.getView().findViewById(R.id.tv_sign_up);
        tvSignUp.performClick();
    }

    @Test
    public void pressBackButton_shouldShowLaunchFragmentInsteadOfSignInOrSignUpFragment() {
        clickSignUp(); // load sign up screen
        mLaunchActivity.onBackPressed(); // Emulate press back button
        FragmentLaunch fragmentLaunch = (FragmentLaunch) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentLaunch.TAG);
        assertNotNull(fragmentLaunch);
        assertTrue("Fragment launch shoud be visible after press back when on Sign Up screen", fragmentLaunch.isVisible());

        fragmentLaunch.getView().findViewById(R.id.btn_sign_in).performClick();
        clickSignUp(); // load sign up screen again
        FragmentSignUp fragmentSignUp = (FragmentSignUp) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignUp.TAG);
        fragmentSignUp.getView().findViewById(R.id.tv_sign_in).performClick(); // continue load sign in screen
        mLaunchActivity.onBackPressed();
        fragmentLaunch = (FragmentLaunch) mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentLaunch.TAG);
        assertNotNull(fragmentLaunch);
        assertTrue("Fragment launch shoud be visible after press back when on Sign In screen", fragmentLaunch.isVisible());


    }
}
