package com.example.roni.profiler.utils;

import io.reactivex.Scheduler;

/**
 * Created by roni on 03/02/18.
 */

public interface BaseSchedulerProvider {
    Scheduler computation();
    Scheduler io();
    Scheduler ui();
}
