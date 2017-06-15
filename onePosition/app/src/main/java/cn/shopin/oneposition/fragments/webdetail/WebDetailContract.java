package cn.shopin.oneposition.fragments.webdetail;

import android.os.Bundle;

import cn.shopin.oneposition.entity.movie.NostalgicEntity;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2017/5/1.
 */

public interface WebDetailContract {
    interface IWebDetailView extends BaseView {

    }

    interface IWebDetailPresenter {
        boolean getDetailById(int detailId);

        boolean setLiked(boolean isLiked, NostalgicEntity mCurrentEntity);
    }
}
