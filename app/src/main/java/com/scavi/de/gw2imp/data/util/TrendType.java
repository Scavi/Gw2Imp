package com.scavi.de.gw2imp.data.util;

public enum TrendType {
    SellItems(1), BuyItems(2), Gems(10);
    private final int mType;

    /**
     * Constructor
     *
     * @param type the trending type identifier
     */
    TrendType(final int type) {
        mType = type;
    }

    /**
     * @return the trending type identifier
     */
    public int getType() {
        return mType;
    }
}
