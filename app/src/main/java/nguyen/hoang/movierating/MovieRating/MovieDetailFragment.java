package nguyen.hoang.movierating.MovieRating;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nguyen.hoang.movierating.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {


    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment newInstance() {
        return new MovieDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

}
