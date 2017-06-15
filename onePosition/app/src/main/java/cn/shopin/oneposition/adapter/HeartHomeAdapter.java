package cn.shopin.oneposition.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.entity.heart.HomeCarousel;

/**
 * Created by zcs on 2017/5/5.
 */

public class HeartHomeAdapter extends CommonRecyclerAdapter<String> {
    private List<String> dataList;
    private Context mContext;

    public HeartHomeAdapter(Context mContext, List<String> dataList, int resId) {
        super(mContext, dataList, resId);
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @Override
    public void setData(RecyclerViewHolder holder, final int position) {
        holder.setTextView(R.id.text1, dataList.get(position));
        holder.setTextView(R.id.text2, dataList.get(position));
        holder.setTextView(R.id.text3, dataList.get(position));
        TextView t1 = holder.findView(R.id.text1);
        TextView t2 = holder.findView(R.id.text2);
        TextView t3 = holder.findView(R.id.text3);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "999", Toast.LENGTH_SHORT).show();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, dataList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "0000", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
