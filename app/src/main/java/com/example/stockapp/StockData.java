package com.example.stockapp;

public class StockData {

    private int stock_profile;
    private String stock_name;
    private String stock_price;

    public StockData(int stock_profile, String stock_name, String stock_price) {
        this.stock_profile = stock_profile;
        this.stock_name = stock_name;
        this.stock_price = stock_price;
    }

    public int getStock_profile() {
        return stock_profile;
    }

    public void setStock_profile(int stock_profile) {
        this.stock_profile = stock_profile;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getStock_price() {
        return stock_price;
    }

    public void setStock_price(String stock_price) {
        this.stock_price = stock_price;
    }
}
