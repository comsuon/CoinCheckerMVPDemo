package com.hienpham.coinchecker.CoinList;

import android.support.annotation.NonNull;

import com.hienpham.coinchecker.BaseView;
import com.hienpham.coinchecker.Model.Coin;

import java.util.List;

public class CoinListPresenterImp implements CoinListContract.CoinListPresenter, CoinListModel.CoinListDataCallbacks {
    CoinListContract.CoinListView mView;
    CoinListModel mModel;

    public CoinListPresenterImp (){
        mModel = new CoinListRepo(this);
    }

    @Override
    public void onResume() {
        loadCoinList(0);
    }

    private void loadCoinList(int start) {
        mView.showLoading();
        mModel.getCoinList(start);
    }

    @Override
    public void attachView(@NonNull BaseView view) {
        mView = (CoinListContract.CoinListView) view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onCoinListLoaded(List<Coin> coinList) {
        if(null != mView){
            mView.hideLoading();
            mView.populateCoinlist(coinList);
        }
    }
}
