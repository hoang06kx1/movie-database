package nguyen.hoang.movierating.MovieRating;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import nguyen.hoang.movierating.CustomView.AutofitRecyclerView;
import nguyen.hoang.movierating.MovieRating.Model.Movie;
import nguyen.hoang.movierating.MovieRating.Model.MovieAdapter;
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
        mGridRecycleMovie.addItemDecoration(new SpaceItemDecoration(mGridRecycleMovie, spacingInPixels, true));
        MovieAdapter adapter = new MovieAdapter(new ArrayList<Movie>(), (AppCompatActivity) getActivity());
        mGridRecycleMovie.setAdapter(adapter);
        return v;
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private RecyclerView recyclerView;
        private int spacing;
        private boolean includeEdge;

        public SpaceItemDecoration(RecyclerView recyclerView, int spacing, boolean includeEdge) {
            this.recyclerView = recyclerView;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
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


