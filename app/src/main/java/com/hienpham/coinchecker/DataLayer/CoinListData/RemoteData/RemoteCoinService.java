package com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData;

import com.hienpham.coinchecker.Core.Thread.AppExecutor;
import com.hienpham.coinchecker.DataLayer.Webservice;
import com.hienpham.coinchecker.Model.Coin;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class RemoteCoinService {
    AppExecutor mExecutor;
    CMCService mCoinService;

    public RemoteCoinService() {
        mExecutor = new AppExecutor();
        mCoinService = new Webservice().getCoinService();
    }

    public void getCoinList(final int start, final GetCoinListCallbacks listener) {
        mExecutor.getNetworkExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<List<Coin>> response = mCoinService.getCoinList(start).execute();
                    listener.onRemoteCoinListLoaded(response.body());
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
}
