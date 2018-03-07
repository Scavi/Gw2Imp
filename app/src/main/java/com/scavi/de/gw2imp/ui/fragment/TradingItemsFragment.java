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
import com.scavi.de.gw2imp.data.entity.item.ItemPriceEntity;
import com.scavi.de.gw2imp.data.entity.item.ItemPriceHistoryEntity;
import com.scavi.de.gw2imp.presenter.TradingItemsPresenter;
import com.scavi.de.gw2imp.ui.adapter.TradingItemAdapter;
import com.scavi.de.gw2imp.ui.graph.Gw2CurrencyFormatter;
import com.scavi.de.gw2imp.ui.view.ITradingItemsView;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class TradingItemsFragment extends AbstractStatusFragment implements ITradingItemsView,
        AdapterView.OnItemClickListener {

    @Inject
    TradingItemsPresenter mPresenter;
    private AutoCompleteTextView mSearchItemName;
    private GraphView mTradingItemGraph;

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
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        injectComponent(((IApplication) getActivity().getApplicationContext()).getComponent());
        View view = inflater.inflate(R.layout.fragment_trading_item, container, false);
        setupUiComponents(view);
        return view;
    }


    @Override
    protected void setupUiComponents(final View fragmentView) {
        super.setupUiComponents(fragmentView);
        mTradingItemGraph = fragmentView.findViewById(R.id.item_price_chart);
        mSearchItemName = fragmentView.findViewById(R.id.search_trading_item_name);
        mSearchItemName.setOnItemClickListener(this);
        mSearchItemName.addTextChangedListener(mPresenter.createTradingItemTextDelay());
    }


    @Override
    public void onItemClick(final AdapterView<?> adapterView,
                            final View view,
                            final int i,
                            final long l) {
        mPresenter.onItemSelected((ItemEntity)adapterView.getItemAtPosition(i));
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
        if (foundItems.size() == 0) {
            return;
        }

        Context context = getActivity() != null && getActivity().getApplicationContext() != null ?
                getActivity().getApplicationContext() : getContext();
        if (context == null) {
            return;
        }
        TradingItemAdapter adapter = new TradingItemAdapter(context,
                android.R.layout.simple_dropdown_item_1line,
                foundItems.toArray(new ItemEntity[foundItems.size()]));
        adapter.setNotifyOnChange(true);
        mSearchItemName.setAdapter(adapter);
    }


    /**
     * Updates the graph with the prices
     *
     * @param itemHistoryPrices the history prices
     * @param itemPrices        the current prices
     */
    @Override
    public void updateItemGraph(@Nonnull final List<ItemPriceHistoryEntity> itemHistoryPrices,
                                @Nonnull final List<ItemPriceEntity> itemPrices) {
        mTradingItemGraph.getLegendRenderer().setVisible(true);
        mTradingItemGraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        mTradingItemGraph.getLegendRenderer().setTextColor(
                ResourcesCompat.getColor(getResources(), R.color.core_title_text_color, null));
        mTradingItemGraph.getGridLabelRenderer().setLabelFormatter(
                new Gw2CurrencyFormatter(getContext()));
        mTradingItemGraph.getGridLabelRenderer().setHorizontalLabelsColor(
                ResourcesCompat.getColor(getResources(), R.color.core_background_color, null));
        mTradingItemGraph.getGridLabelRenderer().setVerticalLabelsColor(
                ResourcesCompat.getColor(getResources(), R.color.core_highlight_text_color, null));

        LineGraphSeries<DataPoint> sellerSeries = createGraphSeries(false,
                itemHistoryPrices,
                itemPrices);
        sellerSeries.setColor(ResourcesCompat.getColor(getResources(), R.color.core_highlight_2,
                null));
        sellerSeries.setAnimated(true);
        sellerSeries.setTitle(getString(R.string.trading_items_sell));
        LineGraphSeries<DataPoint> buyerSeries = createGraphSeries(true,
                itemHistoryPrices,
                itemPrices);
        buyerSeries.setColor(ResourcesCompat.getColor(getResources(), R.color.core_highlight_1,
                null));
        buyerSeries.setAnimated(true);
        buyerSeries.setTitle(getString(R.string.trading_items_buy));

        mTradingItemGraph.addSeries(sellerSeries);
        mTradingItemGraph.addSeries(buyerSeries);
    }


    private LineGraphSeries<DataPoint> createGraphSeries(
            final boolean isBuyPrice,
            @Nonnull final List<ItemPriceHistoryEntity> itemHistoryPrices,
            @Nonnull final List<ItemPriceEntity> itemPrices) {
        // x2 because history prices have a start and end date within the period
        DataPoint[] graphPoints = new DataPoint[(itemHistoryPrices.size() * 2) + itemPrices.size()];
        int pos = 0;
        double price;
        // average start & end price of the history prices
        for (ItemPriceHistoryEntity itemHistoryPrice : itemHistoryPrices) {
            // avg start price of the period
            price = isBuyPrice ? itemHistoryPrice.getAvgStartBuy() :
                    itemHistoryPrice.getAvgStartSell();
            graphPoints[pos] = new DataPoint(pos, price);
            pos++;
            // avg end price of the period
            price = isBuyPrice ? itemHistoryPrice.getAvgEndBuy() :
                    itemHistoryPrice.getAvgEndSell();
            graphPoints[pos] = new DataPoint(pos, price);
            pos++;
        }
        // item prices
        for (ItemPriceEntity itemPrice : itemPrices) {
            price = isBuyPrice ? itemPrice.getBuyPrice() :
                    itemPrice.getSellPrice();
            graphPoints[pos] = new DataPoint(pos, price);
            pos++;
        }
        return new LineGraphSeries<>(graphPoints);
    }


    /**
     * This method resets the item graph from previous searches
     */
    public void resetItemGraph() {
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
