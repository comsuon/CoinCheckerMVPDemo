package com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData;

import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.Model.CoinListRes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CMCService{


    @GET("ticker/?structure=array")
    Call<CoinListRes> getCoinList(@Query("start") int start);


    @GET("ticker/")
    Call<Coin> getSpecificCoin(@Query("id") int coinID);
}
