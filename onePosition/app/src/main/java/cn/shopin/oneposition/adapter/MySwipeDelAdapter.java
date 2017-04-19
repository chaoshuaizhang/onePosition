package cn.shopin.oneposition.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.customview.SwipeDelView;
import cn.shopin.oneposition.entity.movie.CollectEntity;
import cn.shopin.oneposition.util.EnumServerMap;

/**
 * Created by zcs on 2017/3/24.
 */

public class MySwipeDelAdapter extends RecyclerView.Adapter<MySwipeDelAdapter.MyViewHolder> implements SwipeDelView.OnSwipeStatusChangeListener, View.OnTouchListener {
    private Context mContext;
    private List<CollectEntity> datas;
    private LayoutInflater mInflater = null;
    private ArrayList<SwipeDelView> unClosedSwipeView = new ArrayList<>();

    public MySwipeDelAdapter(Context mContext, List<CollectEntity> datas) {
        this.mContext = mContext;
        this.datas = datas;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_swipe_del, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.d("dbman", "onBindViewHolder    " + position);
        holder.swipeView.setOnSwipeStatusChangeListener(this);
        holder.swipeView.fastClose();
        Picasso.with(mContext).load(EnumServerMap.getBaseUrlByTag(Cans.TAG_MOVIE) + datas.get(position).getPic()).into(holder.pic);
        holder.title.setText(datas.get(position).getTitle());
        holder.summary.setText(datas.get(position).getSummary());
        holder.type.setText(datas.get(position).getSubtype().getName());
        holder.comments.setText(String.format("评论%d | 转发%d", datas.get(position).getCommentcount(), datas.get(position).getSharecount()));
        holder.unCollect.setText("取消");
        holder.setTop.setText("置顶");
        holder.unCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.setTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unClosedSwipeView.size() > 0) {
                    closeAllOpenedSwipeView();
                } else {
                    Toast.makeText(mContext, "第" + position + "个", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("dbman", "" + datas.size());
        return datas.size();
    }

    @Override
    public void onOpen(SwipeDelView openedSwipeView) {

        for (int i = 0; i < unClosedSwipeView.size(); i++) {
            if (unClosedSwipeView.get(i) != openedSwipeView) {
                unClosedSwipeView.get(i).close();
            }
        }
        if (!unClosedSwipeView.contains(openedSwipeView)) {
            unClosedSwipeView.add(openedSwipeView);
        }
    }

    @Override
    public void onClose(SwipeDelView closedSwipeView) {
        unClosedSwipeView.remove(closedSwipeView);
    }

    @Override
    public void onSwiping(SwipeDelView swipingSwipeView) {
        if (!unClosedSwipeView.contains(swipingSwipeView)) {
            closeAllOpenedSwipeView();
        }
        unClosedSwipeView.add(swipingSwipeView);
    }

    private void closeAllOpenedSwipeView() {
        for (int i = 0; i < unClosedSwipeView.size(); i++) {
            if (unClosedSwipeView.get(i).getSwipeStatus() != SwipeDelView.SwipeStatus.Close) {
                unClosedSwipeView.get(i).close();
                unClosedSwipeView.remove(i);
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (unClosedSwipeView.size() != 0) {
            closeAllOpenedSwipeView();
            return true;
        } else {
            return false;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private SwipeDelView swipeView;
        private ImageView pic;
        private TextView title;
        private TextView summary;
        private TextView type;
        private TextView comments;
        private TextView unCollect;
        private TextView setTop;

        public MyViewHolder(View view) {
            super(view);
            swipeView = (SwipeDelView) view.findViewById(R.id.swipe_del);
            pic = (ImageView) view.findViewById(R.id.pic);
            title = (TextView) view.findViewById(R.id.title);
            summary = (TextView) view.findViewById(R.id.summary);
            type = (TextView) view.findViewById(R.id.type);
            comments = (TextView) view.findViewById(R.id.comments);
            unCollect = (TextView) view.findViewById(R.id.setTop);
            setTop = (TextView) view.findViewById(R.id.delete);
        }
    }
}
