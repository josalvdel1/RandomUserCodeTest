package com.josalvdel1.randomusercodetest.domain.usecase;

import com.josalvdel1.randomusercodetest.domain.executor.InteractorExecutor;
import com.josalvdel1.randomusercodetest.domain.executor.MainThread;

public abstract class UseCase<T> {

    private final InteractorExecutor interactorExecutor;
    private final MainThread mainThread;

    public UseCase(InteractorExecutor interactorExecutor, MainThread mainThread) {
        this.interactorExecutor = interactorExecutor;
        this.mainThread = mainThread;
    }

    public void execute(Callback<T> callback, Object... params) {
        interactorExecutor.run(() -> onExecute(callback, params));
    }

    public abstract void onExecute(Callback<T> callback, Object... params);

    public void notifySuccess(T result, Callback<T> callback) {
        mainThread.execute(() -> callback.onSuccess(result));
    }

    public void notifyError(Throwable error, Callback<T> callback) {
        mainThread.execute(() -> callback.onError(error));
    }

    public interface Callback<T> {
        void onSuccess(T result);

        void onError(Throwable error);
    }
}
