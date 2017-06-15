package cn.shopin.oneposition.activities.main;

import cn.shopin.oneposition.mvpbase.BaseModel;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2016/12/7.
 */
public interface MainContract {

    interface IMainView extends BaseView {
        void initFrags();

        void setDefaultFrag(int tag);

        void switchFrag(int tag);

    }

    interface MainPresenter {
    }
}
