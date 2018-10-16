package com.squabbi.iitk.hover;

import android.content.Context;
import android.location.Location;

import com.squabbi.iitk.hover.ui.HoverIntelView;
import com.squabbi.iitk.hover.ui.HoverTimerView;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import io.mattcarroll.hover.Content;

/**
 * A factory class for {@link MyHoverMenu} items. Used for creating the Hover menu's tabs and
 * desired items.
 */

public class HoverMenuFactory {

    public MyHoverMenu createMenus(@NonNull Context context) throws IOException {

        // Create a map with keys and respective menus
        Map<String, Content> menuMap = new LinkedHashMap<>();
        menuMap.put(MyHoverMenu.INTEL_ID, new HoverIntelView(context));

        return new MyHoverMenu(context, "iitk", menuMap);
    }
}
