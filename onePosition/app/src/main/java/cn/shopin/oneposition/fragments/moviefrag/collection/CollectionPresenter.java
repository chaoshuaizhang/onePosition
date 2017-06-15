package cn.shopin.oneposition.fragments.moviefrag.collection;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.shopin.oneposition.entity.movie.CollectEntity;
import cn.shopin.oneposition.model.db.DBManager;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.util.rxBus.EventEntity;
import io.reactivex.functions.Consumer;

/**
 * Created by zcs on 2017/4/30.
 */

public class CollectionPresenter extends BasePresenter<CollectionFrag> implements CollectionContract.ICollectionPresenter {
    private DBManager dbManager;

    @Inject
    public CollectionPresenter(DBManager dbManager) {
        this.dbManager = dbManager;
        registerEvent();
    }

    private void registerEvent() {
        addRxBusSubscribe(EventEntity.class, new Consumer<EventEntity>() {
            @Override
            public void accept(EventEntity eventEntity) {
                if (eventEntity.getTag() == 12341) {
                    getDataFromDb();
                }
            }
        });
    }

    public void getDataFromDb() {
        List<CollectEntity> dataList = new ArrayList<>();
        dataList.addAll(dbManager.query());
        if (dataList.size() == 0) {
            Toast.makeText(getView().getActivity(), "数据库为空", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getView().getActivity(), "数据库不为空", Toast.LENGTH_SHORT).show();
        }
        getView().getDataFromDb(dataList);
    }

    @Override
    public void deleteItemFromDb(int id) {
        int sid = dbManager.delete(id);
    }
}
