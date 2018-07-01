package com.scavi.de.gw2imp.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scavi.de.gw2imp.data.entity.item.TrendEntity;
import com.scavi.de.gw2imp.data.util.DbConst;

import java.util.List;

import javax.annotation.Nonnull;

@Dao
public interface ITrendDAO {
    /**
     * Inserts the given trend into the table. If the trend already exists it will be replaced
     *
     * @param trend the trend to add
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTrend(final TrendEntity trend);


    /**
     * Selects the top increasing item trends
     *
     * @param type the type to select
     * @return the top increasing item trends
     */
    @Query("SELECT * FROM " + DbConst.TABLE_TRENDS + " WHERE type = :type ORDER BY percentage ASC" +
            " LIMIT 10")
    List<TrendEntity> selectIncreasingItemTrends(final int type);


    /**
     * Selects the top decreasing item trends
     *
     * @param type the type to select
     * @return the top decreasing item trends
     */
    @Query("SELECT * FROM " + DbConst.TABLE_TRENDS + " WHERE type = :type ORDER BY percentage" +
            " DESC LIMIT 10")
    List<TrendEntity> selectDecreasingItemTrends(final int type);


}
