package com.hienpham.coinchecker.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CoinIconLoader {

    private final static String COIN_ICON_URL = "https://s2.coinmarketcap.com/static/img/coins/32x32/";

    public static void loadIcon(Context context, int id, ImageView imv){
        String coinUrl = COIN_ICON_URL + String.valueOf(id) + ".png";
        Glide.with(context).load(coinUrl).into(imv);
    }
}
