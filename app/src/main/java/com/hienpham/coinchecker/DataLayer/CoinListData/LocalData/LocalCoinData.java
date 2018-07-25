package com.hienpham.coinchecker.DataLayer.CoinListData.LocalData;

import com.hienpham.coinchecker.Model.Coin;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.realm.Realm;
import io.realm.RealmList;

public class LocalCoinData {
    Realm mRealm;

    public void storeCoinList(List<Coin> coinList){
        mRealm = LocalData.getRealm();
        final RealmList<Coin> realmCoinList = new RealmList<>();
        realmCoinList.addAll(coinList);
        mRealm.executeTransaction(realm -> realm.insertOrUpdate(realmCoinList));
        mRealm.close();
    }

    public Maybe<Coin> getSpecificCoin(final int id) {
            mRealm = LocalData.getRealm();
            mRealm.beginTransaction();
            Coin coin = mRealm.where(Coin.class).equalTo("id", id).findFirst();

            mRealm.commitTransaction();

            return Maybe.create(e -> {
                if(null != coin) {
                    e.onSuccess(coin);
                } else {
                    e.onError(new Exception());
                }
            });
    }
}
