package com.recyclerviewadaptermanager.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.recyclerviewadaptermanager.Data;
import com.recyclerviewadaptermanager.R;
import com.recyclerviewadaptermanager.adapter.AdapterManager;
import com.recyclerviewadaptermanager.adapter.ExpandedAdapter;
import com.recyclerviewadaptermanager.adapter.ItemDecoration;
import com.recyclerviewadaptermanager.adapter.ViewHolder;

/**
 * @author xiongxiang
 * @time 2018/8/24.
 * @e-mail 276186694@qq.com
 */
public class ExpandedActivity extends BaseActivity {
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
        mAdapter.addData(getData(10, "GridLayout"));
        //更新数据 一定要调用一次  计算位置
        mAdapter.notifyPosition();
        adapterManager.addAdapter(mAdapter);
        adapterManager.bindRecyclerView(mRecyclerView, this);


        //定义item 间隔
        mAdapter.setItemDecoration(new ItemDecoration() {
            private int rect = 10;

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state, int position) {

            }
        });
    }

    public ExpandedAdapter mAdapter = new ExpandedAdapter<Data>() {
        @Override
        public int groupLayoutId() {
            return R.layout.adapter_item;
        }

        @Override
        public int childLayoutId() {
            return R.layout.adapter_item;
        }

        @Override
        public int getChildCount(int groupPosition) {
            return 5;
        }

        @Override
        public void onBindGroupView(ViewHolder holder, int groupPosition) {
            holder.setText(R.id.tv_text, "商铺 ：" + groupPosition);
            holder.getView(R.id.tv_text).setPadding(20, 0, 0, 0);
        }

        @Override
        public void onBindChildView(ViewHolder holder, int groupPosition, int childPosition) {
            holder.setText(R.id.tv_text, "商铺 ：" + groupPosition + " 商品 ：" + childPosition);
        }
    };
}
