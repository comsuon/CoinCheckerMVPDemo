package com.hienpham.coinchecker.DataLayer.CoinListData.LocalData;

import io.realm.Realm;

public class LocalData {
    /**
     * Return a default Realm Instance
     * To support multiple configuration by overload the method getRealm() with configuration passed as parameter
     */

    public static Realm getRealm() {
        return Realm.getDefaultInstance();
    }
}
