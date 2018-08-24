package com.recyclerviewadaptermanager.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.recyclerviewadaptermanager.Data;
import com.recyclerviewadaptermanager.R;
import com.recyclerviewadaptermanager.adapter.AdapterManager;
import com.recyclerviewadaptermanager.adapter.ItemDecoration;
import com.recyclerviewadaptermanager.adapter.LinearLayoutAdapter;
import com.recyclerviewadaptermanager.adapter.ViewHolder;

/**
 * @author xiongxiang
 * @time 2018/8/24.
 * @e-mail 276186694@qq.com
 */
public class LinearLayoutActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView = findViewById(R.id.rv);

        AdapterManager adapterManager = new AdapterManager();
        mAdapter.addData(getData(20, "LinearLayout"));
        adapterManager.addAdapter(mAdapter);
        mAdapter.setItemDecoration(new ItemDecoration() {
            private int rect=10;

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state, int position) {
                if (position != 0) {
                    outRect.top = rect;
                }
                if (position ==mAdapter.getItemCount()-1){
                    outRect.bottom=rect;
                }
            }
        });


        adapterManager.bindRecyclerView(mRecyclerView, this);
        adapterManager.notifyDataSetChanged();


    }

    @Override
    public String getText() {
        return "LinearLayoutAdapter";
    }

    public LinearLayoutAdapter mAdapter = new LinearLayoutAdapter<Data>() {
        @Override
        public int getLayoutId() {
            return R.layout.adapter_item;
        }

        @Override
        public void convert(ViewHolder holder, int position, Data data) {
            holder.setText(R.id.tv_text, data.getText() + "  " + position);
        }
    };


}
