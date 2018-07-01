package com.scavi.de.gw2imp.data.entity.item;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import com.google.common.base.MoreObjects;
import com.scavi.de.gw2imp.data.util.DbConst;

import java.util.List;


@Entity(tableName = DbConst.TABLE_ITEM_PART_SEARCH,
        primaryKeys = {"id", "namePart"})
public class ItemSearchEntity {
    @ColumnInfo(name = "id")
    private final int mId;
    @NonNull
    @ColumnInfo(name = "namePart")
    private final String mNamePart;


    /**
     * Constructor
     *
     * @param id       the unique id of the item
     * @param namePart a part of the name (e.g. "Eisenbarren" -> has two parts, "Eisen" and
     *                 "barren")
     */
    public ItemSearchEntity(final int id,
                            @NonNull final String namePart) {
        mId = id;
        mNamePart = namePart;
    }


    /**
     * @return the unique id of the item
     */
    public int getId() {
        return mId;
    }


    /**
     * @return a part of the name (e.g. "Eisenbarren" -> has two parts, "Eisen" and
     * "barren")
     */
    public String getNamePart() {
        return mNamePart;
    }


    /**
     * Create an int array of all IDs of the given list
     *
     * @param itemSearchEntities all search entities
     * @return the array with all ids
     */
    public static int[] allIdsFrom(final List<ItemSearchEntity> itemSearchEntities) {
        if (itemSearchEntities == null || itemSearchEntities.size() == 0) {
            return new int[0];
        }
        int[] allIds = new int[itemSearchEntities.size()];
        int i = 0;
        for (ItemSearchEntity itemSearch : itemSearchEntities) {
            allIds[i++] = itemSearch.getId();
        }
        return allIds;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper("ItemSearchEntity").add("ID", mId)
                .add("Part", mNamePart).toString();
    }
}
