package com.scavi.de.gw2imp.ui.filter.dim;

import android.content.Context;
import android.util.DisplayMetrics;

import javax.annotation.Nonnull;

public class DimDisplayMetrics implements IFilterDimensions {

    private final DisplayMetrics mDisplayMetrics;


    public DimDisplayMetrics(@Nonnull final Context context) {
        mDisplayMetrics = context.getResources().getDisplayMetrics();
    }


    @Override
    public int getFinalXPosition() {
        return mDisplayMetrics.widthPixels / 2 - getFilterOpenButtonSize() / 2;
    }

    @Override
    public int getFinalYPosition() {
        int marginFromBottom = getFinalYPositionFromBottom();
        return mDisplayMetrics.heightPixels - marginFromBottom + getFilterOpenButtonSize() / 2;
//        return 0;
    }


    @Override
    public int getFinalYPositionFromBottom() {
        return (int) (mDisplayMetrics.density * 250);
    }

    @Override
    public int getFilterOpenButtonSize() {
        return (int) (mDisplayMetrics.density * 56);
    }
}
