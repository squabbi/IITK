package com.squabbi.iitk.hover.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.dmgdesignuk.locationutils.easylocationutility.EasyLocationUtility;
import com.dmgdesignuk.locationutils.easylocationutility.LocationRequestCallback;
import com.squabbi.iitk.R;

import androidx.annotation.NonNull;
import butterknife.ButterKnife;
import butterknife.OnClick;
import im.delight.android.webview.AdvancedWebView;
import io.mattcarroll.hover.Content;

public class HoverIntelView extends FrameLayout implements Content {

    private AdvancedWebView mWebView;

    private static final String INGRESS_BASE_URL = "https://www.ingress.com/intel?ll=%f,%f&z=15";
    private static final String INGRESS_INTEL_URL = "https://www.ingress.com/intel";

    public HoverIntelView(@NonNull Context context) {
        super(context);

        // Inflate the layout
        init();
    }

    @SuppressLint("DefaultLocale")
    private void init() {

        View layoutView = LayoutInflater.from(getContext()).inflate(R.layout.hoverview_intel, this, true);
        ButterKnife.bind(layoutView, this);

        mWebView = findViewById(R.id.hover_intel_webview);
        // TODO: Look at using EventBus to pass through Location
        mWebView.loadUrl(INGRESS_INTEL_URL);
    }

    @OnClick(R.id.hover_intel_fab)
    void onClick(View view) {
        // Referesh web-page
        mWebView.reload();
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
        mWebView.onResume();
    }

    @Override
    public void onHidden() {
        mWebView.onPause();
    }
}
