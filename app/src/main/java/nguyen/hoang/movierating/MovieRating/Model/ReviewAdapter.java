package nguyen.hoang.movierating.MovieRating.Model;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nguyen.hoang.movierating.MovieRating.Model.WebService.Review.Result;
import nguyen.hoang.movierating.R;

/**
 * Created by Hoang on 1/12/2016.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private ArrayList<Result> mListReview = new ArrayList<>();
    private AppCompatActivity mActivity;

    public ReviewAdapter(ArrayList<Result> listReview, AppCompatActivity activity) {
        mListReview = listReview;
        mActivity = activity;
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        //return mMovieList.size();
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Reviewer;
        public TextView Review;
        public TextView ReadMore;
        public View mView;

        public ViewHolder(View v) {
            super(v);
            Reviewer = (TextView) v.findViewById(R.id.name);
            Review = (TextView) v.findViewById(R.id.review);
            ReadMore = (TextView) v.findViewById(R.id.read_more);
            mView = v;
        }

        public View getView() {
            return mView;
        }
    }
}
