package com.recyclerviewadaptermanager.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.recyclerviewadaptermanager.Data;
import com.recyclerviewadaptermanager.R;
import com.recyclerviewadaptermanager.adapter.AdapterManager;
import com.recyclerviewadaptermanager.adapter.GridLayoutAdapter;
import com.recyclerviewadaptermanager.adapter.ItemDecoration;
import com.recyclerviewadaptermanager.adapter.ViewHolder;

/**
 * @author xiongxiang
 * @time 2018/8/24.
 * @e-mail 276186694@qq.com
 */
public class GridLayoutActivity extends BaseActivity {
    @Override
    public String getText() {
        return "GridLayoutAdapter";
    }

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = findViewById(R.id.rv);
        AdapterManager adapterManager = new AdapterManager();
        mAdapter.addData(getData(50, "GridLayout"));
        adapterManager.addAdapter(mAdapter);
        adapterManager.bindRecyclerView(mRecyclerView, this);


        //定义item 间隔
        mAdapter.setItemDecoration(new ItemDecoration() {
            private int rect = 10;

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state, int position) {
                outRect.top = 5;
                if (position % 3 == 1) {
                    outRect.left = rect;
                    outRect.right = rect;
                }
                if (position % 3 == 0) {
                    outRect.left = rect;
                }
                if (position % 3 == 2) {
                    outRect.right = rect;
                }
            }
        });
    }

    public GridLayoutAdapter mAdapter = new GridLayoutAdapter<Data>(3) {
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
