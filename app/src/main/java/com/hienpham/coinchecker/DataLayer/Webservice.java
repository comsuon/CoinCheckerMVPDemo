package com.hienpham.coinchecker.DataLayer;

import com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData.CMCService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Webservice {
    Retrofit.Builder retrofitBuilder;

    public Webservice () {
        retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    }

    public CMCService getCoinService() {
        return retrofitBuilder
                .baseUrl(" https://api.coinmarketcap.com/v2/")
                .build().create(CMCService.class);
    }
}