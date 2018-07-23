package com.hienpham.coinchecker.CoinList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hienpham.coinchecker.Model.Coin;
import com.hienpham.coinchecker.R;
import com.hienpham.coinchecker.Utils.CoinIconLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoinListAdapter extends Adapter<CoinListAdapter.CoinViewHolder> {
    List<Coin> mData;
    Context mContext;
    OnItemClickListener mItemClickListener;

    public CoinListAdapter (Context context, OnItemClickListener listener) {
        this.mContext = context;
        this.mData = new ArrayList<>();
        this.mItemClickListener = listener;
    }

    public void setData(@NonNull List<Coin> listCoin) {
        if(null == this.mData || listCoin.size() == 0 ) return;

        this.mData.addAll(listCoin);
        notifyDataSetChanged();
    }

    public void refreshData(@NonNull List<Coin> listCoin) {
        if (null == this.mData) mData = new ArrayList<>();

        this.mData.clear();
        this.mData.addAll(listCoin);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(mContext).inflate(R.layout.coin_list_item, parent,false);
        return new CoinViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder holder, int position) {
        holder.renderRowView(this.mData.get(position));
        holder.setOnClickListener(mItemClickListener, this.mData.get(position), position);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class CoinViewHolder extends RecyclerView.ViewHolder{
        View rootView;

        ImageView icon;
        TextView coinName;
        TextView coinPrice;
        TextView changeIn24h;
        TextView lastUpdated;

        public CoinViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;

            icon = rootView.findViewById(R.id.coinIcon);
            coinName = rootView.findViewById(R.id.coinName);
            coinPrice = rootView.findViewById(R.id.coinPrice);
            changeIn24h = rootView.findViewById(R.id.changeIn24h);
            lastUpdated = rootView.findViewById(R.id.updated);
        }

        public void renderRowView(Coin rowData) {
            CoinIconLoader.loadIcon(mContext,rowData.getId(),this.icon);
            coinName.setText(rowData.getName());
            coinPrice.setText("$ " + String.valueOf(rowData.getQuotes().getUSD().getPrice()));
            changeIn24h.setText(String.valueOf(rowData.getQuotes().getUSD().getPercent_change_24h()) + "% (24h)");

            this.changeIn24h.setTextColor(ContextCompat.getColor(mContext, R.color.lightControl));

            if (rowData.getQuotes().getUSD().getPercent_change_24h() < 0) {
                this.changeIn24h.setTextColor(ContextCompat.getColor(mContext,R.color.dumpRed));
            }

            //last updated
            Date date = new Date(rowData.getLast_updated() * 1000L);
            String formattedDate = new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(date);

            this.lastUpdated.setText(formattedDate);
        }

        public void setOnClickListener(final OnItemClickListener listener, final Coin coin, final int position) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, coin, position);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, Coin coin, int position);
    }
}
