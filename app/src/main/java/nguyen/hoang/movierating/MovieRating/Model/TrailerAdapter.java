package nguyen.hoang.movierating.MovieRating.Model;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nguyen.hoang.movierating.CustomView.ScaleImageView;
import nguyen.hoang.movierating.MovieRating.BaseActivity;
import nguyen.hoang.movierating.MovieRating.Model.WebService.MovieDetail.Response;
import nguyen.hoang.movierating.MovieRating.Model.WebService.MovieTrailers.Result;
import nguyen.hoang.movierating.R;

/**
 * Created by Hoang on 1/12/2016.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    public static final String THUMBNAIL_PATH = "https://i1.ytimg.com/vi/%s/mqdefault.jpg";
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_TRAILER = 1;
    private List<Result> mTrailerList = new ArrayList<>();
    private Response mResponse = null;
    private AppCompatActivity mActivity;
    private float mRating;

    public TrailerAdapter(Response response, BaseActivity activity) {
        mActivity = activity;
        mResponse = response;
    }

    public void setTrailerList(List<Result> trailerList) {
        mTrailerList = trailerList;
    }

    @Override
    public TrailerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(viewType == VIEW_TYPE_HEADER ? R.layout.movie_detail_header : R.layout.trailer_item_layout, parent, false);
        return new ViewHolder(v, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return (isHeader(position) ? VIEW_TYPE_HEADER : VIEW_TYPE_TRAILER);
    }

    @Override
    public void onBindViewHolder(final TrailerAdapter.ViewHolder holder, int position) {
        if (!isHeader(position)) {
            String thumbnailUrl = String.format(THUMBNAIL_PATH, mTrailerList.get(position - 1).getKey());
            Picasso.with(mActivity).load(thumbnailUrl).into(holder.movieThumbnail);
            holder.trailerTitle.setText(mTrailerList.get(position - 1).getName());
        } else {
            if (mResponse.getPoster_path() != null) {
                String imageUrl = "http://image.tmdb.org/t/p/w500" + mResponse.getPoster_path();
                Picasso.with(mActivity).load(imageUrl).into(holder.moviePoster);
            }
            holder.movieDescription.setText(mResponse.getOverview());
            holder.movieTitle.setText(mResponse.getTitle());
            holder.movieDate.setText(mResponse.getRelease_date());
            mRating = mResponse.getVote_average();
        }
    }

    @Override
    public int getItemCount() {
        return mTrailerList.size() + 1;
    }

    private boolean isHeader(int position) {
        return (position == 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ScaleImageView moviePoster;
        public TextView movieTitle;
        public TextView movieDescription;
        public TextView movieDate;
        public ScaleImageView movieThumbnail;
        public TextView trailerTitle;
        public View mView;

        public ViewHolder(View v, int viewType) {
            super(v);
            mView = v;
            if (viewType == VIEW_TYPE_TRAILER) {
                movieThumbnail = (ScaleImageView) v.findViewById(R.id.img_thumbnail);
                trailerTitle = (TextView) v.findViewById(R.id.tv_title);
            } else {
                moviePoster = (ScaleImageView) v.findViewById(R.id.img_poster);
                movieTitle = (TextView) v.findViewById(R.id.tv_title);
                movieDate = (TextView) v.findViewById(R.id.date);
                movieDescription = (TextView) v.findViewById(R.id.description);
            }
        }

        public View getView() {
            return mView;
        }
    }
}
