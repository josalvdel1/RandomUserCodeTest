package com.josalvdel1.randomusercodetest.di.module;


import com.josalvdel1.randomusercodetest.domain.executor.InteractorExecutor;
import com.josalvdel1.randomusercodetest.domain.executor.MainThread;
import com.josalvdel1.randomusercodetest.domain.executor.MainThreadImp;
import com.josalvdel1.randomusercodetest.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module()
public class ExecutorModule {

    @Provides
    @Singleton
    public InteractorExecutor providesInteractroExecutor() {
        return new ThreadExecutor();
    }

    @Provides
    public MainThread providesMainThreadExecutor() {
        return new MainThreadImp();
    }
}