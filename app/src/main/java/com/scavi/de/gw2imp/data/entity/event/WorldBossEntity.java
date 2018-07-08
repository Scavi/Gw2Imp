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
package com.scavi.de.gw2imp.data.entity.event;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.data.util.DbConst;
import com.scavi.de.gw2imp.data.util.WorldBossType;

import java.util.Arrays;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.scavi.de.gw2imp.data.util.WorldBossType.*;

@Entity(tableName = DbConst.TABLE_WORLD_BOSSES)
@ParametersAreNonnullByDefault
public class WorldBossEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;
    @ColumnInfo(name = "startMinutes")
    private int mStartMinutes;
    @ColumnInfo(name = "duration")
    private int mDuration;
    @ColumnInfo(name = "warmUp")
    private int mWarmUp;
    @ColumnInfo(name = "typeId")
    private int mTypeId;
    @ColumnInfo(name = "bonusChests")
    private short mBonusChests;
    @ColumnInfo(name = "rareItems")
    private short mRareItems;

    /**
     * Constructor
     *
     * @param id           the id of the current world boss. The id is only required as primary
     *                     key in case
     *                     some world boss start timer will change
     * @param startMinutes the start minutes of the event, starting at 0:00 (e.g. 600 for 10:00 am)
     * @param duration     the duration of the event
     * @param warmUp       some events might have a warm up phase
     * @param bonusChests  the amount of bonus chests for this world boss
     * @param rareItems    the amount of rare items for this world boss
     * @param type         the type of the boss
     */
    public WorldBossEntity(final int id,
                           final int startMinutes,
                           final int duration,
                           final int warmUp,
                           final short bonusChests,
                           final short rareItems,
                           final WorldBossType type) {
        this(id, startMinutes, duration, warmUp, bonusChests, rareItems, type.getTypeId());
    }


    /**
     * Constructor
     *
     * @param id           the id of the current world boss. The id is only required as primary
     *                     key in case
     *                     some world boss start timer will change
     * @param startMinutes the start minutes of the event, starting at 0:00 (e.g. 600 for 10:00 am)
     * @param duration     the duration of the event
     * @param warmUp       some events might have a warm up phase
     * @param bonusChests  the amount of bonus chests for this world boss
     * @param rareItems    the amount of rare items for this world boss
     * @param typeId       the type id of the boss
     */
    public WorldBossEntity(final int id,
                           final int startMinutes,
                           final int duration,
                           final int warmUp,
                           final short bonusChests,
                           final short rareItems,
                           final int typeId) {
        mId = id;
        mStartMinutes = startMinutes;
        mDuration = duration;
        mWarmUp = warmUp;
        mBonusChests = bonusChests;
        mRareItems = rareItems;
        mTypeId = typeId;
    }

    public void changeToDailightSaving() {
        mStartMinutes -= 60;
    }

    /**
     * @return the id of the current world boss. The id is only required as primary key in case
     * some world boss start timer will change
     */
    public int getId() {
        return mId;
    }


    /**
     * @return the start minutes of the event, starting at 0:00 (e.g. 600 for 10:00 am)
     */
    public int getStartMinutes() {
        return mStartMinutes;
    }


    /**
     * @return some events might have a warm up phase
     */
    public int getWarmUp() {
        return mWarmUp;
    }


    /**
     * @return the duration of the event
     */
    public int getDuration() {
        return mDuration;
    }


    /**
     * @return the type id of the boss
     */
    public int getTypeId() {
        return mTypeId;
    }


    /**
     * @return the amount of bonus chests for this world boss
     */
    public short getBonusChests() {
        return mBonusChests;
    }

    /**
     * @return the amount of rare items for this world boss
     */
    public short getRareItems() {
        return mRareItems;
    }

    /**
     * @return the world boss type
     */
    public WorldBossType getType() {
        return WorldBossType.from(getTypeId());
    }


    /**
     * This method creates an array of all world bosses with the defined timer.
     * The method is a big W T F - unfortunately because of all the hard coded information.
     * Unfortunately the v1 api of GW2 is deprecated due to the megaserver concept. Additionally
     * the world bosses don't spawn in a specified period (e.g. karka queen has sometiems 3 and
     * sometimes 5 hours before it spawns again). Due to this I chose this ugly way of creating
     * the information
     * <p>
     * TODO: add duration and warmUp times.
     *
     * @return all world boss information for the event timer
     */
    public static List<WorldBossEntity> createWorldBossEventTimer() {

        WorldBossEntity[] allTimer = new WorldBossEntity[]{
                // first event of the day 00:00
                new WorldBossEntity(1000, 0, 0, 0, (short) 2, (short) 2, KarkaQueen),
                new WorldBossEntity(1100, 0, 0, 0, (short) 2, (short) 1, GolemMarkTwo),
                new WorldBossEntity(1200, 15, 0, 0, (short) 0, (short) 1, JungleWorm),
                new WorldBossEntity(1300, 30, 0, 0, (short) 2, (short) 1, ClawOfJormag),
                new WorldBossEntity(1400, 45, 0, 0, (short) 0, (short) 1, ShadowBehemoth),
                new WorldBossEntity(1500, 60, 0, 0, (short) 0, (short) 1, TaidhaCovington),
                new WorldBossEntity(1600, 60, 0, 0, (short) 2, (short) 2, Tequatl),
                new WorldBossEntity(1700, 75, 0, 0, (short) 0, (short) 1, FrozenMaw),
                new WorldBossEntity(1800, 90, 0, 0, (short) 0, (short) 1, MegaDestroyer),
                new WorldBossEntity(1900, 105, 0, 0, (short) 2, (short) 1, FireElemental),
                new WorldBossEntity(2000, 120, 0, 0, (short) 2, (short) 2, EvolvedJungleWorm),
                new WorldBossEntity(2100, 120, 0, 0, (short) 0, (short) 1, Shatterer),
                new WorldBossEntity(1201, 135, 0, 0, (short) 0, (short) 1, JungleWorm),
                new WorldBossEntity(2200, 150, 0, 0, (short) 2, (short) 1, ModnirUlgoth),
                new WorldBossEntity(1401, 165, 0, 0, (short) 0, (short) 1, ShadowBehemoth),
                new WorldBossEntity(1001, 180, 0, 0, (short) 2, (short) 2, KarkaQueen),
                new WorldBossEntity(1101, 180, 0, 0, (short) 2, (short) 1, GolemMarkTwo),
                new WorldBossEntity(1701, 195, 0, 0, (short) 0, (short) 1, FrozenMaw),
                new WorldBossEntity(1301, 210, 0, 0, (short) 2, (short) 1, ClawOfJormag),
                new WorldBossEntity(1901, 225, 0, 0, (short) 2, (short) 1, FireElemental),
                new WorldBossEntity(1601, 240, 0, 0, (short) 2, (short) 2, Tequatl),
                new WorldBossEntity(1501, 240, 0, 0, (short) 0, (short) 1, TaidhaCovington),
                new WorldBossEntity(1202, 255, 0, 0, (short) 0, (short) 1, JungleWorm),
                new WorldBossEntity(1801, 270, 0, 0, (short) 0, (short) 1, MegaDestroyer),
                new WorldBossEntity(1402, 285, 0, 0, (short) 0, (short) 1, ShadowBehemoth),
                new WorldBossEntity(2101, 300, 0, 0, (short) 0, (short) 1, Shatterer),
                new WorldBossEntity(2001, 300, 0, 0, (short) 2, (short) 2, EvolvedJungleWorm),
                new WorldBossEntity(1702, 315, 0, 0, (short) 0, (short) 1, FrozenMaw),
                new WorldBossEntity(2201, 330, 0, 0, (short) 2, (short) 1, ModnirUlgoth),
                new WorldBossEntity(1902, 345, 0, 0, (short) 2, (short) 1, FireElemental),
                new WorldBossEntity(1102, 360, 0, 0, (short) 2, (short) 1, GolemMarkTwo),
                new WorldBossEntity(1203, 375, 0, 0, (short) 0, (short) 1, JungleWorm),
                new WorldBossEntity(1302, 390, 0, 0, (short) 2, (short) 1, ClawOfJormag),
                new WorldBossEntity(1403, 405, 0, 0, (short) 0, (short) 1, ShadowBehemoth),
                new WorldBossEntity(1502, 420, 0, 0, (short) 0, (short) 1, TaidhaCovington),
                new WorldBossEntity(1002, 420, 0, 0, (short) 2, (short) 2, KarkaQueen),
                new WorldBossEntity(1703, 435, 0, 0, (short) 0, (short) 1, FrozenMaw),
                new WorldBossEntity(1802, 450, 0, 0, (short) 0, (short) 1, MegaDestroyer),
                new WorldBossEntity(1903, 465, 0, 0, (short) 2, (short) 1, FireElemental),
                new WorldBossEntity(1602, 480, 0, 0, (short) 2, (short) 2, Tequatl),
                new WorldBossEntity(2102, 480, 0, 0, (short) 0, (short) 1, Shatterer),
                new WorldBossEntity(1204, 495, 0, 0, (short) 0, (short) 1, JungleWorm),
                new WorldBossEntity(2202, 510, 0, 0, (short) 2, (short) 1, ModnirUlgoth),
                new WorldBossEntity(1404, 525, 0, 0, (short) 0, (short) 1, ShadowBehemoth),
                new WorldBossEntity(1103, 540, 0, 0, (short) 2, (short) 1, GolemMarkTwo),
                new WorldBossEntity(2002, 540, 0, 0, (short) 2, (short) 2, EvolvedJungleWorm),
                new WorldBossEntity(1704, 555, 0, 0, (short) 0, (short) 1, FrozenMaw),
                new WorldBossEntity(1303, 570, 0, 0, (short) 2, (short) 1, ClawOfJormag),
                new WorldBossEntity(1904, 585, 0, 0, (short) 2, (short) 1, FireElemental),
                new WorldBossEntity(1502, 600, 0, 0, (short) 0, (short) 1, TaidhaCovington),  //
                // 10:00
                new WorldBossEntity(1205, 615, 0, 0, (short) 0, (short) 1, JungleWorm),
                new WorldBossEntity(1802, 630, 0, 0, (short) 0, (short) 1, MegaDestroyer),
                new WorldBossEntity(1405, 645, 0, 0, (short) 0, (short) 1, ShadowBehemoth),
                new WorldBossEntity(2103, 660, 0, 0, (short) 0, (short) 1, Shatterer),
                new WorldBossEntity(1705, 675, 0, 0, (short) 0, (short) 1, FrozenMaw),
                new WorldBossEntity(1003, 690, 0, 0, (short) 2, (short) 2, KarkaQueen),
                new WorldBossEntity(2203, 690, 0, 0, (short) 2, (short) 1, ModnirUlgoth),
                new WorldBossEntity(1905, 705, 0, 0, (short) 2, (short) 1, FireElemental),
                new WorldBossEntity(1104, 720, 0, 0, (short) 2, (short) 1, GolemMarkTwo),
                new WorldBossEntity(1206, 735, 0, 0, (short) 0, (short) 1, JungleWorm),
                new WorldBossEntity(1603, 750, 0, 0, (short) 2, (short) 2, Tequatl),
                new WorldBossEntity(1304, 750, 0, 0, (short) 2, (short) 1, ClawOfJormag),
                new WorldBossEntity(1411, 765, 0, 0, (short) 0, (short) 1, ShadowBehemoth),
                new WorldBossEntity(1503, 780, 0, 0, (short) 0, (short) 1, TaidhaCovington),
                new WorldBossEntity(1706, 795, 0, 0, (short) 0, (short) 1, FrozenMaw),
                new WorldBossEntity(2005, 810, 0, 0, (short) 2, (short) 2, EvolvedJungleWorm),
                new WorldBossEntity(1803, 810, 0, 0, (short) 0, (short) 1, MegaDestroyer),
                new WorldBossEntity(1906, 825, 0, 0, (short) 2, (short) 1, FireElemental),
                new WorldBossEntity(2104, 840, 0, 0, (short) 0, (short) 1, Shatterer),
                new WorldBossEntity(1207, 855, 0, 0, (short) 0, (short) 1, JungleWorm),
                new WorldBossEntity(2204, 870, 0, 0, (short) 2, (short) 1, ModnirUlgoth),
                new WorldBossEntity(1406, 885, 0, 0, (short) 0, (short) 1, ShadowBehemoth),
                new WorldBossEntity(1105, 900, 0, 0, (short) 2, (short) 1, GolemMarkTwo),// 15:00
                new WorldBossEntity(1707, 915, 0, 0, (short) 0, (short) 1, FrozenMaw),
                new WorldBossEntity(1305, 930, 0, 0, (short) 2, (short) 1, ClawOfJormag),
                new WorldBossEntity(1907, 945, 0, 0, (short) 2, (short) 1, FireElemental),
                new WorldBossEntity(1504, 960, 0, 0, (short) 0, (short) 1, TaidhaCovington),
                new WorldBossEntity(1004, 960, 0, 0, (short) 2, (short) 2, KarkaQueen),
                new WorldBossEntity(1208, 975, 0, 0, (short) 0, (short) 1, JungleWorm),
                new WorldBossEntity(1804, 990, 0, 0, (short) 0, (short) 1, MegaDestroyer),
                new WorldBossEntity(1407, 1005, 0, 0, (short) 0, (short) 1, ShadowBehemoth),
                new WorldBossEntity(1604, 1020, 0, 0, (short) 2, (short) 2, Tequatl),
                new WorldBossEntity(2105, 1020, 0, 0, (short) 0, (short) 1, Shatterer),
                new WorldBossEntity(1708, 1035, 0, 0, (short) 0, (short) 1, FrozenMaw),
                new WorldBossEntity(2205, 1050, 0, 0, (short) 2, (short) 1, ModnirUlgoth),
                new WorldBossEntity(1908, 1065, 0, 0, (short) 2, (short) 1, FireElemental),
                new WorldBossEntity(2003, 1080, 0, 0, (short) 2, (short) 2, EvolvedJungleWorm),
                new WorldBossEntity(1106, 1080, 0, 0, (short) 2, (short) 1, GolemMarkTwo),
                new WorldBossEntity(1209, 1095, 0, 0, (short) 0, (short) 1, JungleWorm),
                new WorldBossEntity(1306, 1110, 0, 0, (short) 2, (short) 1, ClawOfJormag),
                new WorldBossEntity(1408, 1125, 0, 0, (short) 0, (short) 1, ShadowBehemoth),
                new WorldBossEntity(1005, 1140, 0, 0, (short) 2, (short) 2, KarkaQueen),
                new WorldBossEntity(1505, 1140, 0, 0, (short) 0, (short) 1, TaidhaCovington),
                new WorldBossEntity(1709, 1155, 0, 0, (short) 0, (short) 1, FrozenMaw),
                new WorldBossEntity(1805, 1170, 0, 0, (short) 0, (short) 1, MegaDestroyer),
                new WorldBossEntity(1909, 1185, 0, 0, (short) 2, (short) 1, FireElemental),
                new WorldBossEntity(1605, 1200, 0, 0, (short) 2, (short) 2, Tequatl), // 20:00
                new WorldBossEntity(2106, 1200, 0, 0, (short) 0, (short) 1, Shatterer),
                new WorldBossEntity(1210, 1215, 0, 0, (short) 0, (short) 1, JungleWorm),
                new WorldBossEntity(2206, 1230, 0, 0, (short) 2, (short) 1, ModnirUlgoth),
                new WorldBossEntity(1409, 1245, 0, 0, (short) 0, (short) 1, ShadowBehemoth),
                new WorldBossEntity(1107, 1260, 0, 0, (short) 2, (short) 1, GolemMarkTwo),
                new WorldBossEntity(2004, 1260, 0, 0, (short) 2, (short) 2, EvolvedJungleWorm),
                new WorldBossEntity(1710, 1275, 0, 0, (short) 0, (short) 1, FrozenMaw),
                new WorldBossEntity(1307, 1290, 0, 0, (short) 2, (short) 1, ClawOfJormag),
                new WorldBossEntity(1910, 1305, 0, 0, (short) 2, (short) 1, FireElemental),
                new WorldBossEntity(1506, 1320, 0, 0, (short) 0, (short) 1, TaidhaCovington), //
                // 22:00
                new WorldBossEntity(1211, 1335, 0, 0, (short) 0, (short) 1, JungleWorm),
                new WorldBossEntity(1806, 1350, 0, 0, (short) 0, (short) 1, MegaDestroyer),
                new WorldBossEntity(1410, 1365, 0, 0, (short) 0, (short) 1, ShadowBehemoth),
                new WorldBossEntity(2107, 1380, 0, 0, (short) 0, (short) 1, Shatterer),
                new WorldBossEntity(1710, 1395, 0, 0, (short) 0, (short) 1, FrozenMaw),
                new WorldBossEntity(2207, 1410, 0, 0, (short) 2, (short) 1, ModnirUlgoth),
                new WorldBossEntity(110, 1425, 0, 0, (short) 2, (short) 1, FireElemental)
        };
        return Arrays.asList(allTimer);
    }
}