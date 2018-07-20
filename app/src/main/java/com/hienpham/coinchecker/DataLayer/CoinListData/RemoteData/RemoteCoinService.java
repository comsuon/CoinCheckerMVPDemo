package com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData;

import com.hienpham.coinchecker.Core.Thread.AppExecutor;
import com.hienpham.coinchecker.DataLayer.Webservice;
import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.Model.CoinListRes;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class RemoteCoinService {

    public static int CMC_START_DEFAULT = 1;

    AppExecutor mExecutor;
    CMCService mCoinService;

    public RemoteCoinService() {
        mExecutor = new AppExecutor();
        mCoinService = new Webservice().getCoinService();
    }

    public void fetchCoinList(final int start, final GetCoinListCallbacks listener) {
        mExecutor.getNetworkExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final Response<CoinListRes> response = mCoinService.getCoinList(start).execute();
                    if(null != listener) {
                        mExecutor.getMainThread().execute(new Runnable() {
                            @Override
                            public void run() {
                                listener.onRemoteCoinListLoaded(response.body().getCoinList());
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getSpecificCoin(int id) {

    }

    public interface GetCoinListCallbacks {
        void onRemoteCoinListLoaded (List<Coin> coinList);
    }

    public interface GetSpecificCoinCallbacks {
        void onRemoteSpecificCoinLoaded(Coin coin);
    }
}
