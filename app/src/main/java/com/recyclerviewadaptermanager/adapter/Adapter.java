package com.recyclerviewadaptermanager.adapter;

import android.view.ViewGroup;

/**
 * @author xiongxiang
 * @time 2018/8/17.
 * @e-mail 276186694@qq.com
 */
public abstract class Adapter<T> {
    //创建ViewHolder
    public abstract ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);
    //绑定ViewHolder
    public abstract void onBindViewHolder(ViewHolder holder, int position);
    //条目数量
    public abstract int getItemCount();
    //布局id
    public abstract int getLayoutId();
    //操作ViewHolder
    public abstract void convert(ViewHolder holder, int position, T t);
}
