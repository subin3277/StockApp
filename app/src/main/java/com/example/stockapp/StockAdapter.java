package com.example.stockapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stocklist,parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.CustomViewHolder holder, int position) {
        holder.stock_profile.setImageResource(arrayList.get(position).getStock_profile());
        holder.stock_name.setText(arrayList.get(position).getStock_name());
        holder.stock_price.setText(arrayList.get(position).getStock_price());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curName = holder.stock_name.getText().toString();
                Toast.makeText(v.getContext(), curName, Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
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
