package cn.shopin.oneposition.activities.welcome;

import cn.shopin.oneposition.mvpbase.BaseModel;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2017/4/13.
 */

public interface WelcomeContract {
    interface IWelcomeView extends BaseView { }

    interface IWelcomeModel extends BaseModel { }

    abstract class IWelcomePresenter extends BasePresenter<IWelcomeView> {
        public IWelcomePresenter(IWelcomeView iWelcomeView) {
            super(iWelcomeView);
        }
    }
}
