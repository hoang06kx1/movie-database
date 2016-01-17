package nguyen.hoang.movierating.CustomView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * ImageView that keeps aspect ratio when scaled
 */
public class ScaleImageView extends ImageView {

    public ScaleImageView(Context context) {
        super(context);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable mDrawable = getDrawable();
        if (mDrawable != null) {
            int mDrawableWidth = mDrawable.getIntrinsicWidth();
            int mDrawableHeight = mDrawable.getIntrinsicHeight();
            float actualAspect = (float) mDrawableWidth / (float) mDrawableHeight;

            // Assuming the width is ok, so we calculate the height.
            final int actualWidth = MeasureSpec.getSize(widthMeasureSpec);
            final int height = (int) (actualWidth / actualAspect);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
