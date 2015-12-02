package nguyen.hoang.movierating;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import nguyen.hoang.movierating.AccountManagement.LaunchActivity;

/**
 * Created by smartdev on 02/12/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class FragmentSignInTest {
    private LaunchActivity mLaunchActivity;
    @Before
    public void goToSignInFragmentFromLaunchFragment() {
        mLaunchActivity = Robolectric.setupActivity(LaunchActivity.class);
    }
}
