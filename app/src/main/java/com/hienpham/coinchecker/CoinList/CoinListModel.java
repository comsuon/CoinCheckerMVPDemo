package com.hienpham.coinchecker.CoinList;

import com.hienpham.coinchecker.Model.CoinListRes;

import io.reactivex.Flowable;

public interface CoinListModel {
    Flowable<CoinListRes> getCoinListInterval(int start, long timer);

    Flowable<CoinListRes> getCoinList(int start);

}
