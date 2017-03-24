package cn.shopin.oneposition.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.customview.AdverView;
import cn.shopin.oneposition.entity.heart.LabelResultsEntity;

/**
 * Created by zcs on 2017/3/10.
 */

public class AdverViewAdapter {
    private List<LabelResultsEntity> mDatas;

    public AdverViewAdapter(List<LabelResultsEntity> mDatas) {
        this.mDatas = mDatas;
        if (mDatas == null) {
            throw new RuntimeException("nothing to show");
        }
    }

    /**
     * 获取数据的条数
     *
     * @return
     */
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    /**
     * 获取摸个数据
     *
     * @param position
     * @return
     */
    public LabelResultsEntity getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * 获取条目布局
     *
     * @param parent
     * @return
     */
    public View getView(AdverView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.adver_item, null);
    }

    /**
     * 条目数据适配
     *
     * @param view
     * @param data
     */
    public void setItem(final View view, final LabelResultsEntity data) {
        TextView content = (TextView) view.findViewById(R.id.adver_content);
        content.setText(data.getLabel());
        //可以增加点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //比如打开url
                Toast.makeText(view.getContext(), data.getLabel(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

