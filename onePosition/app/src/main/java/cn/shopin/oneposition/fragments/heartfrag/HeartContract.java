package cn.shopin.oneposition.fragments.heartfrag;

import cn.shopin.oneposition.mvpbase.BaseModel;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2017/2/15.
 */

public interface HeartContract {
    interface IHeartModel extends BaseModel {
    }

    interface IHeartView extends BaseView {
    }

    abstract class IHeartPresenter extends BasePresenter<IHeartView> {
        public IHeartPresenter(IHeartView iHeartView) {
            super(iHeartView);
        }
    }
}
