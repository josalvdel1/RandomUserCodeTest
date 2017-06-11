package com.josalvdel1.randomusercodetest;

import android.app.Application;
import android.content.Context;

import com.josalvdel1.randomusercodetest.di.component.AppComponent;
import com.josalvdel1.randomusercodetest.di.component.DaggerAppComponent;
import com.josalvdel1.randomusercodetest.di.module.AppModule;
import com.squareup.leakcanary.LeakCanary;

public class MyApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        initializeDagger();
        initializeLeakCanary();
    }

    private void initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void initializeLeakCanary() {
        LeakCanary.install(this);
    }


    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }
}