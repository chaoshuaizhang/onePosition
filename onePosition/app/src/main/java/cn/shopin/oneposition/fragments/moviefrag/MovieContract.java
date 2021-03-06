package cn.shopin.oneposition.fragments.moviefrag;

import java.util.List;

import cn.shopin.oneposition.entity.movie.BannerDetailEntity;
import cn.shopin.oneposition.mvpbase.BaseModel;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2016/12/7.
 */
public interface MovieContract {
    interface IMovieModel extends BaseModel {
        void getBanners();

        void getBannerAds();
    }

    interface IMovieView extends BaseView {
        /**
         * 得到banner数据
         *
         * @param lists
         */
        void getBannerData(List<BannerDetailEntity> lists);
    }

    // TODO: 2016/12/18 至于定义成接口还是抽象类还有待考虑
    interface IMoviePresenter{

        /**
         * 得到普通banner
         */
        abstract void getBanners();

        /**
         * 得到广告banner
         */
        abstract void getBannerAds();


    }
}
