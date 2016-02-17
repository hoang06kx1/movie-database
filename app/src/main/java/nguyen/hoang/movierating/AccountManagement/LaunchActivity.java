package nguyen.hoang.movierating.AccountManagement;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.parse.ParseUser;

import nguyen.hoang.movierating.MovieRating.MainActivity;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.Utils.Utils;

public class LaunchActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
        setContentView(R.layout.activity_launch);
        initView();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }
    }

    private void initView() {
        FragmentLaunch fragmentLaunch = new FragmentLaunch();
        Utils.replaceFragmentInAccountManagement(this, fragmentLaunch, fragmentLaunch.TAG, false);
    }

    @Override
    public void onBackPressed() {
        Fragment fragmentSignIn = getSupportFragmentManager().findFragmentByTag(FragmentSignIn.TAG);
        Fragment fragmentSignUp = getSupportFragmentManager().findFragmentByTag(FragmentSignUp.TAG);
        if ((fragmentSignIn != null && fragmentSignIn.isVisible()) || (fragmentSignUp != null && fragmentSignUp.isVisible())) {
            Fragment fragmentLaunch = getSupportFragmentManager().findFragmentByTag(FragmentLaunch.TAG);
            if (fragmentLaunch != null) {
                getSupportFragmentManager().beginTransaction().remove(fragmentLaunch).commit();
            }
            fragmentLaunch = new FragmentLaunch();
            Utils.replaceFragmentInAccountManagement(this, fragmentLaunch, FragmentLaunch.TAG, false);
        } else {
            super.onBackPressed();
        }
    }
}
