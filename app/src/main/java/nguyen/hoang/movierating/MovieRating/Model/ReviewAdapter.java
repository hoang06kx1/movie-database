package nguyen.hoang.movierating.MovieRating.Model;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nguyen.hoang.movierating.MovieRating.BaseActivity;
import nguyen.hoang.movierating.MovieRating.Model.WebService.Review.Result;
import nguyen.hoang.movierating.R;

/**
 * Created by Hoang on 1/12/2016.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<Result> mListReviews = new ArrayList<>();
    private BaseActivity mActivity;

    public ReviewAdapter(List<Result> listReview, BaseActivity activity) {
        mListReviews = listReview;
        mActivity = activity;
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ReviewAdapter.ViewHolder holder, int position) {
        holder.Name.setText(mListReviews.get(position).getAuthor());
        holder.Review.setText(mListReviews.get(position).getContent());
        if (mListReviews.get(position).isExtendable()) {

        }
        final ViewTreeObserver vto = holder.Review.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Layout l = holder.Review.getLayout();
                if (l != null) {
                    int lines = l.getLineCount();
                    if (lines > 0)
                        if (l.getEllipsisCount(lines - 1) > 0) {
                            holder.ReadMore.setVisibility(View.VISIBLE);
                            holder.ReadMore.setText(mActivity.getString(R.string.Read_more));
                        }
                }
                if (vto.isAlive()) {
                    vto.removeOnGlobalLayoutListener(this);
                }
            }
        });

        holder.ReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.ReadMore.getText().equals(mActivity.getString(R.string.Read_more))) {
                    holder.Review.setMaxLines(1000);
                    holder.ReadMore.setText(mActivity.getString(R.string.Hide));
                } else {
                    holder.Review.setMaxLines(5);
                    holder.ReadMore.setText(mActivity.getString(R.string.Read_more));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListReviews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Name;
        public TextView Review;
        public TextView ReadMore;
        public View mView;

        public ViewHolder(View v) {
            super(v);
            Name = (TextView) v.findViewById(R.id.name);
            Review = (TextView) v.findViewById(R.id.review);
            ReadMore = (TextView) v.findViewById(R.id.read_more);
            mView = v;
        }

        public View getView() {
            return mView;
        }
    }
}
