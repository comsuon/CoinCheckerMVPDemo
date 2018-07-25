package com.hienpham.coinchecker.DataLayer.CoinListData;

import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.Model.CoinListRes;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

public interface CoinListRepository {
    Flowable<CoinListRes> getCoinList(int start);
    Maybe<Coin> getSpecificCoin(int id);
}
