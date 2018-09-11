package com.squabbi.iitk.hover;

import android.content.Context;
import android.support.annotation.NonNull;

import com.squabbi.iitk.Bus;
import com.squabbi.iitk.hover.ui.HoverTimerView;
import com.squabbi.iitk.hover.MyHoverMenu;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import io.mattcarroll.hover.Content;

public class HoverMenuFactory {

    public MyHoverMenu createMenus(@NonNull Context context, @NonNull EventBus bus) throws IOException {
        // Create a map with keys and respective menus
        Map<String, Content> menuMap = new LinkedHashMap<>();
        menuMap.put(MyHoverMenu.TIMER_ID, new HoverTimerView(context, Bus.getInstance()));

        return new MyHoverMenu(context, "iitk", menuMap);
    }
}
