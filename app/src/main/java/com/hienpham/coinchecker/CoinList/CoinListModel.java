package com.hienpham.coinchecker.CoinList;

import com.hienpham.coinchecker.Model.Coin;

import java.util.List;

public interface CoinListModel {
    void getCoinListInterval(int start, long timer);

    void getCoinList(int start);

    void getSpecificCoin(int id);

    interface CoinListModelCallbacks {
        void onCoinListLoaded(List<Coin> coinList);
    }
}
