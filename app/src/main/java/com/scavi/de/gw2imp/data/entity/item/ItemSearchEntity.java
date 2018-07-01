package com.scavi.de.gw2imp.data.entity.item;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import com.google.common.base.MoreObjects;
import com.scavi.de.gw2imp.data.util.DbConst;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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


    /**
     * Creates a list of {@link ItemSearchEntity} for all the found words with the ID of the
     * <code>searchItem</code>
     *
     * @param searchItem the search item
     * @param newWords   all words for the search item
     * @return the item search list
     */
    @NonNull
    public static List<ItemSearchEntity> from(@NonNull final ItemSearchEntity searchItem,
                                              @NonNull final Set<String> newWords) {
        List<ItemSearchEntity> newEntries = new ArrayList<>(newWords.size());
        for (String word : newWords) {
            ItemSearchEntity newEntry = new ItemSearchEntity(searchItem.getId(), word);
            newEntries.add(newEntry);
        }
        return newEntries;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper("ItemSearchEntity").add("ID", mId)
                .add("Part", mNamePart).toString();
    }
}
