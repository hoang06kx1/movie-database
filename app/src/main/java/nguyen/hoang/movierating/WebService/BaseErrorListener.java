package nguyen.hoang.movierating.WebService;


import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import nguyen.hoang.movierating.MovieRating.BaseActivity;

/**
 * Created by Hoang on 1/31/2016.
 */
public class BaseErrorListener implements Response.ErrorListener {
    AppCompatActivity mActivity = null;
    ProgressDialog mDialog = null;

    public BaseErrorListener(BaseActivity baseActivity, String tracking) {
        mActivity = baseActivity;
        mDialog = baseActivity.getProgressDialog();
        Log.e("Volley", "Request failed:" + tracking);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (error != null) {
            error.printStackTrace();
        }

        if (mDialog != null && mDialog.isShowing()) {
            mDialog.hide();
        }

        if (mActivity != null && !mActivity.isFinishing() && error != null && error.getMessage() != null && !error.getMessage().isEmpty()) {
            new AlertDialog.Builder(mActivity).setTitle("Error").setMessage(error.getMessage()).setPositiveButton("OK", null).show();
        }
    }
}
