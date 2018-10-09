package com.squabbi.iitk.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.mattcarroll.hover.overlay.OverlayPermission;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.squabbi.iitk.R;
import com.squabbi.iitk.service.MyHoverMenuService;

public class SettingsActivity extends AppCompatActivity {

    private static final Integer REQUEST_CODE_HOVER_PERMISSION = 91;
    private boolean mPermissionsRequested = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_hover_button)
    void onClick(View view) {
        startHoverService();
    }

    public void startHoverService() {

        // On Android M and above we need to ask the user for permission to display the Hover
        // menu within the "alert window" layer.  Use OverlayPermission to check for the permission
        // and to request it.
        if (!mPermissionsRequested && !OverlayPermission.hasRuntimePermissionToDrawOverlay(this)) {
            Intent intent = OverlayPermission.createIntentToRequestOverlayPermission(this);
            startActivityForResult(intent, REQUEST_CODE_HOVER_PERMISSION);
        } else { MyHoverMenuService.showFloatingMenu(this); }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (REQUEST_CODE_HOVER_PERMISSION == requestCode) {
            mPermissionsRequested = true;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
