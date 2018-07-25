package com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData;

import com.hienpham.coinchecker.DataLayer.Webservice;
import com.hienpham.coinchecker.Model.CoinListRes;

import io.reactivex.Single;

public class RemoteCoinService {

    public static int CMC_START_DEFAULT = 1;


    CMCService mCoinService;

    public RemoteCoinService() {

        mCoinService = new Webservice().getCoinService();
    }

    public Single<CoinListRes> fetchCoinList(final int start) {
        return mCoinService.getCoinList(start);
    }

    public void getSpecificCoin(int id) {

    }
}
