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

public class MyHoverMenuService extends HoverMenuService {

    private static final String TAG = "MyHoverMenuService";
    private HoverMenu mHoverMenu;

    // TODO: May have to implement EventBus in onCreate and onDestroy


    public static void showFloatingMenu(Context context) {
        context.startService(new Intent(context, MyHoverMenuService.class));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Bus.getInstance().register(this);
    }

    @Override
    public void onDestroy() {
        //Bus.getInstance().unregister(this);
        super.onDestroy();
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
