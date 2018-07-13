package com.scavi.de.gw2imp.ui.util;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.Timer;
import java.util.TimerTask;


public class DelayedTextFieldWatcher implements TextWatcher {
    private final Runnable mExecutor;
    private final int mDelay;
    private Timer mTimer = new Timer();

    /**
     * Constructor
     *
     * @param executor the method that will be executed after the timer has ended
     * @param delay    the delay in milliseconds
     */
    public DelayedTextFieldWatcher(final Runnable executor,
                                   final int delay) {
        mExecutor = executor;
        mDelay = delay;
    }


    @Override
    public void beforeTextChanged(final CharSequence charSequence,
                                  final int i,
                                  final int i1,
                                  final int i2) {
    }


    @Override
    public void onTextChanged(final CharSequence charSequence,
                              final int i,
                              final int i1,
                              final int i2) {
    }


    @Override
    public void afterTextChanged(final Editable editable) {
        mTimer.cancel();
        if (editable.toString().isEmpty()) {
            execute();
        } else {
            execute();
        }
    }

    private void execute() {
        // TODO pool
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mExecutor.run();
            }
        }, mDelay);
    }
}
