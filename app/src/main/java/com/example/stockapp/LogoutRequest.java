package com.example.stockapp;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

public class LogoutRequest extends JsonObjectRequest {
    final static private String URL = "http://13.124.21.50:8080/api/user/logout";
    private Map mHeaders=new HashMap(1);

    public LogoutRequest(JSONObject jsonRequest, Listener listener) {
        super(Method.POST, URL, jsonRequest, listener, null);
    }

    public void setCookie(String cookie){
        mHeaders.put("Cookie", cookie);
    }

    @Override
    public Map getHeaders() throws AuthFailureError {
        return mHeaders;
    }

}


