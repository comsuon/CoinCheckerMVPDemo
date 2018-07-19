package com.hienpham.coinchecker;

import android.view.View;

public interface BasePresenter {
    void attachView(BaseView view);
    void detachView();
}
