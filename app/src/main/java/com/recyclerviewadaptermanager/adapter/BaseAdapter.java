package com.recyclerviewadaptermanager.adapter;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongxiang
 * @time 2018/8/17.
 * @e-mail 276186694@qq.com
 */
public abstract class BaseAdapter<T> extends Adapter<T> {
    private int minPosition;
    private int maxPosition;
    public List<T> mList = new ArrayList<>();

    public ItemDecoration mItemDecoration;

    public GridLayoutManager.SpanSizeLookup mSpanSizeLookup;


    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> list) {
        mList = list;
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return mSpanSizeLookup;
    }

    public void setSpanSizeLookup(GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        mSpanSizeLookup = spanSizeLookup;
    }

    public ItemDecoration getItemDecoration() {
        return mItemDecoration;
    }

    public void setItemDecoration(ItemDecoration itemDecoration) {
        mItemDecoration = itemDecoration;
    }

    public int getMinPosition() {
        return minPosition;
    }

    public void setMinPosition(int minPosition) {
        this.minPosition = minPosition;
    }

    public int getMaxPosition() {
        return maxPosition;
    }

    public void setMaxPosition(int maxPostion) {
        this.maxPosition = maxPostion;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false));
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state, int position) {
        if (mItemDecoration != null) {
            mItemDecoration.getItemOffsets(outRect, view, parent, state, position);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, position, getData(position));
    }

    public void addData(T t) {
        mList.add(t);
    }

    public void addData(List<T> t) {
        mList.addAll(t);
    }

    public void removeData(T t) {
        mList.remove(t);
    }

    public void removeData(int position) {
        mList.remove(position);
    }

    public T getData(int position) {
        return mList.get(position);
    }

    public void claer() {
        mList.clear();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    //默认填满屏幕
    public int getRatio(int position) {
        return AdapterManager.RATIO;
    }
}
