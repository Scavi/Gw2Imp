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
 *
 */
package com.scavi.de.gw2imp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.data.entity.event.WorldBossEntity;
import com.scavi.de.gw2imp.ui.util.Gw2DataMapper;

public class WorldBossesAdapter extends ArrayAdapter<WorldBossEntity> {

    /**
     * Constructor
     *
     * @param context  the context to global information about the application environment
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param entries  the entries for the adapter
     */
    public WorldBossesAdapter(final Context context,
                              final int resource,
                              final WorldBossEntity[] entries) {
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
        WorldBossEntity currentItem = getItem(position);
        View rowView = convertView;
        // inflate the rowView if necessary
        if (rowView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.world_boss_row, parent, false);
        }
        customizeView(currentItem, rowView);
        return rowView;
    }


    /**
     * @param currentItem the world boss information item
     * @param rowView     the current row view
     */
    private void customizeView(final WorldBossEntity currentItem,
                               @Nullable final View rowView) {
        if (rowView == null) {
            return;
        }
        ImageView worldBossPicture = rowView.findViewById(R.id.world_boss_location_picture);
        int drawableId = Gw2DataMapper.determineWorldBossDrawableId(currentItem);
        worldBossPicture.setImageDrawable(getContext().getDrawable(drawableId));

        TextView statusView = rowView.findViewById(R.id.world_boss_status);
        statusView.setText(createStatusString(currentItem));

        TextView reward = rowView.findViewById(R.id.world_boss_rewards);
        reward.setText(createRewardString(currentItem));

        TextView worldBossView = rowView.findViewById(R.id.world_boss_name);
        String boss = Gw2DataMapper.determineWorldBoss(getContext(), currentItem);
        worldBossView.setText(boss);
    }


    /**
     * Creates the status string of the world boss (currently start hour and minutes)
     *
     * @param currentItem the world boss information item
     * @return the status string
     */
    private String createStatusString(final WorldBossEntity currentItem) {
        int startHour = (currentItem.getStartMinutes() / 60) % 24;
        int startMinutes = currentItem.getStartMinutes() % 60;
        return String.format(getContext().getString(R.string.world_boss_status_pattern),
                startHour, startMinutes);
    }


    /**
     * Creates the treasure string of the world boss (currently start hour and minutes)
     *
     * @param currentItem the world boss information item
     * @return the status string
     */
    private String createRewardString(final WorldBossEntity currentItem) {
        String items = String.format(currentItem.getRareItems() == 1 ?
                        getContext().getString(R.string.world_boss_reward_item_pattern) :
                        getContext().getString(R.string.world_boss_reward_items_pattern),
                currentItem.getRareItems());
        String chests = String.format(currentItem.getBonusChests() == 1 ?
                        getContext().getString(R.string.world_boss_chest_pattern) :
                        getContext().getString(R.string.world_boss_chests_pattern),
                currentItem.getBonusChests());
        return String.format(getContext().getString(R.string.world_boss_reward_pattern),
                chests, items);
    }
}
