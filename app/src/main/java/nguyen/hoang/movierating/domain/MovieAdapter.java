package nguyen.hoang.movierating.domain;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nguyen.hoang.movierating.ui.misc.ScaleImageView;
import nguyen.hoang.movierating.ui.main.activity.BaseActivity;
import nguyen.hoang.movierating.domain.model.movie.popular.Result;
import nguyen.hoang.movierating.ui.main.activity.MovieDetailActivity;
import nguyen.hoang.movierating.ui.main.fragment.MovieDetailFragment;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.util.Utils;
import nguyen.hoang.movierating.domain.api.WebHelper;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Hoang on 1/12/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    protected List<Result> mMovieList = new ArrayList<>();
    protected BaseActivity mActivity;

    public MovieAdapter(List<Result> movieList, BaseActivity activity) {
        mMovieList = movieList;
        mActivity = activity;
    }

    public MovieAdapter(BaseActivity activity) {
        mActivity = activity;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.ViewHolder holder, final int position) {
        holder.movieTitle.setText(mMovieList.get(position).getTitle());
        String imageUrl = "http://image.tmdb.org/t/p/w342" + mMovieList.get(position).getPoster_path();
        // holder.movieImage.setImageUrl("http://image.tmdb.org/t/p/w500" + imageUrl, mActivity.getParseApplication().getImageLoader());
        Picasso.with(mActivity).load(imageUrl).into(holder.movieImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.loadingImage.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = mMovieList.get(position).getId();
                Intent i = new Intent(mActivity, MovieDetailActivity.class);
                i.putExtra(MovieDetailFragment.MOVIE_ID_STRING, (int) id);
                mActivity.startActivity(i);
            }
        });
        bindFavoriteButton(position, holder);
    }

    protected void bindFavoriteButton(final int position, final MovieAdapter.ViewHolder holder) {
        Drawable icFavorite = mActivity.getResources().getDrawable(R.drawable.ic_favorite);
        Drawable icFavoriteClicked = mActivity.getResources().getDrawable(R.drawable.ic_favorite_clicked);
        Long movieId = mMovieList.get(position).getId();
        holder.btFavorite.setImageDrawable(Utils.getFavoriteMovies().get(String.valueOf(movieId)) != null ? icFavoriteClicked : icFavorite);
        holder.btFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = mMovieList.get(position).getId();
                if (Utils.getFavoriteMovies().get(String.valueOf(id)) != null) {
                    Utils.getFavoriteMovies().remove(String.valueOf(id));
                } else {
                    Utils.getFavoriteMovies().put(String.valueOf(id), mMovieList.get(position));
                }
                WebHelper.updateParseFavoriteMoviesObject(Utils.getFavoriteMovies());
                Utils.notifyAllDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public GifImageView loadingImage;
        public ScaleImageView movieImage;
        public TextView movieTitle;
        public ImageButton btFavorite;
        public View mView;

        public ViewHolder(View v) {
            super(v);
            movieImage = (ScaleImageView) v.findViewById(R.id.img_movie);
            movieTitle = (TextView) v.findViewById(R.id.tv_title);
            loadingImage = (GifImageView) v.findViewById(R.id.loading_view);
            btFavorite = (ImageButton) v.findViewById(R.id.bt_favorite);
            mView = v;
            // movieFavorite = (ImageButton) v.findViewById(R.id.bt_favorite);
        }

        public View getView() {
            return mView;
        }
    }
}
