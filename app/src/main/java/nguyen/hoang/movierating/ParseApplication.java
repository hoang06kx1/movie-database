package nguyen.hoang.movierating;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.parse.Parse;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.LinkedHashMap;

import nguyen.hoang.movierating.MovieRating.Model.WebService.PopularMovies.Result;
import nguyen.hoang.movierating.Utils.LruBitmapCache;

/**
 * Created by Hoang on 12/14/2015.
 */
public class ParseApplication extends Application {
    public static final String TAG = "RequestFactory";
    public static LinkedHashMap<String, Result> sMapFavoriteMovies = new LinkedHashMap<>();
    private static ParseApplication mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public static synchronized ParseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
        mInstance = this;
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

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(getRequestQueue(), new LruBitmapCache());
        }
        return mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        addToRequestQueue(req, null);
    }

    public void cancelRequests(String tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void cancelRequests() {
        mRequestQueue.cancelAll(TAG);
    }

}
