package com.josalvdel1.randomusercodetest;

import android.app.Application;
import android.content.Context;

import com.josalvdel1.randomusercodetest.di.component.AppComponent;
import com.josalvdel1.randomusercodetest.di.component.DaggerAppComponent;
import com.josalvdel1.randomusercodetest.di.module.AppModule;

public class MyApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDagger();
    }

    private void initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }
}