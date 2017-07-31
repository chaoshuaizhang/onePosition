package cn.shopin.oneposition.activities.register;

import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2017/7/12.
 */

public interface RegisterContract {
    interface IRegisterView extends BaseView {

        /**
         * 注册
         */
        void register();

        /**
         * 判断注册信息
         */
        void checkRegisterInfo();

        /**
         * 注册结果
         *
         * @param isSucc
         * @param msg
         */
        void registerResult(boolean isSucc, String msg);
    }

    interface IRegisterPresenter {
        /**
         * @param userName
         * @param userPwd
         * @param identifyCode
         */
        void register(String userName, String userPwd, String identifyCode);
    }
}
