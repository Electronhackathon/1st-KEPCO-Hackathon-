package kr.wonjun.electhon.views;

import android.content.Context;
import android.util.AttributeSet;

public class CartaSquareView extends android.support.v7.widget.AppCompatImageView {
    public CartaSquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}
