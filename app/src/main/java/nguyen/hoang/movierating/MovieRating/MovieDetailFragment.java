package nguyen.hoang.movierating.MovieRating;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import nguyen.hoang.movierating.MovieRating.Model.Trailer;
import nguyen.hoang.movierating.MovieRating.Model.TrailerAdapter;
import nguyen.hoang.movierating.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {
    RecyclerView mRecyclerTrailers;

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
        View v = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        mRecyclerTrailers = (RecyclerView) v.findViewById(R.id.recycler_trailers);
        TrailerAdapter adapter = new TrailerAdapter(new ArrayList<Trailer>(), (AppCompatActivity) getActivity());
        mRecyclerTrailers.setAdapter(adapter);
        return v;
    }

}
