package com.squabbi.iitk.hover;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.squabbi.iitk.R;
import com.squabbi.iitk.hover.ui.HoverTabView;
import com.squabbi.iitk.hover.ui.HoverTimerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.mattcarroll.hover.Content;
import io.mattcarroll.hover.HoverMenu;

public class MyHoverMenu extends HoverMenu {

    public static final String TIMER_ID = "timer";

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
                    data.get(tabId))
            );
        }
    }

    private View createTabView(@NonNull String sectionId) {
        switch (sectionId) {
            case TIMER_ID: {
                return createTabView(R.drawable.ic_timer_white_24dp);
            }
        }

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.tab_background);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return imageView;
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
