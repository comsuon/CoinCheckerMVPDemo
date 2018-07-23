package com.hienpham.coinchecker.DataLayer.CoinListData.LocalData;

import com.hienpham.coinchecker.Model.Coin;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class LocalCoinData {
    Realm mRealm;

    public void storeCoinList(List<Coin> coinList){
        mRealm = LocalData.getRealm();
        final RealmList<Coin> realmCoinList = new RealmList<>();
        realmCoinList.addAll(coinList);
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(realmCoinList);
            }
        });
        mRealm.close();
    }

    public Coin getSpecificCoin(final int id){
        mRealm = LocalData.getRealm();
        mRealm.beginTransaction();
        Coin coin = mRealm.where(Coin.class).equalTo("id", id).findFirst();
        mRealm.commitTransaction();
        return coin;
    }
}
