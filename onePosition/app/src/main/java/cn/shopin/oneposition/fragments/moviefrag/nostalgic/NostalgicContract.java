package cn.shopin.oneposition.fragments.moviefrag.nostalgic;

import java.util.List;

import cn.shopin.oneposition.entity.movie.NostalgicEntity;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2017/5/1.
 */

public interface NostalgicContract {
    interface INostalgicView extends BaseView {
        void loadData(String timeStr);

        void getMovieNostalgic(List<NostalgicEntity> datas);

    }

    interface INostalgicPresenter { }
}
