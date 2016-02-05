package nguyen.hoang.movierating.WebService;

import android.app.ProgressDialog;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import nguyen.hoang.movierating.MovieRating.BaseActivity;

/**
 * Created by hoangnguyen on 28/01/2016.
 */
public class WebHelper {
    public static final String API_KEY = "?api_key=c125a18cee35826d09eaee39775b881a";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";

    private static void showProgressDialog(BaseActivity activity, String string) {
        if (activity != null) {
            ProgressDialog dialog = activity.getProgressDialog();
            if (dialog != null) {
                dialog.setMessage(string);
                if (!dialog.isShowing()) {
                    dialog.show();
                }
            }
        }
    }

    public static void getPopularMovies(int page, String loadingMessage, Response.Listener<String> successListener, Response.ErrorListener errorListener, BaseActivity activity) {
        showProgressDialog(activity, loadingMessage);
        String url = BASE_URL + "movie/popular" + API_KEY + (page > 0 ? "&page=" + page : "");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, successListener, errorListener);
        activity.getParseApplication().addToRequestQueue(stringRequest, activity.getClass().getSimpleName());
    }

    public static void getTopRatedMovies(int page, String loadingMessage, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener, BaseActivity activity) {
        showProgressDialog(activity, loadingMessage);
        String url = BASE_URL + "movie/top_rated" + API_KEY + (page > 0 ? "&page=" + page : "");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, successListener, errorListener);
        activity.getParseApplication().addToRequestQueue(jsonObjectRequest, activity.getClass().getSimpleName());
    }

    public static void getMovieDetail(int id, String loadingMessage, Response.Listener<String> successListener, Response.ErrorListener errorListener, BaseActivity activity) {
        showProgressDialog(activity, loadingMessage);
        String url = BASE_URL + "movie/" + id + API_KEY;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, successListener, errorListener);
        activity.getParseApplication().addToRequestQueue(stringRequest, activity.getClass().getSimpleName());
    }

    public static void getMovieReviews(int id, String loadingMessage, Response.Listener<String> successListener, Response.ErrorListener errorListener, BaseActivity activity) {
        showProgressDialog(activity, loadingMessage);
        String url = BASE_URL + "movie/" + id + "/reviews" + API_KEY;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, successListener, errorListener);
        activity.getParseApplication().addToRequestQueue(stringRequest, activity.getClass().getSimpleName());
    }

    public static void getMovieTrailer(int id, String loadingMessage, Response.Listener<String> successListener, Response.ErrorListener errorListener, BaseActivity activity) {
        showProgressDialog(activity, loadingMessage);
        String url = BASE_URL + "movie/" + id + "/videos" + API_KEY;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, successListener, errorListener);
        activity.getParseApplication().addToRequestQueue(stringRequest, activity.getClass().getSimpleName());
    }
}
