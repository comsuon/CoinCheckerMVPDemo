package com.hienpham.coinchecker.CoinPopup;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.R;

public class CoinPopup extends AlertDialog implements CoinPopupContract.CoinPopupView{
    View rootView;
    TextView tvCoinName;
    TextView tvCoinPrice;
    CoinPopupContract.CoinPopupPresenter mPresenter;

    public CoinPopup(Context context, int coinId) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mPresenter = new CoinPopupPresenterImp(coinId);
        mPresenter.attachView(this);
        mPresenter.getCoin(coinId);
    }

    @Override
    public void showPumpFace(Coin coin) {
        initView(R.layout.pumping_popup_layout);

        String coinName = String.format(getContext().getString(R.string.coin_pumping), coin.getName(), coin.getQuotes().getUSD().percent_change_1h);
        String coinPrice = String.format(getContext().getString(R.string.price), coin.getQuotes().getUSD().getPrice());

        this.setText(coinName, coinPrice);
        this.setView(rootView);
        this.show();

    }

    @Override
    public void showDumpFace(Coin coin) {
        initView(R.layout.dumping_popup_layout);

        String coinName = String.format(getContext().getString(R.string.coin_dumping), coin.getName(), coin.getQuotes().getUSD().percent_change_1h);
        String coinPrice = String.format(getContext().getString(R.string.price), coin.getQuotes().getUSD().getPrice());

        this.setText(coinName, coinPrice);

        this.setView(rootView);
        this.show();
    }

    private void initView(int layoutId){
        rootView = LayoutInflater.from(getContext()).inflate(layoutId,null);

        tvCoinName = rootView.findViewById(R.id.coinName);
        tvCoinPrice = rootView.findViewById(R.id.price);
    }

    private void setText(String coinName, String coinPrice){
        tvCoinName.setText(coinName);
        tvCoinPrice.setText(coinPrice);
    }
}
