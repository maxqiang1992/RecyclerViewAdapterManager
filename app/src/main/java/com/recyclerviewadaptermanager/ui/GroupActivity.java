package com.recyclerviewadaptermanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.recyclerviewadaptermanager.Data;
import com.recyclerviewadaptermanager.R;
import com.recyclerviewadaptermanager.adapter.AdapterManager;
import com.recyclerviewadaptermanager.adapter.ExpandedAdapter;
import com.recyclerviewadaptermanager.adapter.GridLayoutAdapter;
import com.recyclerviewadaptermanager.adapter.LinearLayoutAdapter;
import com.recyclerviewadaptermanager.adapter.ViewHolder;

/**
 * @author xiongxiang
 * @time 2018/8/24.
 * @e-mail 276186694@qq.com
 */
public class GroupActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    public String getText() {
        return "Group";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = findViewById(R.id.rv);
        AdapterManager adapterManager = new AdapterManager();
        adapterManager.bindRecyclerView(mRecyclerView, this);

        LinearLayoutAdapter linearLayoutAdapter = createLinearLayoutAdapter();
        linearLayoutAdapter.addData(getData(5, "linearLayoutAdapter"));


        GridLayoutAdapter gridLayoutAdapter = createGridLayoutAdapter();
        gridLayoutAdapter.addData(getData(10, "gridLayoutAdapter"));


        ExpandedAdapter expandedAdapter = createExpandedAdapter();

        expandedAdapter.addData(getData(5, "expandedAdapter"));
        expandedAdapter.notifyPosition();

        adapterManager.addAdapter(linearLayoutAdapter);
        adapterManager.addAdapter(gridLayoutAdapter);
        adapterManager.addAdapter(expandedAdapter);
        linearLayoutAdapter.addData(getData(5, "addLinearLayout"));
        adapterManager.notifyAdapter();

        expandedAdapter.addData(getData(4,"expandedAdapter"));
        expandedAdapter.notifyPosition();
        adapterManager.notifyAdapter();

        LinearLayoutAdapter linearLayoutAdapter1 = createLinearLayoutAdapter();
        linearLayoutAdapter1.addData(getData(5,"jjww"));
        adapterManager.addAdapter(linearLayoutAdapter1);
        adapterManager.notifyAdapter();


    }

    private LinearLayoutAdapter createLinearLayoutAdapter() {
        return new LinearLayoutAdapter<Data>() {
            @Override
            public int getLayoutId() {
                return R.layout.adapter_item;
            }

            @Override
            public void convert(ViewHolder holder, int position, Data o) {
                holder.setText(R.id.tv_text, o.getText() + " : " + position);
            }

        };
    }

    private GridLayoutAdapter createGridLayoutAdapter() {
        return new GridLayoutAdapter<Data>(2) {
            @Override
            public int getLayoutId() {
                return R.layout.adapter_item;
            }

            @Override
            public void convert(ViewHolder holder, int position, Data o) {
                holder.setText(R.id.tv_text, o.getText() + " : " + position);
            }
        };
    }

    private ExpandedAdapter createExpandedAdapter() {
        return new ExpandedAdapter<Data>() {
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
                return 10;
            }

            @Override
            public void onBindGroupView(ViewHolder holder, int groupPosition) {
                holder.setText(R.id.tv_text, "G:" + groupPosition);
            }

            @Override
            public void onBindChildView(ViewHolder holder, int groupPosition, int childPosition) {
                holder.setText(R.id.tv_text, "G:" + groupPosition + " C" + childPosition);
            }

            @Override
            public int getChildRatio(int groupPosition, int childPosition) {
                return AdapterManager.RATIO / (groupPosition + 1);
            }
        };
    }


}
