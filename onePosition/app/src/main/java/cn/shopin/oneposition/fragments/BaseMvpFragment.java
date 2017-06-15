package cn.shopin.oneposition.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.shopin.oneposition.Myapplication;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.di.component.DaggerFragmentComponent;
import cn.shopin.oneposition.di.component.FragmentComponent;
import cn.shopin.oneposition.di.module.FragmentModule;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2016/12/7.
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView {
    @Inject
    protected P mPresenter;
    protected View mView;
    private FragmentComponent fragmentComponent;
    protected Unbinder mUnBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        mView = inflater.inflate(getLayoutId(), null);
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), null);
        }
        ViewGroup parentView = (ViewGroup) mView.getParent();
        if (parentView != null) {
            parentView.removeView(mView);
            return mView;
        }
        initInject();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        mUnBinder = ButterKnife.bind(this, view);
        initEventAndData();
    }

    public FragmentComponent getFragmentComponent() {
//        if (fragmentComponent == null) {
        fragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(Myapplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
//        }
        return fragmentComponent;
    }

    private FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) mPresenter.detachView();
    }

    protected abstract void initInject();

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();
}
