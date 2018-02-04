package com.example.roni.profiler.utils;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Used for Unit testing
 * All jobs that subscribe on trampoline() will be queued and executed one by one.
 * Only one thread will be used, not a pool
 * Created by roni on 03/02/18.
 */

public class ImmediateSchedulerProvider implements BaseSchedulerProvider {
    private static ImmediateSchedulerProvider instance;

    private ImmediateSchedulerProvider(){}

    public static synchronized ImmediateSchedulerProvider getInstance(){
        if(instance == null){
            instance = new ImmediateSchedulerProvider();
        }
        return instance;
    }

    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}
