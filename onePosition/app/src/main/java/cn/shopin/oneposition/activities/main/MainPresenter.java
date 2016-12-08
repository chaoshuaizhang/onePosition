package cn.shopin.oneposition.activities.main;

import android.util.Log;

/**
 * Created by zcs on 2016/12/7.
 */
public class MainPresenter extends MainContract.IMainPresenter {

    MainContract.IMainView mainView;
    MainContract.IMainModel mainModel;

    public MainPresenter(MainContract.IMainView iMainView) {
        super(iMainView);
        mainView = iMainView;
        mainModel = new MainModel();//能否也写成类似view的形式，并且保证view不拥有model实例
    }

    @Override
    protected void fun_main_presenter() {
        Log.d("TAG", "fun_main_presenter");
        mainModel.fun_main_model();
        mainView.fun_main_view();
    }
}
