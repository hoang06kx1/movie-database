package nguyen.hoang.movierating.ui.main.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import nguyen.hoang.movierating.ui.main.fragment.MostRatedMovieFragment;
import nguyen.hoang.movierating.ui.main.fragment.MyFavMovieFragment;
import nguyen.hoang.movierating.ui.main.fragment.PopularMovieFragment;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.util.Utils;
import nguyen.hoang.movierating.domain.api.WebHelper;

/**
 * Created by Hoang on 12/14/2015.
 */
public class MainActivity extends BaseActivity {
    private Toolbar mToolbar;
    private TabLayout mTabs;
    private ViewPager mHomePager;
    private HomeAdapter mHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebHelper.getUserFavoriteMovies(this);
        setContentView(R.layout.activity_main);
        mHomePager = (ViewPager) findViewById(R.id.pager);
        setupHomePager();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.findViewById(R.id.bt_sign_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog(getString(R.string.Logout));
                Utils.logOutUser(MainActivity.this);
            }
        });
        setSupportActionBar(mToolbar);
        mTabs = (TabLayout) findViewById(R.id.tabs);
        mTabs.setupWithViewPager(mHomePager);
    }

    private void setupHomePager() {
        mHomeAdapter = new HomeAdapter(getSupportFragmentManager());
        mHomeAdapter.addFragment(PopularMovieFragment.newInstance(), "POPULAR");
        mHomeAdapter.addFragment(MostRatedMovieFragment.newInstance(), "MOST RATED");
        mHomeAdapter.addFragment(MyFavMovieFragment.newInstance(), "MY FAV");
        mHomePager.setOffscreenPageLimit(2);
        mHomePager.setAdapter(mHomeAdapter);
    }

    public static class HomeAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mListFragment = new ArrayList<>();
        private List<String> mListragmentTitle = new ArrayList<>();

        public HomeAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mListFragment.add(fragment);
            mListragmentTitle.add(title);
        }

        public List<Fragment> getFragments() {
            return mListFragment;
        }

        @Override
        public Fragment getItem(int position) {
            return mListFragment.get(position);
        }

        @Override
        public int getCount() {
            return mListFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mListragmentTitle.get(position);
        }
    }
}
