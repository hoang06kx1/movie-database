package nguyen.hoang.movierating.domain.api;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.LinkedHashMap;

import nguyen.hoang.movierating.ui.main.activity.BaseActivity;
import nguyen.hoang.movierating.domain.model.movie.popular.Result;
import nguyen.hoang.movierating.util.Constants;
import nguyen.hoang.movierating.util.Utils;

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

    public static void getTopRatedMovies(int page, String loadingMessage, Response.Listener<String> successListener, Response.ErrorListener errorListener, BaseActivity activity) {
        showProgressDialog(activity, loadingMessage);
        String url = BASE_URL + "movie/top_rated" + API_KEY + (page > 0 ? "&page=" + page : "");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, successListener, errorListener);
        activity.getParseApplication().addToRequestQueue(stringRequest, activity.getClass().getSimpleName());
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

    public static void createParseFavoriteMoviesObject(LinkedHashMap<String, Result> mapFavoriteMovies, SaveCallback callback) {
        ParseObject favoriteMovies = new ParseObject(Constants.FAVORITE_MOVIE_CLASS_STRING);
        favoriteMovies.setACL(new ParseACL(ParseUser.getCurrentUser()));
        Gson gson = new GsonBuilder().create();
        String jsonFavoriteMovies = gson.toJson(mapFavoriteMovies, LinkedHashMap.class);
        favoriteMovies.put(Constants.FAVORITE_MOVIE_CLASS_STRING, jsonFavoriteMovies);
        favoriteMovies.saveInBackground(callback);
    }

    public static void updateParseFavoriteMoviesObject(final LinkedHashMap<String, Result> mapFavoriteMovies) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.FAVORITE_MOVIE_CLASS_STRING);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    Gson gson = new GsonBuilder().create();
                    String jsonFavoriteMovies = gson.toJson(mapFavoriteMovies, LinkedHashMap.class);
                    object.put(Constants.FAVORITE_MOVIE_CLASS_STRING, jsonFavoriteMovies);
                    object.saveInBackground();
                }
            }
        });
    }

    public static void getUserFavoriteMovies(final FragmentActivity activity) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.FAVORITE_MOVIE_CLASS_STRING);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    if (object != null && object.getString(Constants.FAVORITE_MOVIE_CLASS_STRING) != null) {
                        String favoriteMoviesJson = object.getString(Constants.FAVORITE_MOVIE_CLASS_STRING);
                        Gson gson = new GsonBuilder().create();
                        LinkedHashMap<String, Result> favoriteMovies = gson.fromJson(favoriteMoviesJson, new TypeToken<LinkedHashMap<String, Result>>() {
                        }.getType());
                        Utils.setFavoriteMovies(favoriteMovies);
                    }
                } else {
                    if (e.getCode() == ParseException.OBJECT_NOT_FOUND) {
                        WebHelper.createParseFavoriteMoviesObject(Utils.getFavoriteMovies(), new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e != null) {
                                    e.printStackTrace();
                                    Utils.logOutUser(activity);
                                }
                            }
                        });
                    } else {
                        e.printStackTrace();
                        Utils.logOutUser(activity);
                    }
                }
            }
        });
    }
}
