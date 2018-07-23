package com.hienpham.coinchecker.CoinPopup;

import com.hienpham.coinchecker.BaseView;
import com.hienpham.coinchecker.Model.Coin;

public class CoinPopupPresenterImp implements CoinPopupContract.CoinPopupPresenter {
    CoinPopupContract.CoinPopupView mView;
    int coinId;
    CoinPopupContract.CoinPopupModel mModel;

    public CoinPopupPresenterImp(int coinId){
        this.coinId = coinId;
        this.mModel = new CoinPopupModelImp();
    }

    @Override
    public void getCoin(int id) {
        Coin coin = mModel.getCoinData(id);

        if(mView == null) return;

        if(coin.getQuotes().getUSD().getPercent_change_1h() > 0) {
            mView.showPumpFace(coin);
        } else {
            mView.showDumpFace(coin);
        }
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
