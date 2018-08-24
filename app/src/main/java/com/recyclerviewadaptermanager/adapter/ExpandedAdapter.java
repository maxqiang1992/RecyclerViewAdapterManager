package com.recyclerviewadaptermanager.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * - - - - - - - - - - - - - - -
 * group 0
 * -child 0
 * -child 1
 * -child 2
 * group 1
 * -child 0
 * -child 1
 * -child 2
 * - - - - - - - - - - - - - - -
 * 针对 多商家购物车列表adapter
 *
 * @author xiongxiang
 * @time 2018/8/20.
 * @e-mail 276186694@qq.com
 */
public abstract class ExpandedAdapter<T> extends BaseAdapter<T> {

    private int[] groupStartPosition;

    public abstract int groupLayoutId();

    public abstract int childLayoutId();

    public abstract int getChildCount(int groupPosition);


    int getGroupCount() {
        return getList() == null ? 0 : getList().size();
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void convert(ViewHolder holder, int position, T t) {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        int layoutId = 0;
        if (isGroup(position)) {
            layoutId = groupLayoutId();
        } else {
            layoutId = childLayoutId();
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int[] groupAndChildPosition = getGroupAndChildPosition(position);
        int groupPosition = groupAndChildPosition[0];
        int childPosition = groupAndChildPosition[1];
        if (isGroup(position)) {
            onBindGroupView(holder, groupPosition);
        } else {
            onBindChildView(holder, groupPosition, childPosition);
        }
    }

    public abstract void onBindGroupView(ViewHolder holder, int groupPosition);

    public abstract void onBindChildView(ViewHolder holder, int groupPosition, int childPosition);

    @Override
    public int getItemCount() {
        int count = 0;
        count += getGroupCount();
        int size = getList().size();
        for (int i = 0; i < size; i++) {
            count += getChildCount(i);
        }
        return count;
    }

    //数据发生改变 必须 调用此方法 重新计算 gruop 开始位置
    public void notifyPosition() {
        int count = 0;
        List<T> list = getList();
        groupStartPosition = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            groupStartPosition[i] = count;
            count += 1;
            count += getChildCount(i);
        }
    }


    //判断是不是 group
    public Boolean isGroup(int position) {
        if (groupStartPosition != null) {
            int length = groupStartPosition.length;
            for (int i = 0; i < length; i++) {
                int p = groupStartPosition[i];
                if (p == position) {
                    return true;
                }
            }
        }
        return false;
    }


    //获取 group 和child 位置  0 group 1 child
    public int[] getGroupAndChildPosition(int position) {
        int[] gp = new int[2];
        int groupPosition = findGroupPosition(0, position);
        gp[0] = groupPosition;
        gp[1] = position - groupStartPosition[groupPosition] - 1;
        return gp;
    }

    private int findGroupPosition(int position, int viewPosition) {
        int size = getList().size();
        if (position == size - 1) {
            return position;
        } else {
            int currentGroupPosition = groupStartPosition[position];
            int nextGroupPosition = groupStartPosition[position + 1];
            if (viewPosition >= currentGroupPosition && viewPosition < nextGroupPosition) {
                return position;
            } else {
                return findGroupPosition(position + 1, viewPosition);
            }
        }
    }

    @Override
    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return mSpanSizeLookup;
    }

    @Override
    public void setSpanSizeLookup(GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    public GridLayoutManager.SpanSizeLookup mSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            int[] groupAndChildPosition = getGroupAndChildPosition(position);
            int groupPosition = groupAndChildPosition[0];
            int childPosition = groupAndChildPosition[1];
            if (isGroup(position)) {
                return getGroupRation(groupPosition,childPosition);
            } else {
                return getChildRatio(groupPosition, childPosition);
            }
        }
    };

    //重写此方法 可以重新分配 span   以100为单位  分配占据宽度的比例
    public int getChildRatio(int groupPosition, int childPosition) {
        return  AdapterManager.RATIO;
    }

    public int getGroupRation(int groupPosition, int childPosition) {
        return  AdapterManager.RATIO;
    }
}
