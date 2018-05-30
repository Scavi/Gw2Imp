package com.scavi.de.gw2imp.ui.filter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.ui.filter.dim.IFilterDimensions;

import javax.annotation.Nonnull;

import static android.graphics.Color.TRANSPARENT;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.scavi.de.gw2imp.ui.filter.TabFilter.FilterSetting.Context1;
import static com.scavi.de.gw2imp.ui.filter.TabFilter.FilterSetting.Context2;


/**
 * The code was taken from https://github.com/IhorKlimov/Filters/, slightly changed and refactored.
 */
public class TabFilter {
    protected enum FilterSetting {Context1, Context2}

    private final Builder mBuilder;
    private DisplayMetrics mDisplayMetrics;
    private float mStartX;
    private int mBottomY;
    private int mBottomX;
    private float mBottomListStartY;
    private boolean mResetBottomList;
    private float mStartY;


    /**
     * Constructor
     *
     * @param builder the builder of the tab filter
     */
    private TabFilter(final Builder builder) {
        mBuilder = builder;
    }


    public void setup() {
        mDisplayMetrics = mBuilder.mContext.getResources().getDisplayMetrics();
        GradientDrawable gradientDrawable = (GradientDrawable)
                mBuilder.mFilterActionView.getBackground();
        gradientDrawable.setCornerRadius(0f);
    }


    public void openFilters(View view) {
        if (mStartX == 0.0f) {
            mStartX = view.getX();
            mStartY = view.getY();
            mBottomX = getBottomFilterXPosition();
            mBottomY = getBottomFilterYPosition();
            mBottomListStartY = mBuilder.mFilterActionView.getY();
        }

        int x = mBuilder.mFilterDimensions.getFinalXPosition();
        int y = mBuilder.mFilterDimensions.getFinalYPosition();

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);

