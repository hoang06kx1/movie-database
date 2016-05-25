package nguyen.hoang.movierating.MovieRating;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
 * Activities that contain this fragment must implement the
 * {@link PopularMovieFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PopularMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularMovieFragment extends Fragment {
    AutofitRecyclerView mGridRecycleMovie;
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
        View v = inflater.inflate(R.layout.fragment_popular_movie, container, false);
        mGridRecycleMovie = (AutofitRecyclerView) v.findViewById(R.id.recycler_popular);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_column_space);
        mGridRecycleMovie.addItemDecoration(new SpaceItemDecoration(mGridRecycleMovie, spacingInPixels, true, 0));
        WebHelper.getPopularMovies(0, getString(R.string.Loading),
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
                        Utils.setPopularMovieAdapter(adapter);
                    }
                }, new BaseErrorListener((BaseActivity) getActivity(), "get Popular movies"), (BaseActivity) getActivity());
        return v;
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private RecyclerView recyclerView;
        private int spacing;
        private boolean includeEdge;
        private int complementValue;

        public SpaceItemDecoration(RecyclerView recyclerView, int spacing, boolean includeEdge, int complementValue) {
            this.recyclerView = recyclerView;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
            this.complementValue = complementValue;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            position = position + complementValue;
            if (position < 0) {
                super.getItemOffsets(outRect, view, parent, state);
                return;
            }

            int spanCount = ((GridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}


