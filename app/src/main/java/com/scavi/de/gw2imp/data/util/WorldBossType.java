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
package com.scavi.de.gw2imp.data.util;

import android.util.SparseArray;

public enum WorldBossType {
    Unknown(0),
    TaidhaCovington(1), JungleWorm(2), EvolvedJungleWorm(3), MegaDestroyer(4), ShadowBehemoth(5),
    Shatterer(6), SvanirShamanChief(7), ModnirUlgoth(8), FireElemental(9), GolemMarkTwo(10),
    Tequatl(11), FrozenMaw(12), KarkaQueen(13), ClawOfJormag(14);
    private final int mType;
    private static final SparseArray<WorldBossType> mAllTypes = new SparseArray<>();

    static {
        for (WorldBossType type : values()) {
            mAllTypes.put(type.getTypeId(), type);
        }
    }

    WorldBossType(final int type) {
        mType = type;
    }

    public int getTypeId() {
        return mType;
    }

    /**
     * Converts the given id to the world boss type
     *
     * @param typeId the id of the type
     * @return the enum to the id or <code>Unknown</code>
     */
    public static WorldBossType from(final int typeId) {
        return mAllTypes.get(typeId, Unknown);
    }
}