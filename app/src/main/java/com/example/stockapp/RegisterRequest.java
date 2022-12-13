package com.example.stockapp;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    // 서버 URL 설정
    final static private String URL = "http://13.124.21.50:8080/api/user/join";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPass, String userPass2, String userPin, String userName, String userNickname, String userEmail, String userPhone, String userAddress, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("id", userID);
        map.put("password", userPass);
        map.put("password_confirm", userPass2);
        map.put("simple_pwd", userPin);
        map.put("name", userName);
        map.put("nick_name", userNickname);
        map.put("email", userEmail);
        map.put("phone_number", userPhone);
        map.put("address", userAddress);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
