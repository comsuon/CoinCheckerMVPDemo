package com.hienpham.coinchecker.CoinList;

import com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData.RemoteCoinService;
import com.hienpham.coinchecker.Model.Coin;

import java.util.List;

public class CoinListRepo implements CoinListModel {

    CoinListDataCallbacks mListener;

    RemoteCoinService mRemote;

    public CoinListRepo(CoinListDataCallbacks listener){
        this.mListener = listener;
        mRemote = new RemoteCoinService();
    }

    @Override
    public void getCoinList(int start) {
        mRemote.getCoinList(start, new RemoteCoinService.GetCoinListCallbacks() {
            @Override
            public void onRemoteCoinListLoaded(List<Coin> coinList) {
                //todo local store
                mListener.onCoinListLoaded(coinList);
            }
        });
    }

    @Override
    public void getSpecificCoin(int id) {
    }
}
