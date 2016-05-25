package nguyen.hoang.movierating.MovieRating;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import nguyen.hoang.movierating.CustomView.AutofitRecyclerView;
import nguyen.hoang.movierating.MovieRating.Model.MovieAdapter;
import nguyen.hoang.movierating.MovieRating.Model.WebService.PopularMovies.Response;
import nguyen.hoang.movierating.MovieRating.Model.WebService.PopularMovies.Result;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.Utils.Utils;
import nguyen.hoang.movierating.WebService.BaseErrorListener;
import nguyen.hoang.movierating.WebService.BaseSuccessListener;
import nguyen.hoang.movierating.WebService.WebHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MostRatedMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MostRatedMovieFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    AutofitRecyclerView mGridRecycleMovie;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MostRatedMovieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MostRatedMovieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MostRatedMovieFragment newInstance() {
        MostRatedMovieFragment fragment = new MostRatedMovieFragment();
        Bundle args = new Bundle();
        // TODO: add bungld args
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_popular_movie, container, false);
        mGridRecycleMovie = (AutofitRecyclerView) v.findViewById(R.id.recycler_popular);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_column_space);
        mGridRecycleMovie.addItemDecoration(new PopularMovieFragment.SpaceItemDecoration(mGridRecycleMovie, spacingInPixels, true, 0));
        WebHelper.getTopRatedMovies(0, getString(R.string.Loading),
                new BaseSuccessListener<String>((BaseActivity) getActivity()) {
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        final Gson gson = new GsonBuilder().create();
                        Response responseObject =
                                gson.fromJson(response, Response.class);
                        final List<Result> results = responseObject.getResults();
                        MovieAdapter adapter = new MovieAdapter(results, (BaseActivity) getActivity());
                        mGridRecycleMovie.setAdapter(adapter);
                        Utils.setTopRatedMovieAdapter(adapter);
                    }
                }, new BaseErrorListener((BaseActivity) getActivity(), "get TopRatedMovies"), (BaseActivity) getActivity());
        return v;
    }

}
