package com.recyclerviewadaptermanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.recyclerviewadaptermanager.ui.ExpandedActivity;
import com.recyclerviewadaptermanager.ui.GridLayoutActivity;
import com.recyclerviewadaptermanager.ui.GroupActivity;
import com.recyclerviewadaptermanager.ui.LinearLayoutActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void linearLayoutAdapter(View v){
        startActivity(new Intent(this,LinearLayoutActivity.class));
    }
    public void gridLayoutAdapter(View v){
        startActivity(new Intent(this,GridLayoutActivity.class));
    }
    public void expandedAdapter(View v){
        startActivity(new Intent(this,ExpandedActivity.class));
    }
    public void group(View view) {
        startActivity(new Intent(this,GroupActivity.class));
    }
}
