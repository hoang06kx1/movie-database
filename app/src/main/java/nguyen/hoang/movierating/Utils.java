package nguyen.hoang.movierating;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.parse.ParseException;

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

    public static void showErrorMessage(FragmentActivity activity, ParseException ex) {
        switch (ex.getCode()) {
            case ParseException.CONNECTION_FAILED:
                Toast.makeText(activity, activity.getString(R.string.network_failed), Toast.LENGTH_LONG).show();
                break;
            case ParseException.INVALID_EMAIL_ADDRESS:
                Toast.makeText(activity, activity.getString(R.string.email_invalid), Toast.LENGTH_LONG).show();
                break;
            case ParseException.USERNAME_TAKEN:
                Toast.makeText(activity, activity.getString(R.string.account_existed), Toast.LENGTH_LONG).show();
                break;
            case ParseException.OBJECT_NOT_FOUND:
                Toast.makeText(activity, activity.getString(R.string.wrong_email), Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(activity, "Code: " + ex.getCode() + " - " + ex.getMessage(), Toast.LENGTH_LONG).show();
                break;
        }
    }
}
