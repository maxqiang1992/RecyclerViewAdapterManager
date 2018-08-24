package com.recyclerviewadaptermanager.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author xiongxiang
 * @time 2018/8/17.
 * @e-mail 276186694@qq.com
 */
public interface ItemDecoration {
    /**
     * 控制View的边距
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     * @param position
     */
    void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state, int position);
}
