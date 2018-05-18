package com.myp.myapplication.http;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myp.myapplication.R;
import com.myp.myapplication.api.MD5;
import com.myp.myapplication.api.UserBO;
import com.myp.myapplication.bean.MyHttpBO;
import com.myp.myapplication.h5.H5Activity;
import com.myp.myapplication.mvp.MVPBaseActivity;

import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HttpActivity extends MVPBaseActivity<HttpContract.View, HttpPresenter> implements HttpContract.View {

    private Button bu;
    private TextView te;
    private Button tuibu;
    private Button tiaoH5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        bu = (Button)findViewById(R.id.bu);
        tuibu = (Button)findViewById(R.id.tuibu);
        tiaoH5 = (Button)findViewById(R.id.tiaoH5);
        tiaoH5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HttpActivity.this,H5Activity.class));
            }
        });
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = "zck123";
                mPresenter.MyHttp("13552008150", MD5.strToMd5Low32(password),"");
            }
        });
        tuibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.tuiMyHttp();
            }
        });
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getMyHttp(UserBO myHttpBO) {

    }
}
