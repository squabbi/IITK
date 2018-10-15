package com.squabbi.iitk.hover;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

import com.squabbi.iitk.R;
import com.squabbi.iitk.hover.ui.HoverTabView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.mattcarroll.hover.Content;
import io.mattcarroll.hover.HoverMenu;

/**
 *
 */

public class MyHoverMenu extends HoverMenu {

    public static final String TIMER_ID = "timer";
    public static final String INTEL_ID = "intel";

    private Context mContext;
    private String mMenuId;
    private final List<Section> mSections = new ArrayList<>();

    MyHoverMenu(@NonNull Context context,
                @NonNull String menuId,
                @NonNull Map<String, Content> data) throws IOException {

        this.mContext = context;

        for (String tabId : data.keySet()) {

            mSections.add(new Section(
                    new SectionId(tabId),
                    createTabView(tabId),
                    data.get(tabId)
            ));
        }
    }

    private View createTabView(@NonNull String sectionId) {
        Resources resources = mContext.getResources();
        // Colours
        int secondaryColour = resources.getColor(R.color.secondaryColor, mContext.getTheme());
        int primraryColour = resources.getColor(R.color.primaryTextColor, mContext.getTheme());


        switch (sectionId) {
            case TIMER_ID: {
                return createTabView(R.drawable.ic_outline_timer_24px, secondaryColour, primraryColour);
            }
            case INTEL_ID: {
                return createTabView(R.drawable.ic_outline_map_24px, secondaryColour, primraryColour);
            }
            default: throw new RuntimeException("Unknown tab selected: " + sectionId);
        }
    }

    private View createTabView(@DrawableRes int tabBitmapRes, @ColorInt int backgroundColor, @ColorInt Integer iconColor) {
        Resources resources = mContext.getResources();

        HoverTabView tabView = new HoverTabView(mContext, resources.getDrawable(R.drawable.circle, mContext.getTheme()),
                resources.getDrawable(tabBitmapRes, mContext.getTheme()));

        tabView.setTabBackgroundColour(backgroundColor);
        tabView.setTabForegroundColour(iconColor);
        tabView.setElevation((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, resources.getDisplayMetrics()));

        return tabView;
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
        for (Section section :  mSections) {
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
