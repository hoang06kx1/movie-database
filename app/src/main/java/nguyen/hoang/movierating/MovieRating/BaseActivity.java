package nguyen.hoang.movierating.MovieRating;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nguyen.hoang.movierating.ParseApplication;

/**
 * Created by Hoang on 1/31/2016.
 */
public class BaseActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
    }

    public ProgressDialog getProgressDialog() {
        return mProgressDialog;
    }

    public ParseApplication getParseApplication() {
        return (ParseApplication) getApplication();
    }
}
