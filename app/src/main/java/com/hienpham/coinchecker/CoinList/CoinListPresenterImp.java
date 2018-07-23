package com.hienpham.coinchecker.CoinList;

import android.support.annotation.NonNull;

import com.hienpham.coinchecker.BaseView;
import com.hienpham.coinchecker.CoinList.CoinListContract.CoinListView;
import com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData.RemoteCoinService;
import com.hienpham.coinchecker.Model.Coin;

import java.util.List;

public class CoinListPresenterImp implements CoinListContract.CoinListPresenter, CoinListModel.CoinListModelCallbacks {
    CoinListContract.CoinListView mView;

    CoinListModel mModel;

    public CoinListPresenterImp (){
        mModel = new CoinListModelImp(this);
    }

    @Override
    public void getCoinListInterval() {
        mModel.getCoinListInterval(RemoteCoinService.CMC_START_DEFAULT, 60000);
    }

    @Override
    public void onResume() {
        loadCoinList(RemoteCoinService.CMC_START_DEFAULT);
    }

    @Override
    public void getSpecificCoin(int id) {
        Coin coin = mModel.getSpecificCoin(id);
        mView.showCoin(coin);
    }

    private void loadCoinList(int start) {
        mView.showLoading();
        mModel.getCoinList(start);
    }


    @Override
    public void onCoinListLoaded(List<Coin> coinList) {
        if(null != mView){
            mView.hideLoading();
            Coin lastCoin = coinList.get(coinList.size()-1);
            if(lastCoin.getRank() > 100) {
                mView.populateCoinlist(coinList);
            } else {
                mView.refreshCoinList(coinList);
            }
        }
    }

    @Override
    public void attachView(BaseView view) {
        mView = (CoinListView) view;
    }

    @Override
    public void detachView() {

    }
}
