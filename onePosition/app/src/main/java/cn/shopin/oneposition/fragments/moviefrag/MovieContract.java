package cn.shopin.oneposition.fragments.moviefrag;

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
    }

    // TODO: 2016/12/18 至于定义成接口还是抽象类还有待考虑
    abstract class IMoviePresenter extends BasePresenter<IMovieView> {
        public IMoviePresenter(IMovieView iMovieView) {
            super(iMovieView);
        }

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
