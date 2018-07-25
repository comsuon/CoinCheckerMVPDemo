
package com.hienpham.coinchecker.CoinList;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.hienpham.coinchecker.BaseActivity;
import com.hienpham.coinchecker.CoinPopup.CoinPopup;
import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.R;

import java.util.List;

import io.realm.Realm;

public class CoinListActivity extends BaseActivity implements CoinListContract.CoinListView {

    CoinListContract.CoinListPresenter mPresenter;

    ContentLoadingProgressBar mProgressBar;

    RecyclerView mRecyclerView;

    SwipeRefreshLayout mRefreshLayout;

    @Override
    public void showLoading() {
        if(null != mProgressBar) mProgressBar.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Realm.init(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);
        setUpToolbar(findViewById(R.id.toolbar));
        setTitle(R.string.app_name);

        initPresenter();

        mProgressBar = findViewById(R.id.progressBar);

        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CoinListAdapter(this, (view, coin, position) -> new CoinPopup(this).showPopup(coin.getId())));

        mRefreshLayout = findViewById(R.id.refreshLayout);

        mRefreshLayout.setOnRefreshListener(() -> {
            mRefreshLayout.setRefreshing(false);
            mPresenter.onRefreshCoinList();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null != mPresenter) initPresenter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.detachView();
    }

    @Override
    public void hideLoading() {
        if(null != mProgressBar) mProgressBar.hide();
    }

    @Override
    public void populateCoinlist(List<Coin> coinList) {
        Toast.makeText(this, "Coin List Loaded!", Toast.LENGTH_SHORT).show();

        if(null != this.mRecyclerView && null != this.mRecyclerView.getAdapter()) {
            ((CoinListAdapter)this.mRecyclerView.getAdapter()).setData(coinList);
        }
    }

    @Override
    public void refreshCoinList(List<Coin> coinList) {
        if(null != this.mRecyclerView && null != this.mRecyclerView.getAdapter()) {
            ((CoinListAdapter)this.mRecyclerView.getAdapter()).refreshData(coinList);
        }
    }

    private void initPresenter(){
        mPresenter = new CoinListPresenterImp();
        mPresenter.attachView(this);
        mPresenter.initCoinList();
    }

}
