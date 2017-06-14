package com.josalvdel1.randomusercodetest.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initializeDagger();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            return inflater.inflate(getLayoutId(), container, false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
        initUI();
    }

    public abstract int getLayoutId();

    public abstract void initUI();

    public abstract void initializeDagger();

    private void injectViews(final View view) {
        ButterKnife.bind(this, view);
    }
}