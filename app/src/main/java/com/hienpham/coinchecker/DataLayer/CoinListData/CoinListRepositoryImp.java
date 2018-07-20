package com.hienpham.coinchecker.DataLayer.CoinListData;

import com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData.RemoteCoinService;
import com.hienpham.coinchecker.Model.Coin;

import java.util.List;

public class CoinListRepositoryImp implements CoinListRepository {
    RemoteCoinService mCoinService;
    CoinListRepository.CoinListDataCallbacks mListener;

    public CoinListRepositoryImp(CoinListDataCallbacks listener) {
        mCoinService = new RemoteCoinService();
        mListener = listener;
    }

    @Override
    public void getCoinList(int start) {
        mCoinService.fetchCoinList(start, new RemoteCoinService.GetCoinListCallbacks() {
            @Override
            public void onRemoteCoinListLoaded(List<Coin> coinList) {
                if(mListener != null) {
                    mListener.onCoinListLoaded(coinList);
                }
            }
        });
    }

    @Override
    public void getSpecificCoin(int id) {

    }
}
