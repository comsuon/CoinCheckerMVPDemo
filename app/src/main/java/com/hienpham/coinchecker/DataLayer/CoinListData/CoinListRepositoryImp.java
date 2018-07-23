package com.hienpham.coinchecker.DataLayer.CoinListData;

import com.hienpham.coinchecker.Core.Thread.AppExecutor;
import com.hienpham.coinchecker.DataLayer.CoinListData.LocalData.LocalCoinData;
import com.hienpham.coinchecker.DataLayer.CoinListData.LocalData.LocalData;
import com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData.RemoteCoinService;
import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.Model.CoinListRes;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class CoinListRepositoryImp implements CoinListRepository {
    RemoteCoinService mCoinService;
    CoinListRepository.CoinListDataCallbacks mListener;
    LocalCoinData mCoinLocal;

    AppExecutor mExecutor;

    public CoinListRepositoryImp(CoinListDataCallbacks listener) {
        mCoinService = new RemoteCoinService();
        mCoinLocal = new LocalCoinData();
        mListener = listener;
        mExecutor = new AppExecutor();
    }

    @Override
    public void getCoinList(final int start) {
        mExecutor.getNetworkExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mCoinService.fetchCoinList(start, new RemoteCoinService.GetCoinListCallbacks() {
                    @Override
                    public void onRemoteCoinListLoaded(final List<Coin> coinList) {
                        mCoinLocal.storeCoinList(coinList);

                        if(null != mListener) {
                            mExecutor.getMainThread().execute(new Runnable() {
                                @Override
                                public void run() {
                                    mListener.onCoinListLoaded(coinList);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    @Override
    public Coin getSpecificCoin(int id) {
        return mCoinLocal.getSpecificCoin(id);
    }
}
