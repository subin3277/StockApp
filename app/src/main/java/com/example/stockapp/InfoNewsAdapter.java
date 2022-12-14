package com.example.stockapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class InfoNewsAdapter extends ArrayAdapter<HashMap<Integer, JSONObject>> {
    private HashMap<Integer, JSONObject> items;
    Context context;

    public InfoNewsAdapter(@NonNull Context context, int resource, HashMap<Integer, JSONObject> items){
        super(context, resource);

        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.info_news_list, null, true);

        try {
            TextView title = (TextView) convertView.findViewById(R.id.info_news_title_adp);
            title.setText(items.get(position).getString("title"));

            TextView writer = (TextView) convertView.findViewById(R.id.info_news_writer_adp);
            writer.setText(items.get(position).getString("info"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return convertView;
    }
}
