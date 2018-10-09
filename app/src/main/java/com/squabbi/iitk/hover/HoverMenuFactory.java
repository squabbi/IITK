package com.squabbi.iitk.hover;

import android.content.Context;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import io.mattcarroll.hover.Content;

public class HoverMenuFactory {

    public MasterHoverMenu createMenus(@NonNull Context context) throws IOException {

        // Create a map with keys and respective menus
        Map<String, Content> menuMap = new LinkedHashMap<>();
        menuMap.put(MasterHoverMenu.TIMER_ID, new HoverTimeView(context));

        return new MasterHoverMenu(context, "iitk", menuMap);
    }
}
