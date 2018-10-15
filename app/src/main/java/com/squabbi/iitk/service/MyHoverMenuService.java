package com.squabbi.iitk.service;

import android.content.Context;
import android.content.Intent;

import com.squabbi.iitk.hover.HoverMenuFactory;

import java.io.IOException;

import androidx.annotation.NonNull;
import io.mattcarroll.hover.HoverMenu;
import io.mattcarroll.hover.HoverView;
import io.mattcarroll.hover.window.HoverMenuService;

/**
 * Hover service for initalising the hovering menus.
 */

public class MyHoverMenuService extends HoverMenuService {

    private static final String TAG = "MyHoverMenuService";
    private HoverMenu mHoverMenu;

    // TODO: May have to implement EventBus in onCreate and onDestroy

    /**
     * Static method for creating and starting a new Hover Menu service.
     * @param context context of the application.
     */
    public static void showFloatingMenu(Context context) {
        context.startService(new Intent(context, MyHoverMenuService.class));
    }

    @Override
    protected void onHoverMenuLaunched(@NonNull Intent intent, @NonNull HoverView hoverView) {
        hoverView.setMenu(createHoverMenu());
        hoverView.collapse();
    }

    private HoverMenu createHoverMenu() {
        try {
            return new HoverMenuFactory().createMenus(getContextForHoverMenu());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
