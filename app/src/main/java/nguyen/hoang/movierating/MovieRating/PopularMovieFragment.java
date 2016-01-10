package nguyen.hoang.movierating.MovieRating;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nguyen.hoang.movierating.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PopularMovieFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PopularMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularMovieFragment extends Fragment {

    public PopularMovieFragment() {
        // Required empty public constructor
    }

    public static PopularMovieFragment newInstance() {
        PopularMovieFragment fragment = new PopularMovieFragment();
        Bundle args = new Bundle();
        // args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: placeholder for initialization params.
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movie, container, false);
    }
}


