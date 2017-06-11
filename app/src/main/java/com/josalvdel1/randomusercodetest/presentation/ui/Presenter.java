package com.josalvdel1.randomusercodetest.presentation.ui;

import android.support.annotation.CallSuper;

public class Presenter<T extends Presenter.View> {

    private T view;

    public Presenter() {
    }

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    @CallSuper
    public void resume() {
    }

    @CallSuper
    public void pause() {
    }

    @CallSuper
    public void destroy() {
        view = null;
    }

    protected void showLoading() {
        if (getView() != null) {
            getView().showLoading();
        }
    }

    protected void hideLoading() {
        if (getView() != null) {
            getView().hideLoading();
        }
    }

    public interface View {
        void showLoading();

        void hideLoading();
    }
}