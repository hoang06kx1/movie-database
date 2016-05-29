package nguyen.hoang.movierating;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.parse.Parse;
import com.parse.ParseUser;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import nguyen.hoang.movierating.Utils.LruBitmapCache;
import nguyen.hoang.movierating.Utils.ParseWrapper;

/**
 * Created by Hoang on 12/14/2015.
 */
public class ParseApplication extends Application {
    public static final String TAG = "RequestFactory";
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        initPicasso();
        initParse();
    }

    protected void enableParseLocalDatastore(Context context) {
        Parse.enableLocalDatastore(context);
    }

    protected void initPicasso() {
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        try {
            Picasso.setSingletonInstance(built);
        } catch (IllegalStateException ex) {
            // do nothing
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void initParse() {
        try {
            enableParseLocalDatastore(this);
            Parse.initialize(this);
        } catch (IllegalStateException ex) {
            // do nothing
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    // use for testing third party lib
    ParseWrapper mParseWrapper;
    public synchronized ParseWrapper getParseWrapper() {
        if (mParseWrapper != null) {
            mParseWrapper = new ParseWrapper();
        }
        return mParseWrapper;
    }
}
