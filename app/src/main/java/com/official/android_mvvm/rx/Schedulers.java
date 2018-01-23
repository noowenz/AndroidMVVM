package com.official.android_mvvm.rx;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Provides various threading schedulers.
 */

public class Schedulers {

    @Inject
    public Schedulers() {
    }

    /**
     * IO thread pool scheduler
     */
    public Scheduler io() {
        return io.reactivex.schedulers.Schedulers.io();
    }

    /**
     * Computation thread pool scheduler
     */
    public Scheduler computation() {
        return io.reactivex.schedulers.Schedulers.computation();
    }

    /**
     * Main Thread scheduler
     */
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}