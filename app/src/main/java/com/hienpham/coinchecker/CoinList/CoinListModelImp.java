package com.hienpham.coinchecker.CoinList;

import com.hienpham.coinchecker.DataLayer.CoinListData.CoinListRepository;
import com.hienpham.coinchecker.DataLayer.CoinListData.CoinListRepositoryImp;
import com.hienpham.coinchecker.Model.Coin;

import java.util.List;

public class CoinListModelImp implements CoinListModel, CoinListRepository.CoinListDataCallbacks {

    CoinListRepository mDataManager;
    CoinListModelCallbacks mListener;

    public CoinListModelImp(CoinListModelCallbacks listener){
        this.mListener = listener;
        mDataManager = new CoinListRepositoryImp(this);
    }

    @Override
    public void getCoinList(int start) {
        mDataManager.getCoinList(start);
    }

    @Override
    public void getSpecificCoin(int id) {
    }

    @Override
    public void onCoinListLoaded(List<Coin> coinList) {
        if(null != mListener){
            mListener.onCoinListLoaded(coinList);
        }
    }

    @Override
    public void onSpecificCoinLoaded(Coin coin) {

    }
}
