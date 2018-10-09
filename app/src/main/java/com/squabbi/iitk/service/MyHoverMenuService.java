package com.squabbi.iitk.service;

import android.content.Context;
import android.content.Intent;

import com.squabbi.iitk.R;
import com.squabbi.iitk.hover.HoverMenuFactory;
import com.squabbi.iitk.hover.HoverTheme;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import io.mattcarroll.hover.HoverMenu;
import io.mattcarroll.hover.HoverView;
import io.mattcarroll.hover.window.HoverMenuService;

public class MasterHoverMenuService extends HoverMenuService {

    private static final String TAG = "MasterHoverMenuService";
    private HoverMenu mHoverMenu;

    public static void showFloatingMenu(Context context) {

        context.startService(new Intent(context, MasterHoverMenuService.class));
    }

    // TODO: May have to implement EventBus in onCreate and onDestroy


    @Override
    protected Context getContextForHoverMenu() {

        return new ContextThemeWrapper(this, R.style.AppTheme);
    }

    @Override
    protected void onHoverMenuLaunched(@NonNull Intent intent, @NonNull HoverView hoverView) {

        hoverView.setMenu(createHoverMenu());
        hoverView.collapse();
    }

    private HoverMenu createHoverMenu() {

        try {
            mHoverMenu = new HoverMenuFactory().createMenus();
            return mHoverMenu;
        } catch (IOException ioEx) {
            throw new RuntimeException(ioEx);
        }
    }

    public void onEventMainThread(@NonNull HoverTheme newTheme) {

        mHoverMenu.setTheme(newTheme);
    }
}
