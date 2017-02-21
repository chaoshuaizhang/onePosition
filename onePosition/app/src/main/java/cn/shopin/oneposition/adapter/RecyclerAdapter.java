package cn.shopin.oneposition.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.customview.CircleProgressView;
import cn.shopin.oneposition.entity.movie.MoviePieceEntity;
import cn.shopin.oneposition.util.EnumServerMap;

/**
 * Created by zcs on 2017/2/9.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int RED_ITEM_LEFT = 0;
    public static final int RED_ITEM_RIGHT = 1;
    public static final int WHITE_ITEM_LEFT = 2;
    public static final int WHITE_ITEM_RIGHT = 3;
    public static final int BLACK_ITEM_LEFT = 4;
    public static final int BLACK_ITEM_RIGHT = 5;
    public static final int FOOT_VIEW = 6;
    private LayoutInflater mInflater = null;
    private Context mContext;
    private List<MoviePieceEntity> dataList;
    private setclickListener setclickListener;
    private View footView;
    private LinearLayout loadMoreLayout;
    private TextView clickToLoad;

    public RecyclerAdapter(Context mContext, List<MoviePieceEntity> list) {
        this.mContext = mContext;
        dataList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = null;
        switch (viewType) {
            case RED_ITEM_LEFT:
                view = mInflater.inflate(R.layout.itemlayout_red_left, parent, false);
                viewHolder = new MyViewHolder(view);
                break;
            case RED_ITEM_RIGHT:
                view = mInflater.inflate(R.layout.itemlayout_red_right, parent, false);
                viewHolder = new MyViewHolder(view);
                break;
            case WHITE_ITEM_LEFT:
                view = mInflater.inflate(R.layout.itemlayout_white_left, parent, false);
                viewHolder = new MyViewHolder(view);
                break;
            case WHITE_ITEM_RIGHT:
                view = mInflater.inflate(R.layout.itemlayout_white_right, parent, false);
                viewHolder = new MyViewHolder(view);
                break;
            case BLACK_ITEM_LEFT:
                view = mInflater.inflate(R.layout.itemlayout_black_left, parent, false);
                viewHolder = new MyViewHolder(view);
                break;
            case BLACK_ITEM_RIGHT:
                view = mInflater.inflate(R.layout.itemlayout_black_right, parent, false);
                viewHolder = new MyViewHolder(view);
                break;
            case FOOT_VIEW:
                footView = mInflater.inflate(R.layout.itemlayout_footview, parent, false);
                loadMoreLayout = (LinearLayout) footView.findViewById(R.id.loadmore_layout);
                clickToLoad = (TextView) footView.findViewById(R.id.click_loadmore);
                viewHolder = new MyViewHolder(footView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!isFootView(position)) {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            Glide.with(mContext).load(EnumServerMap.getBaseUrlByTag(Cans.TAG_MOVIE) + dataList.get(position).getPic()).error(R.mipmap.ic_launcher).fitCenter().centerCrop().into(viewHolder.img);
            viewHolder.chineseName.setText(dataList.get(position).getTitle());
            viewHolder.engName.setText(dataList.get(position).getEngname());
            viewHolder.movieTag.setText(dataList.get(position).getLocation());
            viewHolder.progressView.setProgress((int) (dataList.get(position).getPrize() * 10));
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() == 0 ? 0 : dataList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == dataList.size()) {//footview
            return FOOT_VIEW;
        }
        if (position % 3 == 0) {//red
            if (position % 2 == 0) {//red-left
                return RED_ITEM_LEFT;
            } else {//red-right
                return RED_ITEM_RIGHT;
            }
        } else if (position % 3 == 1) {//white
            if (position % 2 == 0) {//white-left
                return WHITE_ITEM_LEFT;
            } else {//white-right
                return WHITE_ITEM_RIGHT;
            }
        } else {//black
            if (position % 2 == 0) {//black-left
                return BLACK_ITEM_LEFT;
            } else {//black-right
                return BLACK_ITEM_RIGHT;
            }
        }
    }

    private boolean isFootView(int position) {
        return position == getItemCount() - 1;
    }

    public void setLaoding(boolean isLoad) {
        if (isLoad && footView != null) {
            clickToLoad.setVisibility(View.GONE);
            loadMoreLayout.setVisibility(View.VISIBLE);
        } else {
            clickToLoad.setVisibility(View.VISIBLE);
            loadMoreLayout.setVisibility(View.GONE);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView chineseName;
        private TextView engName;
        private TextView movieTag;
        private CircleProgressView progressView;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            chineseName = (TextView) itemView.findViewById(R.id.chinese_name);
            engName = (TextView) itemView.findViewById(R.id.eng_name);
            movieTag = (TextView) itemView.findViewById(R.id.tag);
            progressView = (CircleProgressView) itemView.findViewById(R.id.progress_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setclickListener.onItemClick(getLayoutPosition());
                }
            });
        }
    }

    public void setOnItemClick(setclickListener listener) {
        setclickListener = listener;
    }

    public interface setclickListener {
        void onItemClick(int position);
    }
}
