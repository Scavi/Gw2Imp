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
package com.scavi.de.gw2imp.data.entity.item;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.scavi.de.gw2imp.communication.response.items.Item;
import com.scavi.de.gw2imp.data.util.DbConst;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

@Entity(tableName = DbConst.TABLE_ITEMS)
@ParametersAreNonnullByDefault
public class ItemEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final int mItemId;
    @NonNull
    @ColumnInfo(name = "name")
    private final String mName;
    @NonNull
    @ColumnInfo(name = "url")
    private final String mIconUrl;
    @ColumnInfo(name = "icon")
    private byte[] mIcon;


    /**
     * Constructor
     *
     * @param item the loaded item that represents the item entity
     */
    public ItemEntity(final Item item) {
        this(item.getId(), item.getName(), item.getIcon());
    }


    /**
     * Constructor
     *
     * @param itemId  the unique ID of the item
     * @param name    the name of the item
     * @param iconUrl the URL to the icon
     */
    public ItemEntity(final int itemId,
                      @NonNull final String name,
                      @NonNull final String iconUrl) {
        mItemId = itemId;
        mName = name;
        mIconUrl = iconUrl;
    }


    /**
     * @return the icon of the item
     */
    @NonNull
    public byte[] getIcon() {
        return mIcon;
    }


    /**
     * @param icon the icon of the item
     */
    public void setIcon(final byte[] icon) {
        mIcon = icon;
    }


    /**
     * @return the unique ID of the item
     */
    public int getItemId() {
        return mItemId;
    }


    /**
     * @return the name of the item
     */
    @NonNull
    public String getName() {
        return mName;
    }


    /**
     * @return the URL to the icon
     */
    @NonNull
    public String getIconUrl() {
        return mIconUrl;
    }


    @Override
    public String toString() {
        return mName;
    }


    public static List<String> toNameList(final ItemEntity[] items) {
        List<String> names = new ArrayList<>(items.length);
        for (ItemEntity item : items) {
            names.add(item.getName());
        }
        return names;
    }
}
