package com.example.stockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.HttpAuthHandler;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SearchActivity extends AppCompatActivity {

    TextView rank1, rank2, rank3, rank4, rank5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rank1 = findViewById(R.id.seach_rank_1);
        rank2 = findViewById(R.id.seach_rank_2);
        rank3 = findViewById(R.id.seach_rank_3);
        rank4 = findViewById(R.id.seach_rank_4);
        rank5 = findViewById(R.id.seach_rank_5);

        new Thread() {
            @Override
            public void run() {
                try {

                    URL url = new URL("http://13.124.21.50:8080/api/search/view-cnt-ranking");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET"); //전송방식
                    connection.setDoOutput(false);       //데이터를 쓸 지 설정
                    connection.setDoInput(true);        //데이터를 읽어올지 설정

                    InputStream is = connection.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String result;
                    while ((result = br.readLine()) != null) {
                        sb.append(result).append("\n");
                    }

                    JSONArray object_rank = new JSONArray(sb.toString());
                    JSONObject array_rank = object_rank.getJSONObject(0);
                    String company_name1 = array_rank.getString("company_name");
                    rank1.setText("1. " + company_name1);
                    rank1.setTextSize(16);

                    array_rank = object_rank.getJSONObject(1);
                    String company_name2 = array_rank.getString("company_name");
                    rank2.setText("2. " + company_name2);
                    rank2.setTextSize(16);

                    array_rank = object_rank.getJSONObject(2);
                    String company_name3 = array_rank.getString("company_name");
                    rank3.setText("3. " + company_name3);
                    rank3.setTextSize(16);

                    array_rank = object_rank.getJSONObject(3);
                    String company_name4 = array_rank.getString("company_name");
                    rank4.setText("4. " + company_name4);
                    rank4.setTextSize(16);

                    array_rank = object_rank.getJSONObject(4);
                    String company_name5 = array_rank.getString("company_name");
                    rank5.setText("5. " + company_name5);
                    rank5.setTextSize(16);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

}