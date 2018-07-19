
package com.hienpham.coinchecker.CoinList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.R;

import java.util.List;

public class CoinListActivity extends AppCompatActivity implements CoinListContract.CoinListView {

    CoinListContract.CoinListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);
        initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null == mPresenter) initPresenter();

        mPresenter.onResume();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void populateCoinlist(List<Coin> coinList) {

    }

    private void initPresenter(){
        mPresenter = new CoinListPresenterImp();
        mPresenter.attachView(this);
    }
}
