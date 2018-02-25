package com.scavi.de.gw2imp.ui.graph;

import android.content.Context;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.scavi.de.gw2imp.R;

public class Gw2CurrencyFormatter extends DefaultLabelFormatter {
    private enum MoneyType {G, S, K}

    private final int SILVER_LIMIT = 100;
    private final int GOLD_LIMIT = SILVER_LIMIT * 100;

    private final Context mContext;

    public Gw2CurrencyFormatter(final Context context) {
        mContext = context;
    }

    @Override
    public String formatLabel(double value,
                              final boolean isValueX) {
        if (isValueX) {
            // show normal x values
            return super.formatLabel(value, isValueX);
        } else {
            MoneyType type = typeFrom(value);
            value = createCurrencyString(type, value);
            // show currency for y values
            return String.format(mContext.getString(R.string.trading_items_currency_format),
                    super.formatLabel(value, isValueX),
                    type);
        }
    }


    /**
     * Since all money values are specified by copper, this method will calculate the
     * corresponding value to the type
     *
     * @param type  the money type (e.g. silver, gold)
     * @param value the current value (in copper)
     * @return the new value
     */
    private double createCurrencyString(final MoneyType type,
                                        double value) {
        switch (type) {
            case K:
                // nothing to do
                break;
            case S:
                value /= SILVER_LIMIT;
                break;
            case G:
                value /= GOLD_LIMIT;
                break;
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
        return value;
    }

    /**
     * Determines the money type depending on the given copper
     *
     * @param value the copper value
     * @return the type of money
     */
    private MoneyType typeFrom(final double value) {
        if (value < SILVER_LIMIT) {
            return MoneyType.K;
        } else if (value < GOLD_LIMIT) {
            return MoneyType.S;
        } else {
            return MoneyType.G;
        }
    }
}
