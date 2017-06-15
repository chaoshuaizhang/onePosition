package cn.shopin.oneposition.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by zcs on 2017/5/5.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> sparseArray;
    private Context context;

    public RecyclerViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        sparseArray = new SparseArray<>();
    }

    /**
     * 通过填写的itemId来获取具体的View的对象
     *
     * @param itemId R.id.***
     * @param <T>    必须是View的子类
     * @return
     */
    public <T extends View> T findView(int itemId) {
        View mView = sparseArray.get(itemId);
        if (mView == null) {
            //实例化具体的View类型
            mView = itemView.findViewById(itemId);
            sparseArray.put(itemId, mView);
        }
        return (T) mView;
    }

    /**
     * 设置TextView
     *
     * @param itemId
     * @param text
     */
    public void setTextView(int itemId, String text) {
        TextView tv = findView(itemId);
        tv.setText(text);
    }

    /**
     * 加载ImageView
     *
     * @param itemId
     * @param path
     */
    public void setImageView(int itemId, String path) {
        ImageView img = findView(itemId);
        Picasso.with(context).load(path).into(img);
    }
}
