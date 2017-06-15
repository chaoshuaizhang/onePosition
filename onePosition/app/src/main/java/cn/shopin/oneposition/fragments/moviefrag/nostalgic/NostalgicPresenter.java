package cn.shopin.oneposition.fragments.moviefrag.nostalgic;

import java.util.List;

import javax.inject.Inject;

import cn.shopin.oneposition.entity.movie.NostalgicEntity;
import cn.shopin.oneposition.model.net.RetrofitHelper;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.util.RxUtil;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by zcs on 2017/5/1.
 */

public class NostalgicPresenter extends BasePresenter<NostalgicFrag> implements NostalgicContract.INostalgicPresenter {
    private RetrofitHelper retrofitHelper;

    @Inject
    public NostalgicPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    public void getMovieNostalgic(String fromTime, String count, String category, String type, String subtype) {
        addSubscription(retrofitHelper.getMovieNostalgic(fromTime, count, category, type, subtype)
                .compose(RxUtil.<List<NostalgicEntity>>rxSchedulerHelper())
                .subscribe(new Consumer<List<NostalgicEntity>>() {
                    @Override
                    public void accept(@NonNull List<NostalgicEntity> nostalgicEntities) throws Exception {
                        getView().getMovieNostalgic(nostalgicEntities);
                    }
                }));
    }
}
