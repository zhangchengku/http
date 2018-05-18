package com.myp.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/5/10.
 */
public class NextActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String value = getIntent().getExtras().getString("selectedBook");
        TextView textView = new TextView(this);
        textView.setText(value);
        setContentView(textView);
    }
}
