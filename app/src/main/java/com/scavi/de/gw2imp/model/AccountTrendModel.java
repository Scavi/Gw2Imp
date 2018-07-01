package com.scavi.de.gw2imp.model;

import android.content.Context;
import android.support.annotation.WorkerThread;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.async.IExecutorAccess;
import com.scavi.de.gw2imp.data.db.IDatabaseAccess;
import com.scavi.de.gw2imp.data.so.Trend;

import java.util.LinkedList;
import java.util.List;

public class AccountTrendModel extends AbstractModel {
    private final IDatabaseAccess mImpDatabase;
    private final IExecutorAccess mExecutorAccess;

    /**
     * Constructor
     *
     * @param context        the context to global information about the application environment
     * @param impDatabase    the database access of this application
     * @param executorAccess to access the main and background threads
     */
    public AccountTrendModel(final Context context,
                             final IDatabaseAccess impDatabase,
                             final IExecutorAccess executorAccess) {
        super(context);
        mImpDatabase = impDatabase;
        mExecutorAccess = executorAccess;
    }


    /**
     * This method determines all current trends. Currently there are 5 categories supported
     * - top increasing items
     * - top decreasing items
     * - top selected increasing items (my)
     * - top selected decreasing items (my)
     * - gems
     *
     * @return all trends
     */
    @WorkerThread
    public List<Trend> determineAllTrends() {
        List<Trend> allTrends = new LinkedList<>();
        addTopIncreasingItems(allTrends);
        addTopDecreasingItems(allTrends);
        addMyTopIncreasingItems(allTrends);
        addMyTopDecreasingItems(allTrends);
        addGemCourse(allTrends);
        return allTrends;
    }


    private void addTopIncreasingItems(final List<Trend> allTrends) {
        String headerName = mContext.getString(R.string.account_trend_top_increasing_items);
        allTrends.add(new Trend.Builder().setHeaderName(headerName).setIsHeader(true).build());
        // TODO db select
    }


    private void addTopDecreasingItems(final List<Trend> allTrends) {
        String headerName = mContext.getString(R.string.account_trend_top_decreasing_items);
        allTrends.add(new Trend.Builder().setHeaderName(headerName).setIsHeader(true).build());
        // TODO db select
    }


    private void addMyTopIncreasingItems(final List<Trend> allTrends) {
        String headerName = mContext.getString(R.string.account_trend_top_increasing_my_items);
        allTrends.add(new Trend.Builder().setHeaderName(headerName).setIsHeader(true).build());
        // TODO db select
    }


    private void addMyTopDecreasingItems(final List<Trend> allTrends) {
        String headerName = mContext.getString(R.string.account_trend_top_decreasing_my_items);
        allTrends.add(new Trend.Builder().setHeaderName(headerName).setIsHeader(true).build());
        // TODO db select
    }


    private void addGemCourse(final List<Trend> allTrends) {
        String headerName = mContext.getString(R.string.account_trend_gem_course);
        allTrends.add(new Trend.Builder().setHeaderName(headerName).setIsHeader(true).build());
        // TODO db select
    }


    public IExecutorAccess getExecutorAccess() {
        return mExecutorAccess;
    }
}
