package com.recyclerviewadaptermanager.adapter;



/**
 * @author xiongxiang
 * @time 2018/8/17.
 * @e-mail 276186694@qq.com
 */
public abstract class GridLayoutAdapter<T> extends BaseAdapter<T>{
    private int mRatio;
    private int mSpan;

    /**
     * 一行几列
     *
     * @param span
     */
    public GridLayoutAdapter(int span) {
        this.mSpan = span;
        mRatio = AdapterManager.RATIO / span;
    }

    @Override
    public int getRatio(int position) {
        return mRatio;
    }
}

