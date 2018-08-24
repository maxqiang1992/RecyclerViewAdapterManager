package com.recyclerviewadaptermanager.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xiongxiang
 * @time 2018/8/17.
 * @e-mail 276186694@qq.com
 */
public class AdapterManager extends RecyclerView.Adapter<ViewHolder> {
    public static final int RATIO = 100;//比例 100份
    //adapter 容器
    private List<BaseAdapter> mAdapters = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        BaseAdapter currentAdapter = getCurrentAdapter(position);
        return currentAdapter.onCreateViewHolder(parent, getPosition(currentAdapter, position));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BaseAdapter currentAdapter = getCurrentAdapter(position);
        currentAdapter.onBindViewHolder(holder, getPosition(currentAdapter, position));
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        for (int i = 0; i < mAdapters.size(); i++) {
            itemCount += mAdapters.get(i).getItemCount();
        }
        return itemCount;
    }


    //获取adapter 对应数据的位置
    public int getPosition(BaseAdapter adapter, int position) {
        return position - adapter.getMinPosition();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    //找到对应的adpater
    private BaseAdapter getCurrentAdapter(int position) {
        int size = mAdapters.size();
        if (size > 2) {
            //直接中间开始找
            return getCurrentAdapter(position, size / 2 - 1);
        }
        for (int i = 0; i < size; i++) {
            BaseAdapter baseAdapter = mAdapters.get(i);
            int minPositon = baseAdapter.getMinPosition();
            int maxPostion = baseAdapter.getMaxPosition();
            if (position >= minPositon && position <= maxPostion) {
                return baseAdapter;
            }
        }
        return null;
    }

    //寻找adapter
    private BaseAdapter getCurrentAdapter(int position, int node) {
        BaseAdapter adapter = mAdapters.get(node);
        int minPositon = adapter.getMinPosition();
        int maxPostion = adapter.getMaxPosition();
        if (position >= minPositon && position <= maxPostion) {
            return adapter;
        } else {
            if (position > maxPostion) {
                return getCurrentAdapter(position, node + 1);
            } else {
                return getCurrentAdapter(position, node - 1);
            }
        }
    }

    //刷新所有的adapter
    public void notifyAdapter() {
        notifyAdapter(0);
        notifyDataSetChanged();
    }

    private void notifyAdapter(int position) {
        int size = mAdapters.size();
        if (size == 0 || size <= position) {
            return;
        }
        BaseAdapter adapter = mAdapters.get(position);
        if (position == 0) {
            adapter.setMinPosition(0);
            adapter.setMaxPosition(adapter.getItemCount() - 1);
        } else {
            int upAdpaterMax = mAdapters.get(position - 1).getMaxPosition();
            adapter.setMinPosition(upAdpaterMax + 1);
            adapter.setMaxPosition(adapter.getItemCount() + upAdpaterMax);
        }
        notifyAdapter(position + 1);
    }

    public void addAdapter(BaseAdapter adapter) {
        if (adapter == null) {
            return;
        }
        int size = mAdapters.size();
        if (size == 0) {
            adapter.setMinPosition(0);
            adapter.setMaxPosition(adapter.getItemCount() - 1);
        } else {
            int upAdpaterMax = mAdapters.get(size - 1).getMaxPosition();
            adapter.setMinPosition(upAdpaterMax + 1);
            adapter.setMaxPosition(adapter.getItemCount() + upAdpaterMax);
        }
        mAdapters.add(adapter);
    }

    public void removeAdapter(BaseAdapter adapter) {
        mAdapters.remove(adapter);
    }


    public void bindRecyclerView(RecyclerView recyclerView, Context context) {
        bind(recyclerView, context);
    }

    private void bind(RecyclerView recyclerView, Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(context, RATIO);
        mGridLayoutManager.setSpanSizeLookup(mSpanSizeLookup);
        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.setAdapter(this);
        recyclerView.addItemDecoration(mItemDecoration);
    }

    public void clear() {
        mAdapters.clear();
    }

    private RecyclerView.ItemDecoration mItemDecoration = new RecyclerView.ItemDecoration() {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int recyclerViewPosition = parent.getChildAdapterPosition(view);
            if (recyclerViewPosition > 0) {
                //默认分割线
                outRect.top = 1;
            }
            //获取当前adapter
            BaseAdapter currentAdapter = getCurrentAdapter(recyclerViewPosition);
            //当前adapter位置
            int adapterPosition = getPosition(currentAdapter, recyclerViewPosition);

            //分割线设置 传递对应的adapter 处理分割线的
            currentAdapter.getItemOffsets(outRect, view, parent, state, adapterPosition);
        }
    };
    //用于分配 一行几列
    private GridLayoutManager.SpanSizeLookup mSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            BaseAdapter adapter = getCurrentAdapter(position);
            if (adapter.getSpanSizeLookup() == null) {
                int ratio = adapter.getRatio(getPosition(adapter, position));
                return ratio > RATIO ? RATIO : ratio;
            } else {
                int ratio = adapter.getSpanSizeLookup().getSpanSize(getPosition(adapter, position));
                return ratio > RATIO ? RATIO : ratio;
            }

        }
    };

}
