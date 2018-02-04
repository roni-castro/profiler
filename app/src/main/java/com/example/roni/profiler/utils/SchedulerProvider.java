package com.example.roni.profiler.utils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by roni on 03/02/18.
 */

public class SchedulerProvider implements BaseSchedulerProvider {
    private static SchedulerProvider instance;
    private SchedulerProvider(){}

    public static synchronized SchedulerProvider getInstance(){
        if(instance == null){
            instance = new SchedulerProvider();
        }
        return instance;
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
