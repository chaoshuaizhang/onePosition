package cn.shopin.oneposition.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import cn.shopin.oneposition.util.db.DBManager;

/**
 * Created by zcs on 2017/3/24.
 */

public class MySwipeDelAdapter2 extends BaseAdapter implements SwipeDelView.OnSwipeStatusChangeListener, View.OnTouchListener {
    private Context mContext;
    private List<CollectEntity> datas;
    private LayoutInflater mInflater = null;
    private ArrayList<SwipeDelView> unClosedSwipeView = new ArrayList<>();
    private MyItemClickListener myClickListener;
    private DBManager dbManager;

    public MySwipeDelAdapter2(Context mContext, List<CollectEntity> datas, Fragment frag) {
        this.mContext = mContext;
        this.datas = datas;
        dbManager = new DBManager(mContext);
        myClickListener = (MyItemClickListener) frag;
        mInflater = LayoutInflater.from(mContext);
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

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_swipe_del, null);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.swipeView.setOnSwipeStatusChangeListener(this);
        holder.swipeView.fastClose();
        Picasso.with(mContext).load(EnumServerMap.getBaseUrlByTag(Cans.TAG_MOVIE) + datas.get(position).getPic()).into(holder.pic);
        holder.title.setText(datas.get(position).getTitle());
        holder.summary.setText(datas.get(position).getSummary());
        holder.type.setText(datas.get(position).getSubtype().getName());
        holder.comments.setText(String.format("评论%d | 转发%d", datas.get(position).getCommentcount(), datas.get(position).getSharecount()));
        holder.setTop.setText("置顶");
        holder.unCollect.setText("移除");
        holder.unCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.delete(datas.get(position));
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
        holder.context.setClickable(true);
        holder.context.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unClosedSwipeView.size() > 0) {
                    closeAllOpenedSwipeView();
                } else {
                    myClickListener.clickListener(position);
                }
            }
        });
        holder.context.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (unClosedSwipeView.size() == 0) {
                    myClickListener.longClickListener(position);
                    v.setSelected(true);
                }
                return true;
            }
        });
        return convertView;
    }

    class MyViewHolder {
        private SwipeDelView swipeView;
        private ImageView pic;
        private TextView title;
        private TextView summary;
        private TextView type;
        private TextView comments;
        private TextView unCollect;
        private TextView setTop;
        private View context;

        public MyViewHolder(View view) {
            swipeView = (SwipeDelView) view.findViewById(R.id.swipe_del);
            pic = (ImageView) view.findViewById(R.id.pic);
            title = (TextView) view.findViewById(R.id.title);
            summary = (TextView) view.findViewById(R.id.summary);
            type = (TextView) view.findViewById(R.id.type);
            comments = (TextView) view.findViewById(R.id.comments);
            unCollect = (TextView) view.findViewById(R.id.delete);
            setTop = (TextView) view.findViewById(R.id.setTop);
            context = view.findViewById(R.id.content);
            context.setBackgroundResource(R.drawable.collect_item_click);
        }
    }

    public interface MyItemClickListener {
        void clickListener(int position);

        void longClickListener(int position);
    }
}
