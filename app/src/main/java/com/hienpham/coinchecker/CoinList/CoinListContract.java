package com.hienpham.coinchecker.CoinList;

import com.hienpham.coinchecker.BasePresenter;
import com.hienpham.coinchecker.BaseView;
import com.hienpham.coinchecker.Model.Coin;

import java.util.List;

public class CoinListContract {

    public interface CoinListView extends BaseView{
        public void showLoading();
        public void hideLoading();
        public void populateCoinlist(List<Coin> coinList);
    }

    public interface CoinListPresenter extends BasePresenter {
        public void onResume();
    }
}
