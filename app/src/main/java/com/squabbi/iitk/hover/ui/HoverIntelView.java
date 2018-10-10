package com.squabbi.iitk.hover.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.squabbi.iitk.R;

import androidx.annotation.NonNull;
import io.mattcarroll.hover.Content;

public class HoverIntelView extends FrameLayout implements Content {

    public HoverIntelView(@NonNull Context context) {
        super(context);

        // Inflate the layout
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.hoverview_intel, this, true);

        // TODO: Load Ingress intel website
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
