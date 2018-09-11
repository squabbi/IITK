package com.squabbi.iitk.service;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.squabbi.iitk.Bus;
import com.squabbi.iitk.hover.HoverMenuFactory;

import java.io.IOException;

import io.mattcarroll.hover.HoverMenu;
import io.mattcarroll.hover.HoverView;
import io.mattcarroll.hover.window.HoverMenuService;

public class MyHoverMenuService extends HoverMenuService {

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
            return new HoverMenuFactory().createMenus(getContextForHoverMenu(), Bus.getInstance());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
