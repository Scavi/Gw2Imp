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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.jjoe64.graphview.DefaultLabelFormatter;
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
import com.scavi.de.gw2imp.presenter.TradingItemsPresenter;
import com.scavi.de.gw2imp.ui.util.DelayedTextFieldWatcher;
import com.scavi.de.gw2imp.ui.view.ITradingItemsView;

import java.util.List;
import java.util.TimerTask;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class TradingItemsFragment extends AbstractStatusFragment implements ITradingItemsView {
    @Inject
    TradingItemsPresenter mPresenter;
    private EditText mSearchItemName;
    private Spinner mTradingItemsToName;
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
        mTradingItemsToName = fragmentView.findViewById(R.id.found_trading_items);
        mSearchItemName = fragmentView.findViewById(R.id.search_trading_item_name);
        mSearchItemName.addTextChangedListener(mPresenter.createTradingItemTextDelay());
    }


    @Override
    public String getItemSearchName() {
        return mSearchItemName.getText().toString();
    }

    @Override
    public void updateFoundItems(@Nonnull final List<ItemEntity> foundItems) {
        mTradingItemGraph.getLegendRenderer().setVisible(true);
        mTradingItemGraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        mTradingItemGraph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value,
                                      boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + " €";
                }
            }
        });
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 2.4),
                new DataPoint(1, 3.4),
                new DataPoint(2, 0.4),
                new DataPoint(3, 1.2),
                new DataPoint(4, 2.6),
                new DataPoint(5, 1.0),
                new DataPoint(6, 3.5),
                new DataPoint(7, 2.4),
                new DataPoint(8, 13.4),
                new DataPoint(9, 0.9),
        });
        series1.setColor(0xFF0000FF);
        series1.setAnimated(true);
        series1.setTitle("foo");
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 2.2),
                new DataPoint(1, 3.2),
                new DataPoint(2, 0.35),
                new DataPoint(3, 1.1),
                new DataPoint(4, 2.3),
                new DataPoint(5, 1.0),
                new DataPoint(6, 3.1),
                new DataPoint(7, 2.3),
                new DataPoint(8, 13.1),
                new DataPoint(9, 0.7),
        });
        series2.setColor(0xFF00FFFF);
        series2.setAnimated(true);
        series2.setTitle("bar");
        mTradingItemGraph.addSeries(series1);
        mTradingItemGraph.addSeries(series2);
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
        return R.id.trading_items_information;
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
