package cn.shopin.oneposition.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zcs on 2017/2/10.
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<T> dataList;
    private int resId;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public abstract void setData(RecyclerView.ViewHolder holder, int position);
}
