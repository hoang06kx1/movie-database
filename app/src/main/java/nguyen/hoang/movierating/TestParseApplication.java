package nguyen.hoang.movierating;

import android.content.Context;

import org.mockito.Mockito;
import com.parse.ParseUser;

import nguyen.hoang.movierating.Utils.ParseWrapper;

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

    @Override
    public ParseWrapper getParseWrapper() {
        if (mParseWrapper == null) {
            mParseWrapper = Mockito.mock(ParseWrapper.class);
        }
        return mParseWrapper;
    }
}
