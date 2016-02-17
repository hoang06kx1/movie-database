package nguyen.hoang.movierating.MovieRating;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nguyen.hoang.movierating.CustomView.AutofitRecyclerView;
import nguyen.hoang.movierating.MovieRating.Model.FavoriteMovieAdapter;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.Utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFavMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFavMovieFragment extends Fragment {
    AutofitRecyclerView mGridRecycleMovie;
    FavoriteMovieAdapter mAdapter;
    public MyFavMovieFragment() {
        // Required empty public constructor
    }

    public static MyFavMovieFragment newInstance() {
        MyFavMovieFragment fragment = new MyFavMovieFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            /*
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            */
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_popular_movie, container, false);
        mGridRecycleMovie = (AutofitRecyclerView) v.findViewById(R.id.recycler_popular);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_column_space);
        mGridRecycleMovie.addItemDecoration(new PopularMovieFragment.SpaceItemDecoration(mGridRecycleMovie, spacingInPixels, true, 0));
        mAdapter = new FavoriteMovieAdapter((BaseActivity) getActivity());
        mGridRecycleMovie.setAdapter(mAdapter);
        Utils.setFavoriteMovieAdapter(mAdapter);
        return v;
    }
}
