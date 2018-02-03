package com.example.roni.profiler.utils;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Used for Unit testing
 * All jobs that subscribe on trampoline() will be queued and executed one by one.
 * Created by roni on 03/02/18.
 */

public class SyncSchedulerProvider implements BaseSchedulerProvider {
    private static SyncSchedulerProvider instance;

    private SyncSchedulerProvider(){}

    public static synchronized SyncSchedulerProvider getInstance(){
        if(instance == null){
            instance = new SyncSchedulerProvider();
        }
        return instance;
    }

    @Override
    public Scheduler getComputationScheduler() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler getIoScheduler() {
        return null;
    }

    @Override
    public Scheduler getUiScheduler() {
        return null;
    }
}
