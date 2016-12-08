package cn.shopin.oneposition.activities;

import android.os.Bundle;

import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.mvpbase.BaseView;

public abstract class BaseMvpActivity<V extends BaseView, P extends BasePresenter<V>> extends BaseActivity {

    protected P mPresenter;
    protected V mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter(mView);
    }

    /**
     * 关于dialog、progress的一些操作
     */
    protected void showProgressDialog() {
    }

    protected void showMessage() {
    }

    protected abstract P createPresenter(V view);
}
