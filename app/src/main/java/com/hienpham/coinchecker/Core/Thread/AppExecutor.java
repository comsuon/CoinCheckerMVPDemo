package com.hienpham.coinchecker.Core.Thread;

public class AppExecutor {
    private MainThreadExecutor mMainThread;
    private NetworkExecutor mNetworkExecutor;

    public AppExecutor() {
        mMainThread = new MainThreadExecutor();
        mNetworkExecutor = new NetworkExecutor();
    }

    public MainThreadExecutor getMainThread() {
        return mMainThread;
    }

    public NetworkExecutor getNetworkExecutor() {
        return mNetworkExecutor;
    }
}
