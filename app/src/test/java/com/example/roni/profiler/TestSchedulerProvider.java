package com.example.roni.profiler;

import com.example.roni.profiler.utils.BaseSchedulerContract;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

/**
 * Used for Unit testing
 * Created by roni on 03/02/18.
 */

public class TestSchedulerProvider implements BaseSchedulerContract {
    private static TestSchedulerProvider instance;

    private TestSchedulerProvider(){}

    public static synchronized TestSchedulerProvider getInstance(){
        if(instance == null){
            instance = new TestSchedulerProvider();
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
