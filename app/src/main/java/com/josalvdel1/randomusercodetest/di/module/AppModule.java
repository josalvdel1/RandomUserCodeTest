package com.josalvdel1.randomusercodetest.di.module;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.josalvdel1.randomusercodetest.BuildConfig;
import com.josalvdel1.randomusercodetest.R;
import com.josalvdel1.randomusercodetest.di.qualifiers.ApiLevel;
import com.josalvdel1.randomusercodetest.di.qualifiers.Endpoint;
import com.josalvdel1.randomusercodetest.util.PreferencesUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {

    private final Application context;

    public AppModule(Application context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Application provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public PreferencesUtils providePreferencesUtils(SharedPreferences sharedPreferences) {
        return new PreferencesUtils(sharedPreferences);
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharePreferences(Application application) {
        return application.getSharedPreferences(application.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @ApiLevel
    public int provideApiLevel() {
        return Build.VERSION.SDK_INT;
    }

    @Provides
    @Singleton
    @Endpoint
    public String provideEndpoint() {
        return BuildConfig.API_URL;
    }
}