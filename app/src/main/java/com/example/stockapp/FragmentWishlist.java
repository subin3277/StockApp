package com.example.stockapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentWishlist extends Fragment {
    private TextView test;
    private String obj;


    public FragmentWishlist() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        test = view.findViewById(R.id.test);
        if (getArguments() != null)
        {
            obj = getArguments().getString("obj_wishlist"); // 프래그먼트1에서 받아온 값 넣기
            test.setText(obj);
        }
        return view;
    }






}
