package com.squabbi.iitk.hover.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.squabbi.iitk.R;

import androidx.annotation.NonNull;
import io.mattcarroll.hover.Content;

public class HoverTimerView extends FrameLayout implements Content {

    public HoverTimerView(@NonNull Context context) {
        super(context);

        // Inflate the layout
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.hoverview_timers, this, true);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public boolean isFullscreen() {
        return true;
    }

    @Override
    public void onShown() {
        // TODO: Anything that needs to be done when the Frame/Content is shown
    }

    @Override
    public void onHidden() {
        // TODO: Anything that needs to be done when the view is hidden
    }
}
