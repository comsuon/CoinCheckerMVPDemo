package com.hienpham.coinchecker.Model;

import io.realm.RealmObject;

public class Quotes extends RealmObject {
    Currency USD;

    public Currency getUSD() {
        return USD;
    }
}
