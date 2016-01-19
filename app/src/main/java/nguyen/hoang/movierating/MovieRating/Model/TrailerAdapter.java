package nguyen.hoang.movierating.MovieRating.Model;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

import nguyen.hoang.movierating.R;

/**
 * Created by Hoang on 1/12/2016.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    private ArrayList<Trailer> mMovieList = new ArrayList<>();
    private AppCompatActivity mActivity;

    public TrailerAdapter(ArrayList<Trailer> listTrailer, AppCompatActivity activity) {
        mMovieList = listTrailer;
        mActivity = activity;
    }

    @Override
    public TrailerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TrailerAdapter.ViewHolder holder, int position) {
        holder.videoView.setVideoURI(Uri.parse("https://youtu.be/h9tRIZyTXTI?t=623"));
        holder.videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.videoView.start();
            }
        });

    }

    @Override
    public int getItemCount() {
        //return mMovieList.size();
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public VideoView videoView;
        public TextView movieTitle;
        public View mView;

        public ViewHolder(View v) {
            super(v);
            videoView = (VideoView) v.findViewById(R.id.video);
            movieTitle = (TextView) v.findViewById(R.id.tv_title);
            mView = v;
        }

        public View getView() {
            return mView;
        }
    }
}
