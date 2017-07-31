package cn.shopin.oneposition.activities.register;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import cn.shopin.oneposition.entity.BaseResponse;
import cn.shopin.oneposition.entity.UserEntity;
import cn.shopin.oneposition.model.net.RetrofitHelper;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.util.CommonSubscriber;
import cn.shopin.oneposition.util.RxUtil;

/**
 * Created by zcs on 2017/7/12.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView> implements RegisterContract.IRegisterPresenter {
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public RegisterPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void register(String userName, String userPwd, String identifyCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("userPwd", userPwd);
        map.put("identifyCode", identifyCode);
        map.put("mobile", "13716107504");
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("张三");
        userEntity.setUserPwd("12345qwert");
        addSubscription(mRetrofitHelper.register(map, userEntity)
                .compose(RxUtil.<BaseResponse<Boolean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<BaseResponse<Boolean>>(getView()) {
                    @Override
                    public void dataHandle(BaseResponse<Boolean> booleanBaseResponse) {
                        getView().registerResult(booleanBaseResponse.getData(), "结果");
                    }
                }));
    }
}
