package com.josalvdel1.randomusercodetest.di.module;

import android.app.Activity;

import com.josalvdel1.randomusercodetest.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module()
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    public Activity provideActivityContext() {
        return activity;
    }
}