
package com.hienpham.coinchecker.CoinList;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hienpham.coinchecker.BaseActivity;
import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.R;

import java.util.List;

public class CoinListActivity extends BaseActivity implements CoinListContract.CoinListView {

    CoinListContract.CoinListPresenter mPresenter;

    ContentLoadingProgressBar mProgressBar;

    RecyclerView mRecyclerView;

    @Override
    public void showLoading() {
        if(null != mProgressBar && !mProgressBar.isShown()) mProgressBar.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);
        setUpToolbar((Toolbar) findViewById(R.id.toolbar));
        setTitle(R.string.app_name);
        initPresenter();

        mProgressBar = findViewById(R.id.progressBar);

        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CoinListAdapter(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null == mPresenter) initPresenter();

        mPresenter.onResume();
    }

    @Override
    public void hideLoading() {
        if(null != mProgressBar && mProgressBar.isShown()) mProgressBar.hide();
    }

    @Override
    public void populateCoinlist(List<Coin> coinList) {
        Toast.makeText(this, "Coin List Loaded!", Toast.LENGTH_SHORT).show();

        if(null != this.mRecyclerView && null != this.mRecyclerView.getAdapter()) {
            ((CoinListAdapter)this.mRecyclerView.getAdapter()).setData(coinList);
        }
    }

    private void initPresenter(){
        mPresenter = new CoinListPresenterImp();
        mPresenter.attachView(this);
    }

}
