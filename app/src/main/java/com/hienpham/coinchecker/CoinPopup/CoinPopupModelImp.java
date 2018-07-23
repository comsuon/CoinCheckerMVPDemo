package com.hienpham.coinchecker.CoinPopup;

import com.hienpham.coinchecker.DataLayer.CoinListData.CoinListRepository;
import com.hienpham.coinchecker.DataLayer.CoinListData.CoinListRepositoryImp;
import com.hienpham.coinchecker.Model.Coin;

public class CoinPopupModelImp implements CoinPopupContract.CoinPopupModel {
    CoinListRepository coinRepo;

    public CoinPopupModelImp() {
        coinRepo = new CoinListRepositoryImp(null);
    }

    @Override
    public Coin getCoinData(int id) {
        return coinRepo.getSpecificCoin(id);
    }
}
