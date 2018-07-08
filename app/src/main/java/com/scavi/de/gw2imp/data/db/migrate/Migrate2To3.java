package com.scavi.de.gw2imp.data.db.migrate;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.scavi.de.gw2imp.data.entity.item.ItemSearchEntity;
import com.scavi.de.gw2imp.data.entity.item.TrendEntity;
import com.scavi.de.gw2imp.data.util.DbConst;

/**
 * Migrates from version 2 to version 3 of the database. The main changes are:
 * - Add the table Trend {@link TrendEntity} which allows to register trend items (e.g. seller /
 * buyer prices)
 * - Add the table ItemSearch {@link ItemSearchEntity} which optimizes the item search
 */
public class Migrate2To3 extends Migration {

    /**
     * Constructor
     * <p>
     * Migrate from 2 to 3
     */
    public Migrate2To3() {
        super(2, 3);
    }

    @Override
    public void migrate(@NonNull final SupportSQLiteDatabase database) {
        // creates the table item search part
        database.execSQL("CREATE TABLE IF NOT EXISTS " + DbConst.TABLE_ITEM_PART_SEARCH + " (id " +
                "INTEGER NOT NULL, namePart TEXT NOT NULL, PRIMARY KEY(id, namePart))");
        // creates the table trends
        database.execSQL("CREATE TABLE IF NOT EXISTS " + DbConst.TABLE_TRENDS + " (id INTEGER NOT" +
                " NULL, type INTEGER NOT NULL, percentage REAL NOT NULL, time INTEGER NOT NULL, " +
                "PRIMARY KEY(id, type))");
    }
}