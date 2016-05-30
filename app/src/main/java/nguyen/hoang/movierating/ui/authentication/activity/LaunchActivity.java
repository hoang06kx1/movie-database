package nguyen.hoang.movierating.ui.authentication.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.parse.ParseUser;

import nguyen.hoang.movierating.ui.authentication.fragment.LaunchFragment;
import nguyen.hoang.movierating.ui.authentication.fragment.SignInFragment;
import nguyen.hoang.movierating.ui.authentication.fragment.SignUpFragment;
import nguyen.hoang.movierating.ui.main.activity.MainActivity;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.util.Utils;

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
        LaunchFragment launchFragment = new LaunchFragment();
        Utils.replaceFragmentInAccountManagement(this, launchFragment, launchFragment.TAG, false);
    }

    @Override
    public void onBackPressed() {
        Fragment fragmentSignIn = getSupportFragmentManager().findFragmentByTag(SignInFragment.TAG);
        Fragment fragmentSignUp = getSupportFragmentManager().findFragmentByTag(SignUpFragment.TAG);
        if ((fragmentSignIn != null && fragmentSignIn.isVisible()) || (fragmentSignUp != null && fragmentSignUp.isVisible())) {
            Fragment fragmentLaunch = getSupportFragmentManager().findFragmentByTag(LaunchFragment.TAG);
            if (fragmentLaunch != null) {
                getSupportFragmentManager().beginTransaction().remove(fragmentLaunch).commit();
            }
            fragmentLaunch = new LaunchFragment();
            Utils.replaceFragmentInAccountManagement(this, fragmentLaunch, LaunchFragment.TAG, false);
        } else {
            super.onBackPressed();
        }
    }
}
