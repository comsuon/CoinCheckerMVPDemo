package com.hienpham.coinchecker.DataLayer.CoinListData;

import com.hienpham.coinchecker.Core.Thread.AppExecutor;
import com.hienpham.coinchecker.DataLayer.CoinListData.LocalData.LocalCoinData;
import com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData.RemoteCoinService;
import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.Model.CoinListRes;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CoinListRepositoryImp implements CoinListRepository {
    RemoteCoinService mCoinService;
    LocalCoinData mCoinLocal;

    AppExecutor mExecutor;

    public CoinListRepositoryImp() {
        mCoinService = new RemoteCoinService();
        mCoinLocal = new LocalCoinData();
        mExecutor = new AppExecutor();
    }

    @Override
    public Flowable<CoinListRes> getCoinList(final int start) {
        return mCoinService.fetchCoinList(start)
                .toFlowable()
                .doOnNext(coinListRes ->  mExecutor.getNetworkExecutor().execute(() -> mCoinLocal.storeCoinList(coinListRes.getCoinList())))
                .subscribeOn(Schedulers.from(mExecutor.getNetworkExecutor()))
                .observeOn(Schedulers.from(mExecutor.getMainThread()));
    }

    @Override
    public Maybe<Coin> getSpecificCoin(int id) {
        return mCoinLocal.getSpecificCoin(id);
    }
}
