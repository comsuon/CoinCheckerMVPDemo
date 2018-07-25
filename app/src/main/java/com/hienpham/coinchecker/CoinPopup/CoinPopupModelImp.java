package com.hienpham.coinchecker.CoinPopup;

import com.hienpham.coinchecker.DataLayer.CoinListData.CoinListRepository;
import com.hienpham.coinchecker.DataLayer.CoinListData.CoinListRepositoryImp;
import com.hienpham.coinchecker.Model.Coin;

import io.reactivex.Maybe;

public class CoinPopupModelImp implements CoinPopupContract.CoinPopupModel {
    CoinListRepository coinRepo;

    public CoinPopupModelImp() {
        coinRepo = new CoinListRepositoryImp();
    }

    @Override
    public Maybe<Coin> getCoinData(int id) {
        return coinRepo.getSpecificCoin(id);
    }
}
