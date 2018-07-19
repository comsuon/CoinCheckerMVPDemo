package com.hienpham.coinchecker.Core.Thread;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainThreadExecutor implements Executor {

    private Handler mMainThreadExecutor;

    public MainThreadExecutor() {
        mMainThreadExecutor = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mMainThreadExecutor.post(command);
    }
}
