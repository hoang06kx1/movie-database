package nguyen.hoang.movierating.ui.main.activity;

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

    public void showProgressDialog(String message) {
        mProgressDialog.setMessage(message);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void hideProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    public ParseApplication getParseApplication() {
        return (ParseApplication) getApplication();
    }
}
