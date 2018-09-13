package com.squabbi.iitk.hover.ui;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.squabbi.iitk.R;

import org.greenrobot.eventbus.EventBus;

import io.mattcarroll.hover.Content;

public class HoverTimerView extends FrameLayout implements Content {

    private final EventBus mBus;

    public HoverTimerView(@NonNull Context context, @NonNull EventBus eventBus) {
        super(context);
        mBus = eventBus;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.hoverview_timers, this, true);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //mBus.register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        //mBus.unregister(this);
        super.onDetachedFromWindow();
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

    }

    @Override
    public void onHidden() {

    }
}
