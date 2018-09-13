package com.squabbi.iitk.hover;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;

import com.squabbi.iitk.R;
import com.squabbi.iitk.hover.ui.HoverTabView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.mattcarroll.hover.Content;
import io.mattcarroll.hover.HoverMenu;

public class MyHoverMenu extends HoverMenu {

    public static final String TIMER_ID = "timer";
    public static final String LOCAL_PORTALS_ID = "local_portals";

    private Context mContext;
    private String mMenuId;
    private final List<Section> mSections = new ArrayList<>();

    MyHoverMenu(@NonNull Context context,
                @NonNull String menuId,
                @NonNull Map<String, Content> data) {
        this.mContext = context;
        this.mMenuId = menuId;

        for (String tabId : data.keySet()) {
            mSections.add(new Section(
                    new SectionId(tabId),
                    createTabView(tabId),
                    data.get(tabId))
            );
        }
    }

    private View createTabView(@NonNull String sectionId) {
        switch (sectionId) {
            case TIMER_ID: {
                return createTabView(R.drawable.ic_timer_white);
            }
            case LOCAL_PORTALS_ID: {
                return createTabView(R.drawable.ic_current_location_white);
            }
            default: {
                return createTabView(R.drawable.ic_error_white);
            }
        }
    }

    private View createTabView(@DrawableRes int tabBitmapRes) {
        Resources resources = mContext.getResources();
        return new HoverTabView(mContext, resources.getDrawable(R.drawable.tab_background), resources.getDrawable(tabBitmapRes));
    }

    @Override
    public String getId() {
        return mMenuId;
    }

    @Override
    public int getSectionCount() {
        return mSections.size();
    }

    @Nullable
    @Override
    public Section getSection(int index) {
        return mSections.get(index);
    }

    @Nullable
    @Override
    public Section getSection(@NonNull SectionId sectionId) {
        for (Section section : mSections) {
            if (section.getId().equals(sectionId)) {
                return section;
            }
        }
        return null;
    }

    @NonNull
    @Override
    public List<Section> getSections() {
        return new ArrayList<>(mSections);
    }
}
