package com.yf.myapplicationdemo.baseadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.yf.myapplicationdemo.R;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapterActivity extends AppCompatActivity {

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);

        listview = (ListView) findViewById(R.id.lv);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add("" + i);
        }
        MyAdapter myAdapter = new MyAdapter(strings, this, R.layout.item);
        listview.setAdapter(myAdapter);

    }
}
