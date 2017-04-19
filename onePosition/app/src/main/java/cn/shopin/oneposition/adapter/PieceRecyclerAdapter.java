package cn.shopin.oneposition.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.entity.movie.NostalgicEntity;
import cn.shopin.oneposition.entity.movie.PieceEntity;
import cn.shopin.oneposition.util.DataUtil;

import static cn.shopin.oneposition.util.DataUtil.getPercentData;

/**
 * Created by zcs on 2017/2/10.
 */

public class PieceRecyclerAdapter extends RecyclerView.Adapter<PieceRecyclerAdapter.MyViewHolder> {
    Context mContext;
    List<PieceEntity> list;
    private LayoutInflater mInflater = null;
    private pieceClickListener pieceClickListener;
    private static final int[] LIBERTY_COLORS = {
            Color.rgb(220, 87, 0), Color.rgb(17, 87, 156), Color.rgb(246, 179, 0),
            Color.rgb(14, 84, 43), Color.rgb(214, 35, 74), Color.rgb(109, 184, 156),
            Color.rgb(174, 24, 124), Color.rgb(94, 45, 134), Color.rgb(220, 211, 129),
            Color.rgb(156, 47, 42)
    };

    public PieceRecyclerAdapter(Context mContext, List<PieceEntity> list, Fragment fragment) {
        this.mContext = mContext;
        this.list = list;
        mInflater = LayoutInflater.from(mContext);
        pieceClickListener = (PieceRecyclerAdapter.pieceClickListener) fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(mInflater.inflate(R.layout.piece_item_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.linearBg.setBackgroundColor(LIBERTY_COLORS[position]);
        holder.textTag.setText(Integer.toString(position + 1));
        holder.textName.setText(list.get(position).getName());
        holder.textRatio.setText(getPercentData(list.get(position).getRatio()));
        holder.textSession.setText(DataUtil.getDataFormat1(list.get(position).getEvents()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearBg;
        private TextView textTag;
        private TextView textName;
        private TextView textRatio;
        private TextView textSession;

        public MyViewHolder(final View itemView) {
            super(itemView);
            linearBg = (LinearLayout) itemView.findViewById(R.id.tag_bg);
            textTag = (TextView) itemView.findViewById(R.id.tag);
            textName = (TextView) itemView.findViewById(R.id.movie_name);
            textRatio = (TextView) itemView.findViewById(R.id.piece_ratio);
            textSession = (TextView) itemView.findViewById(R.id.movie_session);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pieceClickListener.pieceClick(getLayoutPosition());
                }
            });
        }
    }

    public interface pieceClickListener {
        void pieceClick(int position);
    }
}
