package com.example.stockapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

//    EditText id, pwd;
//    Button login_btn;
    Button btn_wishlist, btn_holding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_wishlist = (Button) findViewById(R.id.btn_wishlist);
        btn_holding = (Button) findViewById(R.id.btn_holding);

        btn_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FragmentWishlist fragmentWishlist = new FragmentWishlist();
                transaction.replace(R.id.frame, fragmentWishlist);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btn_holding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FragmentHolding fragmentHolding = new FragmentHolding();
                transaction.replace(R.id.frame, fragmentHolding);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


    }

}