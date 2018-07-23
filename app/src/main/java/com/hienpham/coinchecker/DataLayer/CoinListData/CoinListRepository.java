package com.hienpham.coinchecker.DataLayer.CoinListData;

import com.hienpham.coinchecker.Model.Coin;

import java.util.List;

public interface CoinListRepository {
    void getCoinList(int start);
    Coin getSpecificCoin(int id);

    interface CoinListDataCallbacks {
        void onCoinListLoaded(List<Coin> coinList);
    }
}
