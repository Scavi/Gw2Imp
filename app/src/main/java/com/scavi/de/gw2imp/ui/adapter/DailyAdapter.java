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
package com.scavi.de.gw2imp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.data.so.Daily;

public class DailyAdapter extends ArrayAdapter<Daily> {

    /**
     * Constructor
     *
     * @param context  the context to global information about the application environment
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param entries  the entries for the adapter
     */
    public DailyAdapter(final Context context,
                        final int resource,
                        final Daily[] entries) {
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
        Daily currentItem = getItem(position);
        View rowView = convertView;
        // inflate the rowView if necessary
        if (rowView == null) {
            final LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.account_raid_row, parent, false);
        }
        //customizeView(currentItem, rowView);
        return rowView;
    }
}
