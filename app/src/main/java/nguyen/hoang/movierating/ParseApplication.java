package nguyen.hoang.movierating;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Hoang on 12/14/2015.
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // [Optional] Power your app with Local Datastore. For more info, go to
        // https://parse.com/docs/android/guide#local-datastore
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
    }
}
