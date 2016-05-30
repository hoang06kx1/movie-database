package nguyen.hoang.movierating.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import nguyen.hoang.movierating.domain.TrailerAdapter;
import nguyen.hoang.movierating.domain.model.movie.detail.Response;
import nguyen.hoang.movierating.domain.model.movie.trailer.Result;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.domain.api.BaseErrorListener;
import nguyen.hoang.movierating.domain.api.BaseSuccessListener;
import nguyen.hoang.movierating.domain.api.WebHelper;
import nguyen.hoang.movierating.ui.main.activity.BaseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {
    public static final String MOVIE_ID_STRING = "MOVIE_ID";
    RecyclerView mRecyclerTrailers;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment newInstance(int id) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MOVIE_ID_STRING, id);
        movieDetailFragment.setArguments(bundle);
        return movieDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        mRecyclerTrailers = (RecyclerView) v.findViewById(R.id.recycler_trailers);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_column_space);
        mRecyclerTrailers.addItemDecoration(new PopularMovieFragment.SpaceItemDecoration(mRecyclerTrailers, spacingInPixels, false, -1));
        final GridLayoutManager gridLayoutManager = (GridLayoutManager) mRecyclerTrailers.getLayoutManager();

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == 0 ? gridLayoutManager.getSpanCount() : 1);
            }
        });

        final int movieId = getArguments().getInt(MOVIE_ID_STRING, -1);
        // Load movie detail
        WebHelper.getMovieDetail(movieId, getString(R.string.Loading),
                new BaseSuccessListener<String>((BaseActivity) getActivity()) {
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        Gson gson = new GsonBuilder().create();
                        Response detail = gson.fromJson(response, Response.class);
                        if (detail != null) {
                            TrailerAdapter adapter = new TrailerAdapter(detail, (BaseActivity) getActivity());
                            mRecyclerTrailers.setAdapter(adapter);
                            // Load trailers
                            WebHelper.getMovieTrailer(movieId, getString(R.string.Loading),
                                    new BaseSuccessListener<String>((BaseActivity) getActivity()) {
                                        @Override
                                        public void onResponse(String response) {
                                            super.onResponse(response);
                                            Gson gson = new GsonBuilder().create();
                                            nguyen.hoang.movierating.domain.model.movie.trailer.Response trailersResponse =
                                                    gson.fromJson(response, nguyen.hoang.movierating.domain.model.movie.trailer.Response.class);
                                            List<Result> trailers = trailersResponse.getResults();
                                            ((TrailerAdapter) mRecyclerTrailers.getAdapter()).setTrailerList(trailers);
                                            mRecyclerTrailers.getAdapter().notifyDataSetChanged();
                                        }
                                    }, new BaseErrorListener((BaseActivity) getActivity(), "get Movie Trailer " + movieId), (BaseActivity) getActivity());
                        }
                    }
                },
                new BaseErrorListener((BaseActivity) getActivity(), "get Movie detail! " + movieId) {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        super.onErrorResponse(error);
                        getFragmentManager().popBackStack();
                    }
                }, (BaseActivity) getActivity());
        return v;
    }

}
