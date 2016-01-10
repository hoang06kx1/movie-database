package nguyen.hoang.movierating;

import android.app.Application;
import android.content.Context;

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
        try {
            enableParseLocalDatastore(this);
            initParse(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void enableParseLocalDatastore(Context context) {
        Parse.enableLocalDatastore(context);
    }

    protected void initParse(Context context) {
        Parse.initialize(context);
    }

}
