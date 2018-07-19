package com.hienpham.coinchecker.DataLayer.CoinListData.RemoteData;

import com.hienpham.coinchecker.Model.Coin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CMCService{

    @GET("ticker/?{start}&structure=array")
    Call<List<Coin>> getCoinList(@Path("start") int start);


    @GET("ticker/?{id}/")
    Call<Coin> getSpecificCoin(@Path("id") int coinID);
}
