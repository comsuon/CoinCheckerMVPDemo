package com.hienpham.coinchecker.CoinPopup;

import com.hienpham.coinchecker.BasePresenter;
import com.hienpham.coinchecker.BaseView;
import com.hienpham.coinchecker.Model.Coin;

import io.reactivex.Maybe;

public class CoinPopupContract {
    interface CoinPopupView extends BaseView{
        void showPumpFace(Coin coin);
        void showDumpFace(Coin coin);
    }

    interface CoinPopupPresenter extends BasePresenter{
        void getCoin(int id);
    }

    interface CoinPopupModel {
        Maybe<Coin> getCoinData(int id);
    }
}
