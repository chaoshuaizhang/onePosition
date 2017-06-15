package cn.shopin.oneposition.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.entity.movie.PieceEntity;

/**
 * Created by zcs on 2017/2/10.
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    private Context mContext;
    private List<T> dataList;
    private int resId;
    private LayoutInflater mInflater = null;
    private OnItemViewClickListener onItemViewClickListener;

    public CommonRecyclerAdapter(Context mContext, List<T> dataList, int resId) {
        this.mContext = mContext;
        this.dataList = dataList;
        this.resId = resId;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemViewClickListener(OnItemViewClickListener listener){
        this.onItemViewClickListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(mContext, mInflater.inflate(resId, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        setData(holder, position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



    public abstract void setData(RecyclerViewHolder holder, int position);
    public interface OnItemViewClickListener{
        //设置item中某个view的点击事件
        void onItemViewClick(RecyclerViewHolder view,int position);
    }
}
