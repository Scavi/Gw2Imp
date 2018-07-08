/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scavi.de.gw2imp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerTradingItemsComponent;
import com.scavi.de.gw2imp.dagger2.module.TradingItemsModule;
import com.scavi.de.gw2imp.data.entity.item.ItemEntity;
import com.scavi.de.gw2imp.model.so.TradingItemData;
import com.scavi.de.gw2imp.presenter.TradingItemsPresenter;
import com.scavi.de.gw2imp.ui.adapter.TradingItemAdapter;
import com.scavi.de.gw2imp.ui.filter.TabFilter;
import com.scavi.de.gw2imp.ui.filter.dim.DimDisplayMetrics;
import com.scavi.de.gw2imp.ui.graph.Gw2CurrencyFormatter;
import com.scavi.de.gw2imp.ui.util.ActivityHelper;
import com.scavi.de.gw2imp.ui.util.LineGraphSeriesCreator;
import com.scavi.de.gw2imp.ui.view.ITradingItemsView;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static android.view.View.VISIBLE;
import static java.util.Objects.requireNonNull;

public class TradingItemsFragment extends AbstractStatusFragment implements ITradingItemsView,
        AdapterView.OnItemClickListener, View.OnClickListener {

    @Inject
    TradingItemsPresenter mPresenter;
    private AutoCompleteTextView mSearchItemName;
    private GraphView mTradingItemGraph;
    private TabFilter mItemGraphFilter;

    /**
     * Called to have the fragment instantiate its user interface view. This is optional, and
     * non-graphical fragments can return null (which is the default implementation). This will
     * be called between onCreate(Bundle) and onActivityCreated(Bundle).
     *
     * @param inflater           LayoutInflater: The LayoutInflater object that can be used to
     *                           inflate any views in the fragment,
     * @param container          ViewGroup: If non-null, this is the parent view that the
     *                           fragment's UI should be attached to. The fragment should not add
     *                           the view itself, but this can be used to generate the
     *                           LayoutParams of the view.
     * @param savedInstanceState Bundle: If non-null, this fragment is being re-constructed from
     *                           a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(@Nonnull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        Context context = getActivity() != null ? getActivity().getApplicationContext() : null;
        if (context == null) {
            return null;
        }
        injectComponent(((IApplication) context).getComponent());
        View view = inflater.inflate(R.layout.fragment_trading_item, container, false);
        setupUiComponents(view);
        mItemGraphFilter.setup();
        return view;
    }


    @Override
    protected void setupUiComponents(final View fragmentView) {
        super.setupUiComponents(fragmentView);
        mTradingItemGraph = fragmentView.findViewById(R.id.item_price_chart);
        mTradingItemGraph.getViewport().setScalable(true);
        mTradingItemGraph.getViewport().setScalableY(true);

        mSearchItemName = fragmentView.findViewById(R.id.search_trading_item_name);
        mSearchItemName.setOnItemClickListener(this);
        mSearchItemName.addTextChangedListener(mPresenter.createTradingItemTextDelay());


        DimDisplayMetrics filterDimensions = new DimDisplayMetrics(requireNonNull(getContext()));
        mItemGraphFilter = new TabFilter.Builder(requireNonNull(getContext()), fragmentView, this)
                .setFilterPossibilitiesLayout(R.id.trading_items_filter_possibilities_container)
                .setFilterDimensions(filterDimensions)
                //.setTabSheetView(R.id.trading_item_tab_sheet)
                .setFilterActionView(R.id.trading_items_filter_actions)
                //.setApplyFilterView(R.id.trading_items_apply_filters)
                .setCancelFilterView(R.id.trading_items_cancel_filters)
                .setFilterRevealView(R.id.trading_items_reveal_filter)
                // .setHorizontalTabScroller(R.id.trading_items_tab_scroller)
                .setOpenFilterButton(R.id.trading_item_filter)
                .setSheetColor(R.color.core_floating_button_color)
                .setButtonColor(R.color.core_floating_button_color)
                .setOpenFilterDrawableId(R.drawable.ic_search_black_24dp)
                .build();

        if (!mPresenter.verifySearchIndexOptimal()) {
            ActivityHelper.showMessageInDialog(requireNonNull(getActivity()),
                    getString(R.string.trading_items_data_search_index_updating));
        }
    }


    @Override
    public void onClick(final View view) {
        if (view.getId() == R.id.trading_item_filter) {
            mItemGraphFilter.openFilters(view);
        } /*else if (view.getId() == R.id.trading_items_apply_filters) {
            mItemGraphFilter.acceptFilters();
        } */ else if (view.getId() == R.id.trading_items_cancel_filters) {
            mItemGraphFilter.closeFilters();
        }
    }


    @Override
    public void onItemClick(final AdapterView<?> adapterView,
                            final View view,
                            final int i,
                            final long l) {
        mPresenter.onItemSelected((ItemEntity) adapterView.getItemAtPosition(i));
    }

    /**
     * @return the current name of the item we want to find
     */
    @Override
    public String getItemSearchName() {
        return mSearchItemName.getText().toString();
    }


    /**
     * @param foundItems the list of items that were found
     */
    @Override
    public void updatePossibleItems(@Nonnull final List<ItemEntity> foundItems) {
        if (!isUpdateable()) {
            return;
        }
        Context context = getActivity() != null && getActivity().getApplicationContext() != null ?
                getActivity().getApplicationContext() : getContext();
        if (context == null) {
            return;
        }
        TradingItemAdapter adapter = new TradingItemAdapter(context,
                android.R.layout.simple_list_item_1,
                foundItems.toArray(new ItemEntity[foundItems.size()]));
        adapter.setNotifyOnChange(true);
        mSearchItemName.setAdapter(adapter);
    }


    @Override
    public void closeSearch() {
        if (!isUpdateable()) {
            return;
        }
        View view = getView();

        InputMethodManager imm = (InputMethodManager) requireNonNull(getContext()).
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(requireNonNull(view).getWindowToken(), 0);
        }
        mItemGraphFilter.closeFilters();
    }


    /**
     * Shows the UI components that will contain the data (set the components to visible)
     */
    @Override
    public void showComponents() {
        if (!isUpdateable()) {
            return;
        }
        requireNonNull(getActivity()).findViewById(R.id.item_price_chart)
                .setVisibility(VISIBLE);
        requireNonNull(getActivity()).findViewById(R.id.trading_item_information)
                .setVisibility(VISIBLE);
        mTradingItemGraph.getLegendRenderer().setVisible(true);
    }

    /**
     * Updates the graph with the prices of the merged history item prices and the item prices of
     * the current month.
     *
     * @param tradingItemData the trading item data containing the history, the current price and
     *                        the item information
     */
    @Override
    public void updateGraph(@Nonnull final TradingItemData tradingItemData) {
        // the legend (which color means what)
        mTradingItemGraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        int legendColor = ResourcesCompat.getColor(getResources(), R.color.core_title_text_color,
                null);
        mTradingItemGraph.getLegendRenderer().setTextColor(legendColor);
        // set the grid label renderer. Set the item prices, the horizontal and vertical labels
        int labelColor = ResourcesCompat.getColor(getResources(), R.color
                .core_highlight_text_color, null);
        Gw2CurrencyFormatter gwTradingItemFormatter = new Gw2CurrencyFormatter(
                getContext(),
                tradingItemData.getItemHistoryPrices().size());
        mTradingItemGraph.getGridLabelRenderer().setLabelFormatter(gwTradingItemFormatter);
        mTradingItemGraph.getGridLabelRenderer().setHorizontalLabelsColor(labelColor);
        mTradingItemGraph.getGridLabelRenderer().setVerticalLabelsColor(labelColor);

        LineGraphSeriesCreator graphCreator = new LineGraphSeriesCreator(tradingItemData);
        // the graph points for the seller prices
        LineGraphSeries<DataPoint> sellerSeries = graphCreator.createSellGraphSeries();
        sellerSeries.setColor(ResourcesCompat.getColor(getResources(), R.color.core_highlight_2,
                null));
        sellerSeries.setAnimated(true);
        sellerSeries.setTitle(getString(R.string.trading_items_sell));

        // the graph points for the buyer prices
        LineGraphSeries<DataPoint> buyerSeries = graphCreator.createBuyGraphSeries();
        buyerSeries.setColor(ResourcesCompat.getColor(getResources(),
                R.color.core_highlight_1,
                null));
        buyerSeries.setAnimated(true);
        buyerSeries.setTitle(getString(R.string.trading_items_buy));

        // all seller and buyer series to the graph
        mTradingItemGraph.addSeries(sellerSeries);
        mTradingItemGraph.addSeries(buyerSeries);
        // set min / max of the x- and y- axis
        adjustViewport(tradingItemData);
    }


    /**
     * Updates the details of the presented trading item
     *
     * @param tradingItemData the trading item data containing the history, the current price and
     *                        the item information
     */
    @Override
    public void updateItemDetails(@Nonnull final TradingItemData tradingItemData) {
        if (!isUpdateable()) {
            return;
        }
        Context context = requireNonNull(getContext());
        ActivityHelper.setTextOnTextView(getActivity(), R.id.trading_item_name,
                tradingItemData.getSelectedItem().getName());
        ActivityHelper.setTextOnTextView(getActivity(), R.id.trading_item_name,
                tradingItemData.getSelectedItem().getName());
        ActivityHelper.setTextOnTextView(getActivity(), R.id.trading_items_latest_update,
                tradingItemData.getLatestUpdate());
        ActivityHelper.setTextOnTextView(getActivity(), R.id.trading_item_sell_min_price,
                tradingItemData.getMinSellPriceFormatted(context));
        ActivityHelper.setTextOnTextView(getActivity(), R.id.trading_item_sell_max_price,
                tradingItemData.getMaxSellPriceFormatted(context));
        ActivityHelper.setTextOnTextView(getActivity(), R.id.trading_item_buy_min_price,
                tradingItemData.getMinBuyPriceFormatted(context));
        ActivityHelper.setTextOnTextView(getActivity(), R.id.trading_item_buy_max_price,
                tradingItemData.getMaxBuyPriceFormatted(context));

    }

    /**
     * In case no prices exists, a message will be presented to the user
     */
    @Override
    public void showNoItemPricesFound() {
        if (!isUpdateable()) {
            return;
        }
        String warning = getString(R.string.trading_items_data_count_warning);
        ActivityHelper.showMessageInDialog(requireNonNull(getActivity()), warning);
    }


    /**
     * Determines the min and max of X and Y coordinates to calculate the view port
     *
     * @param tradingItemData the trading item data containing the history, the current price and
     *                        the item information
     */
    private void adjustViewport(@Nonnull final TradingItemData tradingItemData) {
        // calculate min and max of X. Uses * 2 for the item prices to make sure that the
        // graph zooms correctly
        int minX = 0;
        int maxX = tradingItemData.getItemHistoryPrices().size() *
                Gw2CurrencyFormatter.ONE_GRID_LENGTH +
                tradingItemData.getItemPrices().size() * 2;

        // determines min and max of Y over all prices.
        // TODO remove if code below works
//        int maxY = 0;
//        int minY = Integer.MAX_VALUE;
//        for (ItemPriceHistoryEntity history : tradingItemData.getItemHistoryPrices()) {
//            minY = Math.min(history.getAvgStartSell(), minY);
//            minY = Math.min(history.getAvgEndSell(), minY);
//            maxY = Math.max(history.getAvgStartBuy(), maxY);
//            maxY = Math.max(history.getAvgEndBuy(), maxY);
//        }
//        for (ItemPriceEntity current : tradingItemData.getItemPrices()) {
//            minY = Math.min(current.getSellPrice(), minY);
//            maxY = Math.max(current.getBuyPrice(), maxY);
//        }

        // set the viewport
        mTradingItemGraph.getViewport().setMinX(minX);
        mTradingItemGraph.getViewport().setMaxX(maxX);
        mTradingItemGraph.getViewport().setMinY(tradingItemData.getMinSellPrice());
        mTradingItemGraph.getViewport().setMaxY(tradingItemData.getMaxBuyPrice());
        mTradingItemGraph.getViewport().calcCompleteRange();
    }


    /**
     * This method resets the screen (e.g. item graph, data) from previous searches
     */
    @Override
    public void resetScreen() {
        mTradingItemGraph.removeAllSeries();
    }


    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    protected void injectComponent(final ApplicationComponent applicationComponent) {
        TradingItemsModule module = new TradingItemsModule(this);
        DaggerTradingItemsComponent.builder()
                .applicationComponent(applicationComponent)
                .tradingItemsModule(module)
                .build()
                .inject(this);
    }


    /**
     * @return the resource id of the info / data container
     */
    @Override
    protected int getInfoContainerId() {
        return R.id.item_price_chart;
    }


    /**
     * @return the resource id of the error container
     */
    @Override
    protected int getErrorContainerId() {
        return R.id.trading_items_error_container;
    }


    /**
     * @return the resource id of the view
     */
    @Override
    protected int getGettingThingsDoneContainerId() {
        return R.id.trading_items_getting_things_done;
    }
}
