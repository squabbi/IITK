package com.squabbi.iitk.hover;

import android.content.Context;

import com.squabbi.iitk.hover.ui.HoverTimerView;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import io.mattcarroll.hover.Content;

public class HoverMenuFactory {

    public MyHoverMenu createMenus(@NonNull Context context) throws IOException {

        // Create a map with keys and respective menus
        Map<String, Content> menuMap = new LinkedHashMap<>();
        menuMap.put(MyHoverMenu.TIMER_ID, new HoverTimerView(context));

        return new MyHoverMenu(context, "iitk", menuMap);
    }
}
