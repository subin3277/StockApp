package com.example.stockapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentHolding extends Fragment {
    private TextView test;
    private String obj;

    public FragmentHolding() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holding, container, false);
        test = view.findViewById(R.id.test);
        if (getArguments() != null)
        {
            obj = getArguments().getString("obj_holding"); // Main에서 받아온 값 넣기
            test.setText(obj);
        }
        return view;

    }


}
