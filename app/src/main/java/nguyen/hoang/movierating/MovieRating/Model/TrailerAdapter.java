package nguyen.hoang.movierating.MovieRating.Model;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nguyen.hoang.movierating.CustomView.MyLeadingMarginSpan2;
import nguyen.hoang.movierating.CustomView.ScaleImageView;
import nguyen.hoang.movierating.MovieRating.BaseActivity;
import nguyen.hoang.movierating.MovieRating.Model.WebService.MovieDetail.Response;
import nguyen.hoang.movierating.MovieRating.Model.WebService.MovieTrailers.Result;
import nguyen.hoang.movierating.R;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Hoang on 1/12/2016.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    public static final String DEVELOPER_KEY = "AIzaSyCzm9sDIYwNfuvhtWf1gmL2_NZQnp3GrJ8";
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

    public static void flowText(String text, int height, int width, TextView messageView) {
        float textLineHeight = messageView.getLineHeight();

        // Set the span according to the number of lines and width of the image
        int lines = (int) Math.ceil(height / textLineHeight);
        //For an html text you can use this line: SpannableStringBuilder ss = (SpannableStringBuilder)Html.fromHtml(text);
        SpannableString ss = new SpannableString(text);
        ss.setSpan(new MyLeadingMarginSpan2(lines, width), 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        messageView.setText(ss);

        /*
        // Align the text with the image by removing the rule that the text is to the right of the image
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)messageView.getLayoutParams();
        int[]rules = params.getRules();
        rules[RelativeLayout.RIGHT_OF] = 0;
        */
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
    public void onBindViewHolder(final TrailerAdapter.ViewHolder holder, final int position) {
        if (!isHeader(position)) {
            String thumbnailUrl = String.format(THUMBNAIL_PATH, mTrailerList.get(position - 1).getKey());
            Picasso.with(mActivity).load(thumbnailUrl).into(holder.movieThumbnail);
            holder.trailerTitle.setText(mTrailerList.get(position - 1).getName());
            holder.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    YouTubeInitializationResult result = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(mActivity);
                    if (result != YouTubeInitializationResult.SUCCESS) {
                        result.getErrorDialog(mActivity, 0).show();
                    } else {
                        mActivity.startActivity(YouTubeStandalonePlayer.createVideoIntent(mActivity,
                                DEVELOPER_KEY, mTrailerList.get(position - 1).getKey(), 0, true, true));
                    }
                }
            });
        } else {
            if (mResponse.getPoster_path() != null) {
                String imageUrl = "http://image.tmdb.org/t/p/w500" + mResponse.getPoster_path();
                Picasso.with(mActivity).load(imageUrl).into(holder.moviePoster, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.moviePoster.post(new Runnable() {
                            @Override
                            public void run() {
                                int[] locations = new int[2];
                                holder.moviePoster.getLocationOnScreen(locations);
                                int yPoster = locations[1];
                                holder.movieDescription.getLocationOnScreen(locations);
                                int yDescription = locations[1];
                                int defaultPadding = mActivity.getResources().getDimensionPixelSize(R.dimen.default_one_unit);
                                int posterHeight = holder.moviePoster.getHeight();
                                int posterWidth = holder.moviePoster.getWidth() + defaultPadding;
                                int textMarginLeadingHeight = yPoster + posterHeight - yDescription;
                                flowText(mResponse.getOverview(), textMarginLeadingHeight, posterWidth, holder.movieDescription);
                            }
                        });
                    }

                    @Override
                    public void onError() {

                    }
                });
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
        public GifImageView moviePoster;
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
                moviePoster = (GifImageView) v.findViewById(R.id.img_poster);
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
