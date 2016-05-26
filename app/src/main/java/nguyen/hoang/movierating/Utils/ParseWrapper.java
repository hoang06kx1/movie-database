package nguyen.hoang.movierating.Utils;

import com.parse.LogInCallback;
import com.parse.ParseUser;

/**
 * Created by Hoang on 5/26/2016.
 */
public class ParseWrapper {
    public void logInInBackground(String email, String password, LogInCallback callback) {
        ParseUser.logInInBackground(email, password, callback);
    }
}
