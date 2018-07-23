package com.hienpham.coinchecker.CoinList;

import com.hienpham.coinchecker.BasePresenter;
import com.hienpham.coinchecker.BaseView;
import com.hienpham.coinchecker.Model.Coin;

import java.util.List;

public class CoinListContract {

    public interface CoinListView extends BaseView{
        void showLoading();
        void hideLoading();
        void populateCoinlist(List<Coin> coinList);
        void refreshCoinList(List<Coin> coinsList);
        void showCoin(Coin coin);
    }

    public interface CoinListPresenter extends BasePresenter {
        void getCoinListInterval();
        void getSpecificCoin(int id);
        void onResume();
    }
}