        animator.addUpdateListener(animation -> {
            float v = (float) animation.getAnimatedValue();
            mBuilder.mOpenFilterButton.setX(x + (mStartX - x - ((mStartX - x) * v)));
            mBuilder.mOpenFilterButton.setY(y + (mStartY - y - ((mStartY - y) * (v * v))));
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeOpenFilterButtonBackground();
                new Handler().postDelayed(() -> mBuilder.mOpenFilterButton.animate()
                        .y(mBottomY)
                        .setDuration(200)
                        .start(), 50);
                new Handler().postDelayed(new OpenTabFilterAnimator(mBuilder, x, Context1),
                        200);
                if (mResetBottomList) {
                    resetFilterActionView();
                }
                new Handler().postDelayed(new OpenTabFilterAnimator(mBuilder, x, Context2),
                        400);
                revealFilterSheet(y);
            }
        });

        animator.start();

    }


    /**
     * This method sets, depending on the configured views, all components to invisible
     */
    private void setFilterComponentsInvisible() {
        if (mBuilder.mTabSheet != null) {
            mBuilder.mTabSheet.setVisibility(INVISIBLE);
            if (mBuilder.mHorizontalTabScroller != null) {
                mBuilder.mHorizontalTabScroller.setVisibility(INVISIBLE);
            }
        }
        mBuilder.mOpenFilterButton.setVisibility(VISIBLE);
        mBuilder.mFilterPossibilitiesLayout.setVisibility(INVISIBLE);
        if (mBuilder.mApply != null) {
            mBuilder.mApply.setVisibility(INVISIBLE);
        }
        mBuilder.mCancel.setVisibility(INVISIBLE);
    }

    /**
     * This method accepts will be called if the filter settings should be applied.
     */
    public void closeFilters() {
        setFilterComponentsInvisible();

        int x = mBuilder.mFilterDimensions.getFinalXPosition();
        int y = mBuilder.mFilterDimensions.getFinalYPosition();
        int startX = (int) mBuilder.mOpenFilterButton.getX();
        int startY = (int) mBuilder.mOpenFilterButton.getY();

        animateConcealing(y);
        animateBottomSheet();
        animateOpenFilterButton(x, y, startX, startY);
        mBuilder.mOpenFilterButton.setImageResource(mBuilder.mOpenFilterDrawableId);
        mBuilder.mOpenFilterButton.setBackgroundTintList(mBuilder.mSheetColor);
    }


    private void animateConcealing(final int y) {
        Animator reveal = ViewAnimationUtils.createCircularReveal(
                mBuilder.mFilterRevealView,
                mDisplayMetrics.widthPixels / 2,
                (int) (y - mBuilder.mFilterRevealView.getY()) +
                        mBuilder.mFilterDimensions.getFilterOpenButtonSize() / 2,
                mBuilder.mFilterRevealView.getHeight() * .5f,
                mBuilder.mFilterDimensions.getFilterOpenButtonSize() / 2);

        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mBuilder.mFilterRevealView.setVisibility(INVISIBLE);
                mBuilder.mOpenFilterButton.setBackgroundTintList(mBuilder.mButtonColor);
                mBuilder.mOpenFilterButton.setElevation(mDisplayMetrics.density * 4);

            }
        });
        reveal.start();
    }


    /**
     *
     */
    private void animateBottomSheet() {
        GradientDrawable gradientDrawable = (GradientDrawable) mBuilder.mFilterActionView
                .getBackground();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                mBuilder.mFilterActionView.getLayoutParams();

        int startWidth = mBuilder.mFilterActionView.getWidth();
        int startHeight = mBuilder.mFilterActionView.getHeight();
        int startY = (int) mBuilder.mFilterActionView.getY();

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(animation -> {
            float v = (float) animation.getAnimatedValue();
            gradientDrawable.setCornerRadius(mDisplayMetrics.density * 50 * v);

            int i = (int) (startWidth - (startWidth - mBuilder.mFilterDimensions
                    .getFilterOpenButtonSize()) * v);
            params.width = i;
            params.height = (int) (startHeight -
                    (startHeight - mBuilder.mFilterDimensions.getFilterOpenButtonSize()) * v);
            mBuilder.mFilterActionView.setY(mBuilder.mFilterDimensions.getFinalYPosition() +
                    (startY - mBuilder.mFilterDimensions.getFinalYPosition()) -
                    ((startY - mBuilder.mFilterDimensions.getFinalYPosition()) * v));

            mBuilder.mFilterActionView.requestLayout();
        });
        animator.start();
    }

    private void animateOpenFilterButton(final int x,
                                         final int y,
                                         final int startX,
                                         final int startY) {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(animation -> {
            float v = (float) animation.getAnimatedValue();
            mBuilder.mOpenFilterButton.setX(x - (x - startX - ((x - startX) * v)));
            mBuilder.mOpenFilterButton.setY(y + (startY - y - ((startY - y) * (v * v))));
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                new Handler().postDelayed(() -> {
                    returnFabToInitialPosition();
                    mBuilder.mFilterActionView.setVisibility(INVISIBLE);
                }, 50);
            }
        });

        animator.start();
    }


    /**
     * Resets the filter action view. The filter action view contains the apply / cancel button
     * (depending on the configuration)
     */
    private void resetFilterActionView() {
        mResetBottomList = false;
        mBuilder.mFilterActionView.setVisibility(VISIBLE);
        GradientDrawable gradientDrawable =
                (GradientDrawable) mBuilder.mFilterActionView.getBackground();
        mBuilder.mFilterActionView.setAlpha(0f);
        gradientDrawable.setCornerRadius(0f);
        RelativeLayout.LayoutParams params =
                (RelativeLayout.LayoutParams) mBuilder.mFilterActionView.getLayoutParams();
        params.width = -1;
        params.height = (int) (mDisplayMetrics.density * 64);
        mBuilder.mFilterActionView.setY(mBottomListStartY + mDisplayMetrics.density * 8);
        mBuilder.mFilterActionView.requestLayout();
    }

    private int getBottomFilterYPosition() {
        return (int) (mBuilder.mCancel.getY()
                + (mDisplayMetrics.heightPixels - getStatusBarHeight() - mDisplayMetrics.density
                * 64) - mDisplayMetrics.density * 4);
    }

    private int getBottomFilterXPosition() {
        return (int) (mBuilder.mCancel.getX()
                + mDisplayMetrics.widthPixels / 2
                - mDisplayMetrics.density * 4);
    }

    /**
     * Removes the background of the button that was used to open the filter
     */
    private void removeOpenFilterButtonBackground() {
        mBuilder.mOpenFilterButton.setBackgroundTintList(ColorStateList.valueOf(TRANSPARENT));
        mBuilder.mOpenFilterButton.setElevation(0f);
    }

    private void revealFilterSheet(int y) {
        mBuilder.mFilterRevealView.setVisibility(VISIBLE);

        Animator a = ViewAnimationUtils.createCircularReveal(
                mBuilder.mFilterRevealView,
                mDisplayMetrics.widthPixels / 2,
                (int) (y - mBuilder.mFilterRevealView.getY()) + mBuilder.mFilterDimensions
                        .getFilterOpenButtonSize() / 2,
                mBuilder.mFilterDimensions.getFilterOpenButtonSize() / 2,
                mBuilder.mFilterRevealView.getHeight() * .7f);
        a.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mBuilder.mFilterPossibilitiesLayout.setVisibility(VISIBLE);
            }
        });
        a.start();
    }


    /**
     *
     */
    private void returnFabToInitialPosition() {
        int x = mBuilder.mFilterDimensions.getFinalXPosition();
        int y = mBuilder.mFilterDimensions.getFinalYPosition();
        mResetBottomList = true;

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(animation -> {
            float v = (float) animation.getAnimatedValue();
            mBuilder.mOpenFilterButton.setX(x + ((mStartX - x) * v));
            mBuilder.mOpenFilterButton.setY((float) (y + (mStartY - y) * (Math.pow(v, .5f))));
        });
        animator.start();
    }

    /**
     * @return the height of the status bar
     */
    private int getStatusBarHeight() {
        return (int) (mDisplayMetrics.density * 24);
    }


    private class OpenTabFilterAnimator implements Runnable {
        private final Builder mBuilder;
        private final int mTargetXPosition;
        private final FilterSetting mFilterSetting;

        OpenTabFilterAnimator(final Builder builder,
                              final int targetXPosition,
                              final FilterSetting filterSetting) {
            mBuilder = builder;
            mTargetXPosition = targetXPosition;
            mFilterSetting = filterSetting;
        }

        @Override
        public void run() {
            if (mFilterSetting == FilterSetting.Context1) {
                initiateFirstContext();
            } else if (mFilterSetting == Context2) {
                initiateSecondContext();
            }

        }


        private void initiateFirstContext() {
            mBuilder.mCancel.setVisibility(VISIBLE);
            mBuilder.mCancel.setTranslationX(-(mBottomX - mTargetXPosition));
            mBuilder.mCancel.animate()
                    .translationXBy(mBottomX - mTargetXPosition)
                    .setDuration(200)
                    .start();
            mBuilder.mOpenFilterButton.animate()
                    .x(mBottomX)
                    .setDuration(200)
                    .start();
            mBuilder.mOpenFilterButton.animate()
                    .x(mBottomX)
                    .setDuration(200)
                    .start();

            if (mBuilder.mTabSheet != null) {
                mBuilder.mTabSheet.setScaleY(0f);
                mBuilder.mTabSheet.setVisibility(VISIBLE);
                mBuilder.mTabSheet.animate()
                        .scaleY(1f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                if (mBuilder.mHorizontalTabScroller != null) {
                                    mBuilder.mHorizontalTabScroller.setVisibility(VISIBLE);
                                }
                            }
                        })
                        .setDuration(200)
                        .start();
            }
        }

        private void initiateSecondContext() {
            mBuilder.mFilterActionView.animate()
                    .alpha(1f)
                    .setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mBuilder.mOpenFilterButton.setImageResource(R.drawable.cancel); // FIXME
                            mBuilder.mOpenFilterButton.setVisibility(INVISIBLE);
                            mBuilder.mOpenFilterButton.setX(
                                    mBuilder.mCancel.getX() - mDisplayMetrics.density * 4);
                            mBuilder.mOpenFilterButton.setY(getBottomFilterYPosition());
                            if (mBuilder.mApply != null) {
                                mBuilder.mApply.setVisibility(VISIBLE);
                            }
                        }
                    }).start();
        }
    }


    public static class Builder {
        private Context mContext;
        private View mLookupView;
        private View.OnClickListener mClickListener;
        private View mFilterPossibilitiesLayout;
        private View mTabSheet;
        private View mFilterActionView;
        private View mApply;
        private View mCancel;
        private View mHorizontalTabScroller;
        private ImageButton mOpenFilterButton;
        private View mFilterRevealView;
        private ColorStateList mSheetColor;
        private ColorStateList mButtonColor;
        private int mOpenFilterDrawableId;
        private IFilterDimensions mFilterDimensions;


        public Builder(@Nonnull final Context context,
                       @Nonnull final View lookupView,
                       @Nonnull final View.OnClickListener clickListener) {
            mContext = context;
            mLookupView = lookupView;
            mClickListener = clickListener;
        }


        /**
         * Sets the filter dimensions that will be used to determine the position of the view
         *
         * @param filterDimensions the filter dimensions
         * @return this
         */
        public Builder setFilterDimensions(@Nonnull final IFilterDimensions filterDimensions) {
            mFilterDimensions = filterDimensions;
            return this;
        }

        /**
         * Sets the layout for all filter information that will be presented after the filter
         * layer will be revealed
         *
         * @param filterDataContainerId the id of the filter data container
         * @return this
         */
        public Builder setFilterPossibilitiesLayout(final int filterDataContainerId) {
            mFilterPossibilitiesLayout = mLookupView.findViewById(filterDataContainerId);
            return this;
        }


        /**
         * Sets the view that contains the cancel and commit action.
         *
         * @param filterActionViewId the id for the cancel and commit action views
         * @return this
         */
        public Builder setFilterActionView(final int filterActionViewId) {
            mFilterActionView = mLookupView.findViewById(filterActionViewId);
            return this;
        }


        /**
         * Sets the view to apply the current filter action. The apply button is optional
         * in case that the view will be closed
         *
         * @param applyFiltersViewId the view id to apply the filter action
         * @return this
         */
        public Builder setApplyFilterView(final int applyFiltersViewId) {
            mApply = mLookupView.findViewById(applyFiltersViewId);
            mApply.setOnClickListener(mClickListener);
            return this;
        }


        /**
         * Sets the view to cancel the current filter action
         *
         * @param cancelFilterViewId the view id to cancel the filter action
         * @return this
         */
        public Builder setCancelFilterView(final int cancelFilterViewId) {
            mCancel = mLookupView.findViewById(cancelFilterViewId);
            mCancel.setOnClickListener(mClickListener);
            return this;
        }


        /**
         * Set the sheet for the tabs (e.g. if you have multiple filter mechanisms on different
         * sheets)
         *
         * @param tabSheetViewId the view id of the tab sheet
         * @return this
         */
        public Builder setTabSheetView(final int tabSheetViewId) {
            mTabSheet = mLookupView.findViewById(tabSheetViewId);
            return this;
        }


        /**
         * Set the view to reveal all filter mechanisms in the current context
         *
         * @param filterRevealViewId the view id to reveal the filter
         * @return this
         */
        public Builder setFilterRevealView(final int filterRevealViewId) {
            mFilterRevealView = mLookupView.findViewById(filterRevealViewId);
            return this;
        }


        /**
         * Sets the view for the horizontal tab scroller.
         * The horizontal tab scroller is optional
         *
         * @param horizontalTabScrollerId the horizontal tab scroller id
         * @return this
         */
        public Builder setHorizontalTabScroller(final int horizontalTabScrollerId) {
            mHorizontalTabScroller = mLookupView.findViewById(horizontalTabScrollerId);
            return this;
        }


        /**
         * Sets the view for the button to open all filter possibilities
         *
         * @param openFilterButtonId the id of the button to open all filter possibilities
         * @return this
         */
        public Builder setOpenFilterButton(final int openFilterButtonId) {
            mOpenFilterButton = mLookupView.findViewById(openFilterButtonId);
            mOpenFilterButton.setOnClickListener(mClickListener);
            return this;
        }


        /**
         * Sets the sheet color
         *
         * @param sheetColor the sheet color
         * @return this
         */
        public Builder setSheetColor(@ColorRes final int sheetColor) {
            mSheetColor = ColorStateList.valueOf(ContextCompat.getColor(mContext, sheetColor));
            return this;
        }


        /**
         * Sets the button color
         *
         * @param buttonColor the sheet color
         * @return this
         */
        public Builder setButtonColor(@ColorRes final int buttonColor) {
            mButtonColor = ColorStateList.valueOf(ContextCompat.getColor(mContext, buttonColor));
            return this;
        }

        /**
         * Set the id of the drawable that is shown when the filter is closed
         *
         * @param openFilterDrawableId the id of the drawable that is shown when the filter is
         *                             closed
         * @return this
         */
        public Builder setOpenFilterDrawableId(final int openFilterDrawableId) {
            mOpenFilterDrawableId = openFilterDrawableId;
            return this;
        }

        public TabFilter build() {
            return new TabFilter(this);
        }
    }
}
