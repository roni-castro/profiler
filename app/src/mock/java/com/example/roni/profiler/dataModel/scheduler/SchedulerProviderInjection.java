package com.example.roni.profiler.dataModel.scheduler;

import com.example.roni.profiler.utils.SyncSchedulerProvider;

/**
 * Created by roni on 03/02/18.
 */

public class SchedulerProviderInjection {
    public static SyncSchedulerProvider getSchedulerProvider(){
        return SyncSchedulerProvider.getInstance();
    }
}
