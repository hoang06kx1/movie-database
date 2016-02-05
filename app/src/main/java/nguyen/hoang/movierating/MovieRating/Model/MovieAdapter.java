package nguyen.hoang.movierating.MovieRating.Model;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nguyen.hoang.movierating.CustomView.ScaleImageView;
import nguyen.hoang.movierating.MovieRating.BaseActivity;
import nguyen.hoang.movierating.MovieRating.Model.WebService.PopularMovies.Result;
import nguyen.hoang.movierating.MovieRating.MovieDetailActivity;
import nguyen.hoang.movierating.MovieRating.MovieDetailFragment;
import nguyen.hoang.movierating.R;

/**
 * Created by Hoang on 1/12/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Result> mMovieList = new ArrayList<>();
    private BaseActivity mActivity;

    public MovieAdapter(List<Result> movieList, BaseActivity activity) {
        mMovieList = movieList;
        mActivity = activity;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, final int position) {
        holder.movieTitle.setText(mMovieList.get(position).getTitle());
        String imageUrl = "http://image.tmdb.org/t/p/w342" + mMovieList.get(position).getPoster_path();
        // holder.movieImage.setImageUrl("http://image.tmdb.org/t/p/w500" + imageUrl, mActivity.getParseApplication().getImageLoader());
        Picasso.with(mActivity).load(imageUrl).into(holder.movieImage);
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = mMovieList.get(position).getId();
                Intent i = new Intent(mActivity, MovieDetailActivity.class);
                i.putExtra(MovieDetailFragment.MOVIE_ID_STRING, (int) id);
                mActivity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ScaleImageView movieImage;
        public TextView movieTitle;
        public ImageView movieFavorite;
        public View mView;

        public ViewHolder(View v) {
            super(v);
            movieImage = (ScaleImageView) v.findViewById(R.id.img_movie);
            movieTitle = (TextView) v.findViewById(R.id.tv_title);
            mView = v;
            // movieFavorite = (ImageButton) v.findViewById(R.id.bt_favorite);
        }

        public View getView() {
            return mView;
        }
    }
}
