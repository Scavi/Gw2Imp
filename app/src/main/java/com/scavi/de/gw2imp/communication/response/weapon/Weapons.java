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
 */

package com.scavi.de.gw2imp.communication.response.weapon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * All Weapons of all classes
 */
public class Weapons {
    @SerializedName("Axe")
    @Expose
    private Weapon axe;
    @SerializedName("Dagger")
    @Expose
    private Weapon dagger;
    @SerializedName("Focus")
    @Expose
    private Weapon focus;
    @SerializedName("Greatsword")
    @Expose
    private Weapon greatsword;
    @SerializedName("Hammer")
    @Expose
    private Weapon hammer;
    @SerializedName("Longbow")
    @Expose
    private Weapon longbow;
    @SerializedName("Mace")
    @Expose
    private Weapon mace;
    @SerializedName("Pistol")
    @Expose
    private Weapon pistol;
    @SerializedName("Rifle")
    @Expose
    private Weapon rifle;
    @SerializedName("Shield")
    @Expose
    private Weapon shield;
    @SerializedName("Scepter")
    @Expose
    private Weapon scepter;
    @SerializedName("Shortbow")
    @Expose
    private Weapon shortbow;
    @SerializedName("Spear")
    @Expose
    private Weapon spear;
    @SerializedName("Speargun")
    @Expose
    private Weapon speargun;
    @SerializedName("Sword")
    @Expose
    private Weapon sword;
    @SerializedName("Torch")
    @Expose
    private Weapon torch;
    @SerializedName("Trident")
    @Expose
    private Weapon trident;
    @SerializedName("Warhorn")
    @Expose
    private Weapon warhorn;

    public Weapon getAxe() {
        return axe;
    }

    public void setAxe(Weapon axe) {
        this.axe = axe;
    }

    public Weapon getDagger() {
        return dagger;
    }

    public void setDagger(Weapon dagger) {
        this.dagger = dagger;
    }

    public Weapon getFocus() {
        return focus;
    }

    public void setFocus(Weapon focus) {
        this.focus = focus;
    }

    public Weapon getGreatsword() {
        return greatsword;
    }

    public void setGreatsword(Weapon greatsword) {
        this.greatsword = greatsword;
    }

    public Weapon getHammer() {
        return hammer;
    }

    public void setHammer(Weapon hammer) {
        this.hammer = hammer;
    }

    public Weapon getLongbow() {
        return longbow;
    }

    public void setLongbow(Weapon longbow) {
        this.longbow = longbow;
    }

    public Weapon getMace() {
        return mace;
    }

    public void setMace(Weapon mace) {
        this.mace = mace;
    }

    public Weapon getPistol() {
        return pistol;
    }

    public void setPistol(Weapon pistol) {
        this.pistol = pistol;
    }

    public Weapon getRifle() {
        return rifle;
    }

    public void setRifle(Weapon rifle) {
        this.rifle = rifle;
    }

    public Weapon getShield() {
        return shield;
    }

    public void setShield(Weapon shield) {
        this.shield = shield;
    }

    public Weapon getScepter() {
        return scepter;
    }

    public void setScepter(Weapon scepter) {
        this.scepter = scepter;
    }

    public Weapon getShortbow() {
        return shortbow;
    }

    public void setShortbow(Weapon shortbow) {
        this.shortbow = shortbow;
    }

    public Weapon getSpear() {
        return spear;
    }

    public void setSpear(Weapon spear) {
        this.spear = spear;
    }

    public Weapon getSpeargun() {
        return speargun;
    }

    public void setSpeargun(Weapon speargun) {
        this.speargun = speargun;
    }

    public Weapon getSword() {
        return sword;
    }

    public void setSword(Weapon sword) {
        this.sword = sword;
    }

    public Weapon getTorch() {
        return torch;
    }

    public void setTorch(Weapon torch) {
        this.torch = torch;
    }

    public Weapon getTrident() {
        return trident;
    }

    public void setTrident(Weapon trident) {
        this.trident = trident;
    }

    public Weapon getWarhorn() {
        return warhorn;
    }

    public void setWarhorn(Weapon warhorn) {
        this.warhorn = warhorn;
    }
}
