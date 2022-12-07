package com.example.stockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText id, pwd;
    Button login_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.login_tmp_id);
        pwd = findViewById(R.id.login_tmp_pwd);
        login_btn = findViewById(R.id.login_tmp_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idtxt = id.getText().toString();
                String pwdtxt = pwd.getText().toString();

                new Thread() {
                    @Override
                    public void run() {
                        try {

                            URL url = new URL("http://13.124.21.50:8080/api/user/login");
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("POST"); //전송방식
                            connection.setDoOutput(true);       //데이터를 쓸 지 설정
                            connection.setDoInput(true);        //데이터를 읽어올지 설정

                            JSONObject jsonObject = new JSONObject();

                            try {
                                jsonObject.put("id",idtxt);
                                jsonObject.put("password",pwdtxt);

                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                            Log.e("json","생성한 json"+jsonObject.toString());

                            OutputStream outputStream = connection.getOutputStream();
                            outputStream.write(jsonObject.toString().getBytes());
                            outputStream.flush();

                            String response;
                            int responseCode = connection.getResponseCode();

                            if(responseCode == HttpURLConnection.HTTP_OK) {
                                InputStream inputStream = connection.getInputStream();
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                byte[] byteBuffer = new byte[1024];
                                byte[] byteData = null;
                                int nLength = 0;
                                while((nLength = inputStream.read(byteBuffer, 0, byteBuffer.length)) != -1) {
                                    baos.write(byteBuffer, 0, nLength);
                                }
                                byteData = baos.toByteArray(); response = new String(byteData);

                                response = new String(byteData);

                                try {
                                    JSONObject responseJSON = new JSONObject(response);

                                    Log.e("json", responseJSON.toString());

                                    Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                                    startActivity(intent);
                                    finish();

                                } catch (JSONException e){
                                    e.printStackTrace();
                                }

                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });


    }

}