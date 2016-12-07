package cn.shopin.oneposition.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2016/12/7.
 */
public abstract class BaseMvpFragment<V extends BaseView, P extends BasePresenter<V>> extends BaseFragment {
    protected P mPresenter;
    protected V mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter(mView);
    }

    /**
     * @param view 也可定义为BaseView类型
     * @return
     */
    protected abstract P createPresenter(V view);
}
