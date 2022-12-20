package com.example.stockapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    Button btn_wishlist, btn_holding;
    public static JSONArray object_wishlist;
    public static JSONArray object_holding;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_wishlist = (Button) findViewById(R.id.btn_wishlist);
        btn_holding = (Button) findViewById(R.id.btn_holding);
        Intent intent = getIntent();
        String sessionid = intent.getStringExtra("sessionid");





//        관심주식목록 가져오기
        new Thread() {
            @Override
            public void run() {
                try {
                    String sorting_method = "all";
                    URL url = new URL("http://13.124.21.50:8080/api/user/stock/wishlist?sorting_method="+sorting_method);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Cookie", sessionid);
                    connection.setRequestMethod("GET"); //전송방식
                    connection.setDoOutput(false);       //데이터를 쓸 지 설정
                    connection.setDoInput(true);        //데이터를 읽어올지 설정

                    InputStream is = connection.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    String result;
                    while ((result = br.readLine()) != null) {
                        sb.append(result).append("\n");
                    }

                    JSONObject contents = new JSONObject(sb.toString());

                    object_wishlist = new JSONArray(contents.getString("contents"));
                    Log.d("hahaha", "관심주식목록!!!!!" + object_wishlist);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    Log.d("hahaha", "관심주식목록에러" + e);
                }
            }
        }.start();

        //        보유주식목록 가져오기
        new Thread() {
            @Override
            public void run() {
                try {
                    String sorting_method = "all";
                    URL url = new URL("http://13.124.21.50:8080/api/user/stock/holding?sorting_method="+sorting_method);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Cookie", sessionid);
                    connection.setRequestMethod("GET"); //전송방식
                    connection.setDoOutput(false);       //데이터를 쓸 지 설정
                    connection.setDoInput(true);        //데이터를 읽어올지 설정

                    InputStream is = connection.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    String result;
                    while ((result = br.readLine()) != null) {
                        sb.append(result).append("\n");
                    }

                    JSONObject contents = new JSONObject(sb.toString());

                    object_holding = new JSONArray(contents.getString("contents"));
                    Log.d("hahaha", "보유주식목록!!!!!" + object_holding);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    Log.d("hahaha", "보유주식목록에러" + e);
                }
            }
        }.start();



        btn_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                Gson gson = new Gson();
                String obj_wishlist = gson.toJson(object_wishlist);
                bundle.putString("obj_wishlist", obj_wishlist);//번들에 넘길 값 저장

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FragmentWishlist fragmentWishlist = new FragmentWishlist();
                fragmentWishlist.setArguments(bundle);
                transaction.replace(R.id.frame, fragmentWishlist);
                transaction.commit();



            }
        });

        btn_holding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                Gson gson = new Gson();
                String obj_holding = gson.toJson(object_holding);
                bundle.putString("obj_holding", obj_holding);//번들에 넘길 값 저장

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FragmentHolding fragmentHolding = new FragmentHolding();
                fragmentHolding.setArguments(bundle);
                transaction.replace(R.id.frame, fragmentHolding);
                transaction.commit();
            }
        });


    }

}