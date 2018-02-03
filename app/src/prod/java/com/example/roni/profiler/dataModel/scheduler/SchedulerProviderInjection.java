package com.example.roni.profiler.dataModel.scheduler;

/**
 * Created by roni on 03/02/18.
 */

public class SchedulerProviderInjection {
    public static SchedulerProvider getSchedulerProvider(){
        return SchedulerProvider.getInstance();
    }
}
