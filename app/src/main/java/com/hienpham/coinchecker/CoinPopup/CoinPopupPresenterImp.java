package com.hienpham.coinchecker.CoinPopup;

import android.util.Log;

import com.hienpham.coinchecker.BaseView;
import com.hienpham.coinchecker.Model.Coin;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class CoinPopupPresenterImp implements CoinPopupContract.CoinPopupPresenter {
    CoinPopupContract.CoinPopupView mView;
    CoinPopupContract.CoinPopupModel mModel;

    public CoinPopupPresenterImp(){
        this.mModel = new CoinPopupModelImp();
    }

    @Override
    public void getCoin(int id) {

        mModel.getCoinData(id).subscribeWith(new MaybeObserver<Coin>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Coin coin) {
                if(mView == null) return;
                Log.d("Coin Value", coin.toString());
                if(coin.getQuotes().getUSD().getPercent_change_1h() > 0) {
                    mView.showPumpFace(coin);
                } else {
                    mView.showDumpFace(coin);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void attachView(BaseView view) {
        mView = (CoinPopupContract.CoinPopupView) view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
