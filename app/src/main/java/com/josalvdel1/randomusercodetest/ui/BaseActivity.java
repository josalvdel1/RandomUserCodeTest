package com.josalvdel1.randomusercodetest.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.josalvdel1.randomusercodetest.MyApplication;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
            ButterKnife.bind(this);
        }
    }

    public abstract int getLayoutId();

    public void initializeDagger() {
        MyApplication.get(this)
                .getAppComponent()
                .inject(this);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}