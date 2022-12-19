package com.example.stockapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.CustomViewHolder>{


    private ArrayList<StockData> arrayList;

    public StockAdapter(ArrayList<StockData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public StockAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stocklist.parent, false);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView stock_profile;
        protected TextView stock_name;
        protected TextView stock_price;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.stock_profile = (ImageView) itemView.findViewById(R.id.stock_profile);
            this.stock_name = (TextView) itemView.findViewById(R.id.stock_name);
            this.stock_price = (TextView) itemView.findViewById(R.id.stock_price);
        }
    }
}
