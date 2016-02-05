package nguyen.hoang.movierating.MovieRating;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import nguyen.hoang.movierating.MovieRating.Model.ReviewAdapter;
import nguyen.hoang.movierating.MovieRating.Model.WebService.Review.Result;
import nguyen.hoang.movierating.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieReviewFragment extends Fragment {
    private RecyclerView mRecyclerView;

    public MovieReviewFragment() {
        // Required empty public constructor
    }

    public static MovieReviewFragment newInstance() {
        return new MovieReviewFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_review, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_review);
        ReviewAdapter adapter = new ReviewAdapter(new ArrayList<Result>(), (AppCompatActivity) getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.grid_column_space)));
        mRecyclerView.setAdapter(adapter);
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
