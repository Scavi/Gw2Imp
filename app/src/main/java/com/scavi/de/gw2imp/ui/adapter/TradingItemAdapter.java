package com.scavi.de.gw2imp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.scavi.de.gw2imp.data.entity.item.ItemEntity;

public class TradingItemAdapter extends ArrayAdapter<ItemEntity> {
    private final ItemEntity[] _entries;

    /**
     * Constructor
     *
     * @param context  the context to global information about the application environment
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param entries  the entries for the adapter
     */
    public TradingItemAdapter(Context context,
                              int resource,
                              final ItemEntity[] entries) {
        super(context, resource, entries);
        _entries = entries;
    }


    /**
     * @see ArrayAdapter#getView(int, View, ViewGroup)
     */
    @Override
    @NonNull
    public View getView(final int position,
                        @Nullable final View convertView,
                        @NonNull final ViewGroup parent) {


        ItemEntity currentItem = getItem(position);
        View rowView = convertView;
        // inflate the rowView if necessary
        if (rowView == null) {
            final LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        }
        TextView textView = rowView.findViewById(android.R.id.text1);
        textView.setText(currentItem.getName());
        return rowView;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(final CharSequence charSequence) {
                FilterResults results = new FilterResults();
                results.values = ItemEntity.toNameList(_entries);
                return results;
            }

            @Override
            protected void publishResults(final CharSequence charSequence,
                                          final FilterResults filterResults) {
                notifyDataSetChanged();
            }
        };
    }
}
