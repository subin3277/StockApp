package com.example.stockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoActivity extends AppCompatActivity {

    private TextView tv_id, btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tv_id = findViewById(R.id.tv_id);
        btn_logout = findViewById(R.id.btn_logout);


        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        tv_id.setText(userID);




        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("hahaha", response);
                            JSONObject jsonObject = new JSONObject(response);
                            String responseWord = jsonObject.getString("response");
                            if (responseWord.equals("success_logout")) { // 로그아웃에 성공한 경우
                                Log.d("hahaha", "로그아웃 성공!!!!!");
                                Toast.makeText(getApplicationContext(), "로그아웃에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(InfoActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else { // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(), "로그아웃에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LogoutRequest logoutRequest = new LogoutRequest(responseListener);
                RequestQueue queue = Volley.newRequestQueue(InfoActivity.this);
                queue.add(logoutRequest);
            }
        });
    }
}