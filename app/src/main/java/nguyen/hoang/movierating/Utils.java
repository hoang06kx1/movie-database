package nguyen.hoang.movierating;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import nguyen.hoang.movierating.AccountManagement.FragmentLaunch;
import nguyen.hoang.movierating.R;

/**
 * Created by smartdev on 02/12/2015.
 */
public class Utils {
    public static void replaceFragmentInAccountManagement(FragmentActivity activity, Fragment fragment, String tag) {
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, fragment, tag).addToBackStack(tag).commit();
    }

    public static void replaceFragmentInAccountManagement(FragmentActivity activity, Fragment fragment, String tag, boolean addToBackStack) {
        if (!addToBackStack) {
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, fragment, tag).commit();
        } else {
            replaceFragmentInAccountManagement(activity, fragment, tag);
        }
    }
}
