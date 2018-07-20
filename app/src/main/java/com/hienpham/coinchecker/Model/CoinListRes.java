package com.hienpham.coinchecker.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoinListRes {
    @SerializedName("data")
    List<Coin> coinList;

    public List<Coin> getCoinList() {
        return coinList;
    }
}
