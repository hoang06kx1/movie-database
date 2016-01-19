package nguyen.hoang.movierating.MovieRating.Model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nguyen.hoang.movierating.MovieRating.MovieDetailActivity;
import nguyen.hoang.movierating.R;

/**
 * Created by Hoang on 1/12/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> mMovieList = new ArrayList<>();
    private AppCompatActivity mActivity;

    public MovieAdapter(ArrayList<Movie> movieList, AppCompatActivity activity) {
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
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mActivity, MovieDetailActivity.class);
                mActivity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        //return mMovieList.size();
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView movieImage;
        public TextView movieTitle;
        public ImageView movieFavorite;
        public View mView;

        public ViewHolder(View v) {
            super(v);
            movieImage = (ImageView) v.findViewById(R.id.img_movie);
            movieTitle = (TextView) v.findViewById(R.id.tv_title);
            mView = v;
            // movieFavorite = (ImageButton) v.findViewById(R.id.bt_favorite);
        }

        public View getView() {
            return mView;
        }
    }
}
