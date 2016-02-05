package nguyen.hoang.movierating.MovieRating;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import nguyen.hoang.movierating.R;

public class MovieDetailActivity extends BaseActivity {
    Toolbar mToolbar;
    ViewPager mViewPager;
    TabLayout mTabs;
    int mMovieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieId = getIntent().getExtras().getInt(MovieDetailFragment.MOVIE_ID_STRING, -1);
        setContentView(R.layout.activity_movie_detail);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(mViewPager);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mTabs = (TabLayout) findViewById(R.id.tabs);
        mTabs.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MovieDetailFragment.newInstance(mMovieId), getString(R.string.DETAILS));
        adapter.addFragment(MovieReviewFragment.newInstance(), getString(R.string.REVIEWS));
        viewPager.setAdapter(adapter);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> mListFragment = new ArrayList<>();
        ArrayList<String> mListTitle = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mListFragment.add(fragment);
            mListTitle.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mListFragment.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mListTitle.get(position);
        }

        @Override
        public int getCount() {
            return mListFragment.size();
        }
    }
}
