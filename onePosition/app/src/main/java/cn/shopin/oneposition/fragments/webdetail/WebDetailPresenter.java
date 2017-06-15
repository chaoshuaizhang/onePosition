package cn.shopin.oneposition.fragments.webdetail;

import javax.inject.Inject;

import cn.shopin.oneposition.entity.movie.NostalgicEntity;
import cn.shopin.oneposition.model.db.DBManager;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.util.rxBus.EventEntity;
import cn.shopin.oneposition.util.rxBus.RxBus;

/**
 * Created by zcs on 2017/5/1.
 */

public class WebDetailPresenter extends BasePresenter<WebDetailFrag> implements WebDetailContract.IWebDetailPresenter {
    private DBManager dbManager;

    @Inject
    public WebDetailPresenter(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public boolean getDetailById(int detailId) {
        return dbManager.query(detailId);
    }

    @Override
    public boolean setLiked(boolean isLiked, NostalgicEntity mCurrentEntity) {
        boolean flag = false;
        if (isLiked) {
            flag = dbManager.insert(mCurrentEntity) != -1;
        } else {
            flag = dbManager.delete(mCurrentEntity) == 0;//插入成功返回false 失败返回true
        }
        //通知WebDetailFrag模块更新
        RxBus.getRxBusInstance().post(new EventEntity(12341, null));
        return flag;
    }
}
