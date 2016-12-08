package cn.shopin.oneposition.activities.main;

import cn.shopin.oneposition.mvpbase.BaseModel;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2016/12/7.
 */
public interface MainContract {
    interface IMainModel extends BaseModel {
        void fun_main_model();
    }

    interface IMainView extends BaseView {
        void fun_main_view();
    }

    abstract class IMainPresenter extends BasePresenter<IMainView> {
        public IMainPresenter(IMainView iMainView) {
            super(iMainView);
        }
        protected abstract void fun_main_presenter();
    }
}
