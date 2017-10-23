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
package com.scavi.de.gw2imp.communication.response.achievement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Daily {

    @SerializedName("pve")
    @Expose
    private List<Pve> pve = null;
    @SerializedName("pvp")
    @Expose
    private List<Pvp> pvp = null;
    @SerializedName("wvw")
    @Expose
    private List<Wvw> wvw = null;
    @SerializedName("fractals")
    @Expose
    private List<Fractal> fractals = null;
    @SerializedName("special")
    @Expose
    private List<Object> special = null;

    public List<Pve> getPve() {
        return pve;
    }

    public void setPve(List<Pve> pve) {
        this.pve = pve;
    }

    public List<Pvp> getPvp() {
        return pvp;
    }

    public void setPvp(List<Pvp> pvp) {
        this.pvp = pvp;
    }

    public List<Wvw> getWvw() {
        return wvw;
    }

    public void setWvw(List<Wvw> wvw) {
        this.wvw = wvw;
    }

    public List<Fractal> getFractals() {
        return fractals;
    }

    public void setFractals(List<Fractal> fractals) {
        this.fractals = fractals;
    }

    public List<Object> getSpecial() {
        return special;
    }

    public void setSpecial(List<Object> special) {
        this.special = special;
    }
}
