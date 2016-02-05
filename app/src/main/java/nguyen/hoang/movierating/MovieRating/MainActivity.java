package nguyen.hoang.movierating.MovieRating;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import nguyen.hoang.movierating.R;

/**
 * Created by Hoang on 12/14/2015.
 */
public class MainActivity extends BaseActivity {
    private Toolbar mToolbar;
    private TabLayout mTabs;
    private ViewPager mHomePager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHomePager = (ViewPager) findViewById(R.id.pager);
        setupHomePager();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mTabs = (TabLayout) findViewById(R.id.tabs);
        mTabs.setupWithViewPager(mHomePager);
    }

    private void setupHomePager() {
        HomeAdapter homeAdapter = new HomeAdapter(getSupportFragmentManager());
        homeAdapter.addFragment(PopularMovieFragment.newInstance(), "POPULAR");
        homeAdapter.addFragment(MostRatedMovieFragment.newInstance(), "MOST RATED");
        homeAdapter.addFragment(MyFavMovieFragment.newInstance(), "MY FAV");
        mHomePager.setAdapter(homeAdapter);
    }

    public static class HomeAdapter extends FragmentPagerAdapter {
        private List<Fragment> mListFragment = new ArrayList<>();
        private List<String> mListragmentTitle = new ArrayList<>();

        public HomeAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mListFragment.add(fragment);
            mListragmentTitle.add(title);
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
