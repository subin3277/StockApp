package com.example.stockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class InfoActivity extends AppCompatActivity {

    private TextView info_title;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        String stock_code = intent.getStringExtra("stock_code");

        info_title = findViewById(R.id.info_title);

        new Thread() {
            @Override
            public void run() {
                try {

                    URL url = new URL("http://13.124.21.50:8080/api/stock/info/"+stock_code);
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

                    JSONObject contents = new JSONObject(sb.toString());
                    JSONObject content = new JSONObject(contents.getString("contents"));
                    String company_title = content.getString("company_name");

                    //뉴스 가져오기
                    url = new URL("http://13.124.21.50:8080/api/stock/news/"+stock_code);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET"); //전송방식
                    connection.setDoOutput(false);       //데이터를 쓸 지 설정
                    connection.setDoInput(true);        //데이터를 읽어올지 설정

                    is = connection.getInputStream();
                    sb = new StringBuilder();
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    while ((result = br.readLine()) != null) {
                        sb.append(result).append("\n");
                    }

                    contents = new JSONObject(sb.toString());
                    JSONArray newslist = new JSONArray(contents.getString("contents"));

                    HashMap<Integer, JSONObject> items = new HashMap<Integer, JSONObject>();
                    ListView newslistview = findViewById(R.id.info_news);

                    for (int i=0;i<newslist.length();i++){
                        items.put(i, newslist.getJSONObject(i));
                    }
                    InfoNewsAdapter adapter = new InfoNewsAdapter(InfoActivity.this, 0, items);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            info_title.setText(company_title);
                            newslistview.setAdapter(adapter);
                        }
                    });
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
}