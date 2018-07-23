package com.hienpham.coinchecker.CoinList;

import com.hienpham.coinchecker.DataLayer.CoinListData.CoinListRepository;
import com.hienpham.coinchecker.DataLayer.CoinListData.CoinListRepositoryImp;
import com.hienpham.coinchecker.Model.Coin;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CoinListModelImp implements CoinListModel, CoinListRepository.CoinListDataCallbacks {

    CoinListRepository mDataManager;
    CoinListModelCallbacks mListener;
    boolean timerRunning;

    public CoinListModelImp(CoinListModelCallbacks listener){
        this.mListener = listener;
        mDataManager = new CoinListRepositoryImp(this);
        timerRunning = false;
    }

    @Override
    public void getCoinListInterval(final int start, final long timer) {
        if (timerRunning) return;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mDataManager.getCoinList(start);
                timerRunning = true;
            }
        }, 0, timer);
    }

    @Override
    public void getCoinList(int start) {
        mDataManager.getCoinList(start);
    }

    @Override
    public void onCoinListLoaded(List<Coin> coinList) {
        if(null != mListener){
            mListener.onCoinListLoaded(coinList);
        }
    }
}
