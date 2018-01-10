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
package com.scavi.de.gw2imp.ui.util;

import android.content.Context;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.data.entity.event.WorldBossEntity;
import com.scavi.de.gw2imp.data.entity.raid.RaidEntity;

import java.util.Locale;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Gw2DataMapper {
    private static final String VALE_GUARDIAN = "vale_guardian";
    private static final String SPIRIT_WOODS = "spirit_woods";
    private static final String GORSEVAL = "gorseval";
    private static final String SABETHA = "sabetha";
    private static final String SLOTHASOR = "slothasor";
    private static final String BANDIT_TRIO = "bandit_trio";
    private static final String MATTHIAS = "matthias";
    private static final String ESCORT = "escort";
    private static final String KEEP_CONSTRUCT = "keep_construct";
    private static final String TWISTED_CASTLE = "twisted_castle";
    private static final String XERA = "xera";
    private static final String CAIRN = "cairn";
    private static final String MURSAAT_OVERSEER = "mursaat_overseer";
    private static final String SAMAROG = "samarog";
    private static final String DEIMOS = "deimos";

    /**
     * Private constructor for the helper
     */
    private Gw2DataMapper() {
    }


    /**
     * Determines from the raid context of the given {@link RaidEntity} the drawable id
     *
     * @param entity the raid entity
     * @return the drawable id
     */
    public static int determineRaidDrawableId(final RaidEntity entity) {
        int drawableId = 0;
        switch (entity.getRaidContext().toLowerCase(Locale.getDefault())) {
            case VALE_GUARDIAN:
                drawableId = R.drawable.raid_value_guardian;
                break;
            case SPIRIT_WOODS:
                drawableId = R.drawable.raid_spirit_woods;
                break;
            case GORSEVAL:
                drawableId = R.drawable.raid_gorseval;
                break;
            case SABETHA:
                drawableId = R.drawable.raid_sabetha;
                break;
            case SLOTHASOR:
                drawableId = R.drawable.raid_slothasor;
                break;
            case BANDIT_TRIO:
                drawableId = R.drawable.raid_bandit_trio;
                break;
            case MATTHIAS:
                drawableId = R.drawable.raid_matthias;
                break;
            case ESCORT:
                drawableId = R.drawable.raid_escort;
                break;
            case KEEP_CONSTRUCT:
                drawableId = R.drawable.raid_keep_construct;
                break;
            case TWISTED_CASTLE:
                drawableId = R.drawable.raid_twisted_castle;
                break;
            case XERA:
                drawableId = R.drawable.raid_xera;
                break;
            case CAIRN:
                drawableId = R.drawable.raid_cairn;
                break;
            case MURSAAT_OVERSEER:
                drawableId = R.drawable.raid_mursaat_overseer;
                break;
            case SAMAROG:
                drawableId = R.drawable.raid_samarog;
                break;
            case DEIMOS:
                drawableId = R.drawable.raid_deimos;
                break;
        }
        return drawableId;
    }


    /**
     * Determines from the world boss context of the given {@link WorldBossEntity} the drawable id
     *
     * @param entity the world boss entity
     * @return the drawable id
     */
    public static int determineWorldBossDrawableId(final WorldBossEntity entity) {
        int id = R.drawable.quaggan_knight;
        // TODO
        switch (entity.getType()) {
            case TaidhaCovington:
                break;
            case JungleWorm:
                break;
            case EvolvedJungleWorm:
                break;
            case MegaDestroyer:
                break;
            case ShadowBehemoth:
                break;
            case Shatterer:
                break;
            case SvanirShamanChief:
                break;
            case ModnirUlgoth:
                break;
            case FireElemental:
                break;
            case GolemMarkTwo:
                break;
            case Tequatl:
                break;
            case FrozenMaw:
                break;
            case KarkaQueen:
                break;
            case ClawOfJormag:
                break;
        }
        return id;
    }


    /**
     * Determines from the world boss context of the given {@link WorldBossEntity#getType()}
     *
     * @param context the context to global information about the application environment
     * @param entity  the world boss entity
     * @return the title / name of of the world boss
     */
    public static String determineWorldBoss(final Context context,
                                            final WorldBossEntity entity) {
        String title = "";
        switch (entity.getType()) {
            case TaidhaCovington:
                title = context.getString(R.string.world_boss_taidha_covington);
                break;
            case JungleWorm:
                title = context.getString(R.string.world_boss_jungle_worm);
                break;
            case EvolvedJungleWorm:
                title = context.getString(R.string.world_boss_evolved_jungle_worm);
                break;
            case MegaDestroyer:
                title = context.getString(R.string.world_boss_mega_destroyer);
                break;
            case ShadowBehemoth:
                title = context.getString(R.string.world_boss_shadow_behemoth);
                break;
            case Shatterer:
                title = context.getString(R.string.world_boss_shatterer);
                break;
            case SvanirShamanChief:
                title = context.getString(R.string.world_boss_svanir_shaman_chief);
                break;
            case ModnirUlgoth:
                title = context.getString(R.string.world_boss_modniir_ulgoth);
                break;
            case FireElemental:
                title = context.getString(R.string.world_boss_fire_elemental);
                break;
            case GolemMarkTwo:
                title = context.getString(R.string.world_boss_golem_mark_two);
                break;
            case Tequatl:
                title = context.getString(R.string.world_boss_tequatl_the_sunless);
                break;
            case FrozenMaw:
                title = context.getString(R.string.world_boss_frozen_maw);
                break;
            case KarkaQueen:
                title = context.getString(R.string.world_boss_karka_queen);
                break;
            case ClawOfJormag:
                title = context.getString(R.string.world_boss_claw_of_jormag);
                break;
        }
        return title;
    }
}
