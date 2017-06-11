package com.josalvdel1.randomusercodetest.domain.executor;

import android.os.Handler;
import android.os.Looper;

public class MainThreadImp implements MainThread {

    private Handler handler;

    public MainThreadImp() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}