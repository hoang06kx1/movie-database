package nguyen.hoang.movierating.domain;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Map;

import nguyen.hoang.movierating.ui.main.activity.BaseActivity;
import nguyen.hoang.movierating.domain.model.movie.popular.Result;
import nguyen.hoang.movierating.util.Utils;

/**
 * Created by Hoang on 1/12/2016.
 */
public class FavoriteMovieAdapter extends MovieAdapter {
    public FavoriteMovieAdapter(BaseActivity activity) {
        super(activity);
        updateMovieList();
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
        viewHolder.btFavorite.setVisibility(View.GONE);
        return viewHolder;
    }

    @Override
    protected void bindFavoriteButton(final int position, final FavoriteMovieAdapter.ViewHolder holder) {
        // do nothing
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void updateMovieList() {
        mMovieList = new ArrayList<>();
        for (Map.Entry<String, Result> entry : Utils.getFavoriteMovies().entrySet()) {
            mMovieList.add(entry.getValue());
        }
        notifyDataSetChanged();
    }
}
