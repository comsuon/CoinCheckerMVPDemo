package com.hienpham.coinchecker.Core.Thread;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class NetworkExecutor implements Executor {
    private static final int POOL_LIMIT = 3;

    Executor mNetworkExecutor;

    public NetworkExecutor () {
        mNetworkExecutor = Executors.newFixedThreadPool(3);
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mNetworkExecutor.execute(command);
    }
}
