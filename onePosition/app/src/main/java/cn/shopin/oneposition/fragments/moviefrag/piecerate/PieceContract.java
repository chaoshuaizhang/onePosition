package cn.shopin.oneposition.fragments.moviefrag.piecerate;

import java.util.List;

import cn.shopin.oneposition.entity.movie.PieceEntity;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2017/5/2.
 */

public interface PieceContract {
    interface IPieceView extends BaseView {
        /**
         * 根据时间戳获得数据
         * @param timeStr
         */
        void loadMvDetail(String timeStr);

        void setMvDetail(List<PieceEntity> pieceEntities);
    }

    interface IPiecePresenter {
        void loadMvDetail(String timeStr);
    }
}
