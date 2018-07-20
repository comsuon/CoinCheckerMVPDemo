
package com.hienpham.coinchecker.CoinList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.hienpham.coinchecker.BaseActivity;
import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.R;

import java.util.List;

public class CoinListActivity extends BaseActivity implements CoinListContract.CoinListView {

    CoinListContract.CoinListPresenter mPresenter;

    @Override
    public void showLoading() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);
        setUpToolbar((Toolbar) findViewById(R.id.toolbar));
        setTitle(R.string.app_name);
        initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null == mPresenter) initPresenter();

        mPresenter.onResume();
    }

    @Override
    public void hideLoading() {}


    @Override
    public void populateCoinlist(List<Coin> coinList) {
        Toast.makeText(this, "Coin List Loaded!", Toast.LENGTH_SHORT).show();
    }

    private void initPresenter(){
        mPresenter = new CoinListPresenterImp();
        mPresenter.attachView(this);
    }

}
