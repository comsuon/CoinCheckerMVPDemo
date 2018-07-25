package com.hienpham.coinchecker.CoinList;

import android.support.annotation.NonNull;

import com.hienpham.coinchecker.BaseView;
import com.hienpham.coinchecker.CoinList.CoinListContract.CoinListView;
import com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData.RemoteCoinService;
import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.Model.CoinListRes;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.subscribers.DisposableSubscriber;

public class CoinListPresenterImp implements CoinListContract.CoinListPresenter {
    CoinListContract.CoinListView mView;

    CoinListModel mModel;

    public CoinListPresenterImp (){
        mModel = new CoinListModelImp(this);
    }

    @Override
    public void initCoinList() {
        mView.showLoading();
        mModel.getCoinListInterval(RemoteCoinService.CMC_START_DEFAULT, 1).subscribeWith(new DisposableSubscriber<CoinListRes>() {
            @Override
            public void onNext(CoinListRes coinListRes) {
                onCoinListLoaded(coinListRes.getCoinList());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onRefreshCoinList() {
        mModel.getCoinList(RemoteCoinService.CMC_START_DEFAULT)
                .doOnSubscribe(disposable -> mView.showLoading())
                .subscribeWith(new DisposableSubscriber<CoinListRes>() {
                    @Override
                    public void onNext(CoinListRes coinListRes) {
                        onCoinListLoaded(coinListRes.getCoinList());
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void onCoinListLoaded(List<Coin> coinList) {
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
