package com.scavi.de.gw2imp.ui.graph;

import android.content.Context;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.scavi.de.gw2imp.R;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Gw2CurrencyFormatter extends DefaultLabelFormatter {
    public enum MoneyType {G, S, K}

    public static final int ONE_GRID_LENGTH = 18;

    private static final int SILVER_LIMIT = 100;
    private static final int GOLD_LIMIT = SILVER_LIMIT * 100;
    private final Context mContext;
    private static final DecimalFormat mFormat = new DecimalFormat();
    private final int mHistoryDataSetCount;
    private int mHorizontalStep;

    /**
     * Constructor
     *
     * @param context             the context to global information about the application
     *                            environment
     * @param historyDataSetCount the amount of history entries
     */
    public Gw2CurrencyFormatter(final Context context,
                                final int historyDataSetCount) {
        mContext = context;
        mHistoryDataSetCount = historyDataSetCount;
        mFormat.setMaximumFractionDigits(2);
    }

    @Override
    public String formatLabel(final double value,
                              final boolean isValueX) {
        return isValueX ? createHorizontalLabel(value) :
                createVerticalLabel(value);
    }


    /**
     * Creates the horizontal label for history prices (since history prices are in the grid).
     * For current prices the month / dates won't be determined because the labels would overlap.
     *
     * @param value 0, 20, 40, ...
     * @return the horizontal label
     */
    private String createHorizontalLabel(final double value) {
        if (value == 0) {
            mHorizontalStep = 0;
        }
        int monthDifference = mHorizontalStep - mHistoryDataSetCount;
        if (monthDifference <= 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, monthDifference);
            String date = String.format("%s/%s",
                    calendar.get(Calendar.MONTH) + 1,
                    String.valueOf(calendar.get(Calendar.YEAR)).substring(2));
            mHorizontalStep++;
            return date;
        } else {
            return null;
        }
    }


    /**
     * Creates the vertical label based on the price. Recalculates the given copper depending on the
     * currency.
     *
     * @param value the value
     * @return the vertical label
     */
    private String createVerticalLabel(final double value) {
        return createCurrencyString(mContext, value);
    }


    /**
     * @param context the context to global information about the application
     *                environment
     * @param value   the value
     * @return the converted currency string with the ending
     */
    public static String createCurrencyString(final Context context,
                                              double value) {
        MoneyType type = typeFrom(value);
        value = valueFrom(type, value);
        // show currency for y values
        return String.format(context.getString(R.string.trading_items_currency_format),
                mFormat.format(value),
                type);
    }


    /**
     * Since all money values are specified by copper, this method will calculate the
     * corresponding value to the type
     *
     * @param type  the money type (e.g. silver, gold)
     * @param value the current value (in copper)
     * @return the new value
     */
    private static double valueFrom(final MoneyType type,
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
    private static MoneyType typeFrom(final double value) {
        if (value < SILVER_LIMIT) {
            return MoneyType.K;
        } else if (value < GOLD_LIMIT) {
            return MoneyType.S;
        } else {
            return MoneyType.G;
        }
    }


    /**
     * Determines the money type from the given value and determines the real value in it's
     * corresponding money type (e.g. 100 copper = 1 silver)
     *
     * @param value the current value
     * @return the real value
     */
    public static double realValue(final double value) {
        MoneyType type = typeFrom(value);
        return valueFrom(type, value);
    }
}
