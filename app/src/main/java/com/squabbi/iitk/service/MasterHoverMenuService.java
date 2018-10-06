package com.squabbi.iitk.service;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import io.mattcarroll.hover.HoverMenu;
import io.mattcarroll.hover.HoverView;
import io.mattcarroll.hover.window.HoverMenuService;

public class MasterHoverMenuService extends HoverMenuService {

    public static void showFloatingMenu(Context context) {

        context.startService(new Intent(context, MasterHoverMenuService.class));
    }

    // TODO: May have to implement EventBus in onCreate and onDestroy


    @Override
    protected void onHoverMenuLaunched(@NonNull Intent intent, @NonNull HoverView hoverView) {
//        super.onHoverMenuLaunched(intent, hoverView);
        hoverView.setMenu(createHoverMenu());
        hoverView.collapse();
    }

    private HoverMenu createHoverMenu() {

        try {
            return new HoverMenuFactory()
        }
    }
}
