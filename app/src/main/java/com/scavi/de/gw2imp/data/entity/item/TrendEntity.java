package com.scavi.de.gw2imp.data.entity.item;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import com.google.common.base.MoreObjects;
import com.scavi.de.gw2imp.data.util.DbConst;
import com.scavi.de.gw2imp.data.util.TrendType;

@Entity(tableName = DbConst.TABLE_TRENDS,
        primaryKeys = {"id", "type"})
public class TrendEntity {
    @ColumnInfo(name = "id")
    private final int mId;
    @ColumnInfo(name = "type")
    private final int mType;
    @ColumnInfo(name = "percentage")
    private final double mPercentage;
    @ColumnInfo(name = "time")
    private final long mTime;


    /**
     * Constructor
     *
     * @param id         the ID of the entity. Depending on the type the ID might be the primary
     *                   key of an item or something else
     * @param type       the type to the current entity (e.g. items, or gems)
     * @param percentage the development of the price to the last selection
     */
    public TrendEntity(final int id,
                       final TrendType type,
                       final double percentage) {
        this(id, type.getType(), percentage, System.currentTimeMillis());
    }


    /**
     * Constructor
     *
     * @param id         the ID of the entity. Depending on the type the ID might be the primary
     *                   key of an item or something else
     * @param type       the type to the current entity (e.g. items, or gems)
     * @param percentage the development of the price to the last selection
     * @param time       the current timestamp / moment of the current price
     */
    public TrendEntity(final int id,
                       final int type,
                       final double percentage,
                       final long time) {
        mId = id;
        mType = type;
        mPercentage = percentage;
        mTime = time;
    }


    /**
     * @return the ID of the entity. Depending on the type the ID might be the primary key of an
     * item or something else
     */
    public int getId() {
        return mId;
    }


    /**
     * @return the type ID
     */
    public int getType() {
        return mType;
    }


    /**
     * @return the development of the price to the last selection
     */
    public double getPercentage() {
        return mPercentage;
    }


    /**
     * @return the current timestamp / moment of the current price
     */
    public long getTime() {
        return mTime;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(ItemSearchEntity.class.getName()).add("ID", mId)
                .add("Type", mType).add("Percentage", mPercentage).add("Time", mTime).toString();
    }
}
