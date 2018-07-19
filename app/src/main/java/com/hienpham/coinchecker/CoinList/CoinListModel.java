package com.hienpham.coinchecker.CoinList;

import com.hienpham.coinchecker.Model.Coin;

import java.util.List;

public interface CoinListModel {
    void getCoinList(int start);

    void getSpecificCoin(int id);

    public interface CoinListDataCallbacks {
        void onCoinListLoaded(List<Coin> coinList);
    }
}
