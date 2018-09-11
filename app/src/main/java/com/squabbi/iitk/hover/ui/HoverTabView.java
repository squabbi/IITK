package com.squabbi.iitk.hover.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;

public class HoverTabView extends View {

    private int mBackgroundColor;
    private Integer mForegroundColor;

    private Drawable mCircleDrawable;
    private Drawable mIconDrawable;
    private int mIconInsetLeft, mIconInsetTop, mIconInsetRight, mIconInsetBottom;

    public HoverTabView(Context context, Drawable backgroundDrawable, Drawable iconDrawable) {
        super(context);
        mCircleDrawable = backgroundDrawable;
        mIconDrawable = iconDrawable;
        init();
    }

    private void init() {
        int insetsDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics());
        mIconInsetLeft = mIconInsetTop = mIconInsetRight = mIconInsetBottom = insetsDp;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Make circle as large as View minus padding.
        mCircleDrawable.setBounds(getPaddingLeft(), getPaddingTop(), w - getPaddingRight(), h - getPaddingBottom());

        // Re-size the icon as necessary.
        updateIconBounds();

        invalidate();
    }

    private void updateIconBounds() {
        if (null != mIconDrawable) {
            Rect bounds = new Rect(mCircleDrawable.getBounds());
            bounds.set(bounds.left + mIconInsetLeft, bounds.top + mIconInsetTop, bounds.right - mIconInsetRight, bounds.bottom - mIconInsetBottom);
            mIconDrawable.setBounds(bounds);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCircleDrawable.draw(canvas);
        if (null != mIconDrawable) {
            mIconDrawable.draw(canvas);
        }
    }
}
