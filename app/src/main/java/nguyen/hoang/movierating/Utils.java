package nguyen.hoang.movierating;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import org.apache.commons.validator.routines.EmailValidator;


/**
 * Created by smartdev on 02/12/2015.
 */
public class Utils {
    public static String PASSWORD_REGREX = "^(?=.*[A-Z])(?=.*(_|[^\\w])).{6,}$";
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

    public static boolean validateEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static boolean validatePassword(String password) {
        return (password != null && !password.isEmpty()) && password.matches(PASSWORD_REGREX);
    }
}
