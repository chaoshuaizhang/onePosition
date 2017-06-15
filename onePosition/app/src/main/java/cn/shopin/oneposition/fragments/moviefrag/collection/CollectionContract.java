package cn.shopin.oneposition.fragments.moviefrag.collection;

import java.util.List;

import cn.shopin.oneposition.entity.movie.CollectEntity;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2017/4/30.
 */

public interface CollectionContract {
    interface ICollectionView extends BaseView {
        void getDataFromDb(List<CollectEntity> dataList);
    }

    interface ICollectionPresenter {
        void getDataFromDb();

        void deleteItemFromDb(int id);
    }
}
