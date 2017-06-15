package cn.shopin.oneposition.activities;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import cn.shopin.oneposition.Myapplication;
import cn.shopin.oneposition.di.component.ActivityComponent;
import cn.shopin.oneposition.di.component.DaggerActivityComponent;
import cn.shopin.oneposition.di.module.ActivityModule;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.mvpbase.BaseView;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    protected P mPresenter;
    private static ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFullScreen) {
            setFullScreen();
        }
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        Myapplication.getInstance().addActivity(this);
        initEventAndData();
    }

    protected ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .appComponent(Myapplication.getAppComponent())
                    .activityModule(getActivityModule())
                    .build();
        }
        return activityComponent;
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /**
     * 关于dialog、progress的一些操作
     */
    protected void showProgressDialog() {
    }

    protected void showMessage() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract void initInject();

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();
}
