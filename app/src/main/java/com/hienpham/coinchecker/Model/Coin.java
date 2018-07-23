package com.hienpham.coinchecker.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Coin extends RealmObject{
    @PrimaryKey
    int id;
    String name;
    int rank;
    Quotes quotes;
    long last_updated;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public Quotes getQuotes() {
        return quotes;
    }

    public long getLast_updated() {
        return last_updated;
    }
}

