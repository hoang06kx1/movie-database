package nguyen.hoang.movierating.MovieRating;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import nguyen.hoang.movierating.MovieRating.Model.ReviewAdapter;
import nguyen.hoang.movierating.MovieRating.Model.WebService.Review.Response;
import nguyen.hoang.movierating.MovieRating.Model.WebService.Review.Result;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.WebService.BaseErrorListener;
import nguyen.hoang.movierating.WebService.BaseSuccessListener;
import nguyen.hoang.movierating.WebService.WebHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieReviewFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private int mMovieId;

    public MovieReviewFragment() {
        // Required empty public constructor
    }

    public static MovieReviewFragment newInstance(int movieId) {
        Bundle bundle = new Bundle();
        bundle.putInt(MovieDetailFragment.MOVIE_ID_STRING, movieId);
        MovieReviewFragment movieReviewFragment = new MovieReviewFragment();
        movieReviewFragment.setArguments(bundle);
        return movieReviewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieId = getArguments().getInt(MovieDetailFragment.MOVIE_ID_STRING, -1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_review, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_review);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.grid_column_space)));
        final BaseActivity baseActivity = (BaseActivity) getActivity();
        WebHelper.getMovieReviews(mMovieId, getString(nguyen.hoang.movierating.R.string.Loading),
                new BaseSuccessListener<String>(baseActivity) {
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        Gson gson = new GsonBuilder().create();
                        Response reviewsReponse = gson.fromJson(response, Response.class);
                        List<Result> results = reviewsReponse.getResults();
                        ReviewAdapter adapter = new ReviewAdapter(results, baseActivity);
                        mRecyclerView.setAdapter(adapter);
                    }
                }, new BaseErrorListener(baseActivity), baseActivity);
        return v;
    }

    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int mVerticalSpaceHeight;

        public VerticalSpaceItemDecoration(int mVerticalSpaceHeight) {
            this.mVerticalSpaceHeight = mVerticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = mVerticalSpaceHeight;
            }
        }
    }

}
