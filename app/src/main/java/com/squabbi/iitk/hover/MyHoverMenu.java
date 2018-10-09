package com.squabbi.iitk.hover;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

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

public class MasterHoverMenu extends HoverMenu {

    public static final String TIMER_ID = "timer";
    public static final String INTEL_ID = "intel";

    private Context mContext;
    private String mMenuId;
    private HoverTheme mTheme;
    private final List<Section> mSections = new ArrayList<>();

    MasterHoverMenu(@NonNull Context context,
                    @NonNull String menuId,
                    @NonNull Map<String, Content> data,
                    @NonNull HoverTheme theme) throws IOException {

        this.mContext = context;
        this.mTheme = theme;

        for (String tabId : data.keySet()) {

            mSections.add(new Section(
                    new SectionId(tabId),
                    createTabView(tabId),
                    data.get(tabId)
            ));
        }
    }

    private View createTabView(@NonNull String sectionId) {
        switch (sectionId) {
            case TIMER_ID: {
                return createTabView(R.drawable.ic_outline_timer_24px, mTheme.getAccentColour, mTheme.getBaseColour);
            }
        }

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.tab_background);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return imageView;
    }

    private View createTabView(@DrawableRes int tabBitmapRes, @ColorInt int backgroundColor, @ColorInt Integer iconColor) {
        Resources resources = mContext.getResources();
        // TODO: Implement elevation

        HoverTabView tabView = new HoverTabView(mContext, resources.getDrawable(R.drawable.tab_background),
                resources.getDrawable(tabBitmapRes));

        tabView.setTabBackgroundColour(backgroundColor);
        tabView.setTabForegroundColour(iconColor);
        tabView.setElevation((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, resources.getDisplayMetrics()));

        return tabView;
    }

    public void setTheme(HoverTheme theme) {
        mTheme = theme;
        notifyMenuChanged();
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
