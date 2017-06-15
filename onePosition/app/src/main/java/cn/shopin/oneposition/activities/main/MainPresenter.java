package cn.shopin.oneposition.activities.main;

import android.util.Log;

import javax.inject.Inject;

import cn.shopin.oneposition.mvpbase.BasePresenter;

/**
 * Created by zcs on 2016/12/7.
 */
public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract.MainPresenter {

    @Inject
    public MainPresenter() {
    }
}
