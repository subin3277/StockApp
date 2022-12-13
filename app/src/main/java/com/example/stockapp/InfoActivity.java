package com.example.stockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoActivity extends AppCompatActivity {

    private TextView tv_id, tv_test;
    private Button btn_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tv_id = findViewById(R.id.tv_id);
        btn_logout = findViewById(R.id.btn_logout);
        tv_test = findViewById(R.id.tv_test);


        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String sessionid = intent.getStringExtra("sessionid");
        Log.e("hahaha", "@@@@@@@@@" + sessionid);

        tv_id.setText(userID);
        tv_test.setText(sessionid);




        // 로그아웃
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}