package com.squabbi.iitk.hover.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

/**
 * View class for constructing the look and design of each Tab's icon for use within
 * the Hover service.
 */

public class HoverTabView extends View {

    private int mBackgroundColour;
    private Integer mForegroundColour;

    private Drawable mCircleDrawable;
    private Drawable mIconDrawable;
    private int mIconInsetLeft, mIconInsetTop, mIconInsetRight, mIconInsetBottom;

    /**
     * Constructor for the HoverTabView View.
     * @param context context of the Hover service.
     * @param backgroundDrawable Drawable background for the Tab.
     * @param iconDrawable Drawable icon for the Tab.
     */
    public HoverTabView(Context context, Drawable backgroundDrawable, Drawable iconDrawable) {

        super(context);
        mCircleDrawable = backgroundDrawable;
        mIconDrawable = iconDrawable;
        init();
    }

    private void init() {

        int insetsDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                10, getContext().getResources().getDisplayMetrics());
        mIconInsetLeft = mIconInsetTop = mIconInsetRight = mIconInsetBottom = insetsDp;
    }

    /**
     * Sets the background colour of the drawable within the tab.
     * @param backgroundColour Background colour of the tab.
     */
    public void setTabBackgroundColour(@ColorInt int backgroundColour) {

        mBackgroundColour = backgroundColour;
        mCircleDrawable.setColorFilter(mBackgroundColour, PorterDuff.Mode.SRC_ATOP);
    }

    /**
     * Sets the icon colour of the tab.
     * @param foregroundColour Colour of the tab's icon.
     */
    public void setTabForegroundColour(@ColorInt Integer foregroundColour) {
        mForegroundColour = foregroundColour;
        if (null != mForegroundColour) {
            mIconDrawable.setColorFilter(mForegroundColour, PorterDuff.Mode.SRC_ATOP);
        } else {
            mIconDrawable.setColorFilter(null);
        }
    }

    /**
     * Sets the tab's icon.
     * @param icon Icon drawable for the tab.
     */
    public void setIcon(@Nullable Drawable icon) {
        mIconDrawable = icon;
        if (null != mForegroundColour && null != mIconDrawable) {
            mIconDrawable.setColorFilter(mForegroundColour, PorterDuff.Mode.SRC_ATOP);
        }
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Make circle as large as View minus padding.
        mCircleDrawable.setBounds(getPaddingLeft(), getPaddingTop(), w - getPaddingRight(), h - getPaddingBottom());

        // Re-size the icon as necessary.
        updateIconBounds();

        invalidate();
    }
}
