package cn.shopin.oneposition.fragments.moviefrag;

import cn.shopin.oneposition.mvpbase.BaseModel;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2016/12/7.
 */
public interface MovieContract {
    interface IMovieModel extends BaseModel {
    }

    interface IMovieView extends BaseView {
    }

    abstract class IMoviePresenter extends BasePresenter<IMovieView> {
        public IMoviePresenter(IMovieView iMovieView) {
            super(iMovieView);
        }
    }
}
