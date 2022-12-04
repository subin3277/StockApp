package com.example.stockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.nio.charset.StandardCharsets;

public class SearchActivity extends AppCompatActivity {

    TextView rank1, rank2, rank3, rank4, rank5;
    ListView search_result;
    Button search_button;
    EditText search_keyword;
    LinearLayout linear;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rank1 = findViewById(R.id.search_rank_1);
        rank2 = findViewById(R.id.search_rank_2);
        rank3 = findViewById(R.id.search_rank_3);
        rank4 = findViewById(R.id.search_rank_4);
        rank5 = findViewById(R.id.search_rank_5);

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
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    String result;
                    while ((result = br.readLine()) != null) {
                        sb.append(result).append("\n");
                    }

                    JSONObject contents = new JSONObject(sb.toString());

                    JSONArray object_rank = new JSONArray(contents.getString("contents"));
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

        search_result = findViewById(R.id.search_result);
        search_button = findViewById(R.id.search_btn);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_keyword = findViewById(R.id.search_keyword);
                String keyword = search_keyword.getText().toString();
                new Thread() {
                    @Override
                    public void run() {
                        try {

                            URL url = new URL("http://13.124.21.50:8080/api/search/result?keyWord="+keyword);
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
                            JSONArray object_rank = new JSONArray(contents.getString("contents"));

                            String[] search_res = new String[object_rank.length()];
                            Log.e("1",object_rank.toString());
                            for (int i=0; i < object_rank.length();i++) {
                                JSONObject array_rank = object_rank.getJSONObject(i);
                                String company_name = array_rank.getString("company_name");
                                search_res[i] = company_name;
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    linear = findViewById(R.id.search_linear);
                                    linear.setVisibility(View.GONE);
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, search_res);
                                    search_result.setAdapter(adapter);

                                    search_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            // 항목 클릭시 나올 화면
                                        }
                                    });
                                }
                            });


                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}