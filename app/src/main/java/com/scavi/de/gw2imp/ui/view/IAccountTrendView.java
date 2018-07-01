package com.scavi.de.gw2imp.ui.view;

import com.scavi.de.gw2imp.data.so.Trend;

import java.util.List;

public interface IAccountTrendView extends IStatusView {

    /**
     * Updates all trends
     *
     * @param allTrends all trends
     */
    void updateTrendView(final List<Trend> allTrends);
}
