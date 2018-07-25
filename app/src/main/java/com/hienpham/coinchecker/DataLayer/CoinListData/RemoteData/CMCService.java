package com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData;

import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.Model.CoinListRes;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CMCService{

    @Headers("Cache-Control: no-cache")
    @GET("ticker/?structure=array")
    Single<CoinListRes> getCoinList(@Query("start") int start);


    @GET("ticker/")
    Single<Coin> getSpecificCoin(@Query("id") int coinID);
}
