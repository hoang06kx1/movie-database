package nguyen.hoang.movierating;

import android.content.Context;

import com.parse.Parse;

/**
 * Created by Hoang on 12/14/2015.
 */
public class TestParseApplication extends ParseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void enableParseLocalDatastore(Context context) {
        // Parse.enableLocalDatastore(context);
    }
}
