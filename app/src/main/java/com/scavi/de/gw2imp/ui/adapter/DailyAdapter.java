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
import com.scavi.de.gw2imp.data.entity.achievement.AchievementEntity;
import com.scavi.de.gw2imp.data.so.Daily;
import com.scavi.de.gw2imp.ui.util.AndroidVersionHelper;

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
            rowView = inflater.inflate(R.layout.account_daily_row, parent, false);
        }
        customizeView(currentItem, rowView);
        return rowView;
    }


    /**
     * Customizes the current row view with the daily information
     *
     * @param currentItem the daily information item
     * @param rowView     the current row view
     */
    @SuppressWarnings("unchecked")
    private void customizeView(@Nullable final Daily currentItem,
                               final View rowView) {
        if (currentItem == null) {
            return;
        }

        // doesn't contain a daily context (it is only for informational grouping)
        if (currentItem.isInfoData()) {
            rowView.findViewById(R.id.daily_header).setVisibility(View.VISIBLE);
            rowView.findViewById(R.id.daily_context_data).setVisibility(View.GONE);
            processDailyHeader(currentItem, rowView);
        }
        // it contains a daily context
        else {
            rowView.findViewById(R.id.daily_header).setVisibility(View.GONE);
            rowView.findViewById(R.id.daily_context_data).setVisibility(View.VISIBLE);
            processDailyContext(currentItem, rowView);
        }
    }


    /**
     * @param currentItem the daily information item
     * @param rowView     the current row view
     */
    private void processDailyHeader(final Daily currentItem,
                                    final View rowView) {
        TextView dailyRealmView = rowView.findViewById(R.id.daily_realm);
        dailyRealmView.setText(currentItem.getType());
    }


    /**
     * @param currentItem the daily information item
     * @param rowView     the current row view
     */
    private void processDailyContext(final Daily currentItem,
                                     final View rowView) {
        TextView dailyStepView = rowView.findViewById(R.id.daily_event);
        dailyStepView.setText(currentItem.getAchievementData().getName());

        TextView dailyProgressView = rowView.findViewById(R.id.daily_progress);
        dailyProgressView.setText(
                String.format(getContext().getString(R.string.core_account_daily_in_progress),
                        currentItem.getCurrent(),
                        currentItem.getMax()));

        // set style and text of the status view
        TextView statusView = rowView.findViewById(R.id.daily_status);
        String text;
        if (currentItem.isCompleted()) {
            AndroidVersionHelper.setTextAppearance(getContext(), statusView, R.style
                    .Gw2ImpTheme_Text_SuccessHighlight);
            text = getContext().getString(R.string.core_account_daily_completed);
        } else {
            AndroidVersionHelper.setTextAppearance(getContext(), statusView, R.style
                    .Gw2ImpTheme_Text_ErrorHighlight);
            text = getContext().getString(R.string.core_account_daily_uncompleted);
        }
        statusView.setText(text);
    }
}
