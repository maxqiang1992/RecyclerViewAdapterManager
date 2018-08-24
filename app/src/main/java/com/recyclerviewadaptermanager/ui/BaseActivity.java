package com.recyclerviewadaptermanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.recyclerviewadaptermanager.Data;
import com.recyclerviewadaptermanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongxiang
 * @time 2018/8/24.
 * @e-mail 276186694@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_base_layout);
        TextView textView = findViewById(R.id.tv_title);
        textView.setText(getText());
    }

    public abstract String getText();

    public List<Data> getData(int length,String  text) {
        List<Data> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(new Data(text));
        }
        return list;
    }
}
