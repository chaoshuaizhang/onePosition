package cn.shopin.oneposition.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.entity.movie.MoviePieceEntity;

import static android.R.id.list;

/**
 * Created by zcs on 2017/2/10.
 */

public class RecyclerAdapter1 extends BaseAdapter {
    Context mContext;
    List<MoviePieceEntity> list;
    private LayoutInflater mInflater = null;

    public RecyclerAdapter1(Context mContext, List<MoviePieceEntity> list) {
        this.mContext = mContext;
        this.list = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mInflater.inflate(R.layout.itemlayout_red_left, viewGroup, false);
        return view;
    }
}
