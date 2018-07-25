package com.hienpham.coinchecker.CoinList;

import com.hienpham.coinchecker.DataLayer.CoinListData.CoinListRepository;
import com.hienpham.coinchecker.DataLayer.CoinListData.CoinListRepositoryImp;
import com.hienpham.coinchecker.Model.CoinListRes;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

public class CoinListModelImp implements CoinListModel {

    CoinListRepository mDataManager;
    CoinListContract.CoinListPresenter mPresenter;

    public CoinListModelImp(CoinListContract.CoinListPresenter presenter){
        this.mPresenter = presenter;
        mDataManager = new CoinListRepositoryImp();
    }

    @Override
    public Flowable<CoinListRes> getCoinListInterval(final int start, final long timer) {
        return Flowable.interval(0,timer, TimeUnit.MINUTES)
                .flatMap(t -> mDataManager.getCoinList(start));
    }

    @Override
    public Flowable<CoinListRes> getCoinList(int start) {
        return mDataManager.getCoinList(start);
    }
}
