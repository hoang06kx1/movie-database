package nguyen.hoang.movierating.domain.api;


import android.app.ProgressDialog;

import com.android.volley.Response;

import nguyen.hoang.movierating.ui.main.activity.BaseActivity;

/**
 * Created by Hoang on 1/31/2016.
 */
public class BaseSuccessListener<T> implements Response.Listener<T> {
    BaseActivity mActivity = null;
    ProgressDialog mDialog = null;

    public BaseSuccessListener(BaseActivity baseActivity) {
        mActivity = baseActivity;
        mDialog = mActivity.getProgressDialog();
    }

    @Override
    public void onResponse(T response) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.hide();
        }
    }
}
