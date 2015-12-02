package nguyen.hoang.movierating;

import android.support.v4.app.Fragment;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


import nguyen.hoang.movierating.AccountManagement.FragmentLaunch;
import nguyen.hoang.movierating.AccountManagement.FragmentSignIn;
import nguyen.hoang.movierating.AccountManagement.FragmentSignUp;
import nguyen.hoang.movierating.AccountManagement.LaunchActivity;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Administrator on 11/26/2015.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class FragmentLaunchTest {
    LaunchActivity mLaunchActivity;
    @Before
    public void setupFragment() {
        mLaunchActivity = Robolectric.setupActivity(LaunchActivity.class);
    }

    @Test
    public void shouldStartLaunchFragmentWhenLaunchActivityStart() {
        Fragment launchFragment = mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentLaunch.TAG);
        assertNotNull("launch fragment is null", launchFragment);
    }

    @Test
    public void clickSignInButtonShouldStartSignInFragment() {
        Button btnSignIn = (Button) mLaunchActivity.findViewById(R.id.btn_sign_in);
        // Button btnSignUp = (Button) mLaunchActivity.findViewById(R.id.btn_sign_up);
        btnSignIn.performClick();
        Fragment signInFragment = mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignIn.TAG);
        assertNotNull("sign in fragment is null", signInFragment);
    }

    @Test
    public void clickSignUpButtonShouldStartSignUpFragment() {
        Button btnSignUp = (Button) mLaunchActivity.findViewById(R.id.btn_sign_up);
        btnSignUp.performClick();
        Fragment signUpFragment = mLaunchActivity.getSupportFragmentManager().findFragmentByTag(FragmentSignUp.TAG);
        assertNotNull("sign in fragment is null", signUpFragment) ;
    }
}
