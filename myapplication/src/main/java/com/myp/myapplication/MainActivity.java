package com.myp.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;


public class MainActivity extends AppCompatActivity  {


    private SwipeToLoadLayout sss;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 布局文件，直接从github考过来的
        setContentView(R.layout.activity_main);
       sss =  (SwipeToLoadLayout)findViewById(R.id.swipeToLoadLayout);
        sss.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("asdfasd", "onRefresh: ");
                sss.setRefreshing(false);
            }
        });
    }
}

