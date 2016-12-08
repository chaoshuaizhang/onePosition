package cn.shopin.oneposition.mvpbase;

/**
 * Created by zcs on 2016/12/7.
 */
public abstract class BasePresenter<V> {
    protected V mView;

    public BasePresenter(V v) {
        mView = v;
    }

    public void onDestory() {
        mView = null;
    }
}
