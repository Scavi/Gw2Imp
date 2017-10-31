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
import android.widget.TextView;

import com.google.common.base.Strings;
import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.data.entity.raid.RaidEntity;
import com.scavi.de.gw2imp.ui.util.AndroidVersionHelper;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class RaidAdapter extends ArrayAdapter<RaidEntity> {

    /**
     * Constructor
     *
     * @param context  the context to global information about the application environment
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param entries  the entries for the adapter
     */
    public RaidAdapter(final Context context,
                       final int resource,
                       final RaidEntity[] entries) {
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
        RaidEntity currentItem = getItem(position);
        View rowView = convertView;
        // inflate the rowView if necessary
        if (rowView == null) {
            final LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.account_raid_row, parent, false);
        }
        customizeView(currentItem, rowView);
        return rowView;
    }


    /**
     * Customizes the current row view with the raid information
     *
     * @param currentItem the raid information item
     * @param rowView     the current row view
     */
    @SuppressWarnings("unchecked")
    private void customizeView(@Nullable final RaidEntity currentItem,
                               final View rowView) {
        if (currentItem == null) {
            return;
        }

        // doesn't contain a raid context (it is only for informational grouping)
        if (Strings.isNullOrEmpty(currentItem.getRaidContext())) {
            rowView.findViewById(R.id.raid_header).setVisibility(View.VISIBLE);
            rowView.findViewById(R.id.raid_context_data).setVisibility(View.GONE);
            processRaidHeader(currentItem, rowView);
        }
        // it contains a raid context
        else {
            rowView.findViewById(R.id.raid_header).setVisibility(View.GONE);
            rowView.findViewById(R.id.raid_context_data).setVisibility(View.VISIBLE);
            processRaidContext(currentItem, rowView);
        }
    }


    /**
     * @param currentItem the raid information item
     * @param rowView     the current row view
     */
    private void processRaidHeader(final RaidEntity currentItem,
                                   final View rowView) {
        TextView raidRealmView = rowView.findViewById(R.id.raid_realm);
        raidRealmView.setText(currentItem.getRaid());
    }

    /**
     * @param currentItem the raid information item
     * @param rowView     the current row view
     */
    private void processRaidContext(final RaidEntity currentItem,
                                    final View rowView) {
        TextView raidStepView = rowView.findViewById(R.id.raid_boss);
        raidStepView.setText(currentItem.getRaidContext());

        // set style and text of the status view
        TextView statusView = rowView.findViewById(R.id.raid_status);
        String text;
        if (currentItem.isCompleted()) {
            AndroidVersionHelper.setTextAppearance(getContext(), statusView, R.style
                    .Gw2ImpTheme_Text_SuccessHighlight);
            text = getContext().getString(R.string.core_account_raid_completed);
        } else {
            AndroidVersionHelper.setTextAppearance(getContext(), statusView, R.style
                    .Gw2ImpTheme_Text_ErrorHighlight);
            text = getContext().getString(R.string.core_account_raid_uncompleted);
        }
        statusView.setText(text);
    }
}
