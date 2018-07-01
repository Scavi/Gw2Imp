package com.scavi.de.gw2imp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.data.so.Trend;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class TrendAdapter extends ArrayAdapter<Trend> {

    /**
     * Constructor
     *
     * @param context  the context to global information about the application environment
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param entries  the entries for the adapter
     */
    public TrendAdapter(final Context context,
                        final int resource,
                        final Trend[] entries) {
        super(context, resource, entries);
    }


    /**
     * @see ArrayAdapter#getView(int, View, ViewGroup)
     */
    @Override
    @NonNull
    public View getView(final int position,
                        @Nullable final View convertView,
                        @NonNull final ViewGroup parent) {
        Trend currentItem = getItem(position);
        View rowView = convertView;
        // inflate the rowView if necessary
        if (rowView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.account_trend_row, parent, false);
        }
        customizeView(currentItem, rowView);
        return rowView;
    }


    private void customizeView(@Nullable final Trend currentItem,
                               final View rowView) {
        if (currentItem == null) {
            return;
        }
        if (currentItem.isHeader()) {
            customizeHeaderView(currentItem, rowView);
        } else {
            customizeTrendView(currentItem, rowView);
        }
    }

    private void customizeHeaderView(final Trend currentItem,
                                     final View rowView) {
        // set the visibility of the views
        rowView.findViewById(R.id.trend_context_data).setVisibility(View.VISIBLE);
        rowView.findViewById(R.id.trend_context_data).setVisibility(View.GONE);

        TextView trendingHeader = rowView.findViewById(R.id.trending_header);
        trendingHeader.setText(currentItem.getHeaderName());
    }

    private void customizeTrendView(final Trend currentItem,
                                    final View rowView) {
        // set the visibility of the views
        rowView.findViewById(R.id.trend_context_data).setVisibility(View.GONE);
        rowView.findViewById(R.id.trend_context_data).setVisibility(View.VISIBLE);
    }
}
