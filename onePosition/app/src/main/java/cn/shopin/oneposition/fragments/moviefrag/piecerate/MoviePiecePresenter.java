package cn.shopin.oneposition.fragments.moviefrag.piecerate;

import java.util.List;

import javax.inject.Inject;

import cn.shopin.oneposition.entity.movie.PieceEntity;
import cn.shopin.oneposition.model.net.RetrofitHelper;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.util.RxUtil;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by zcs on 2017/5/2.
 */

public class MoviePiecePresenter extends BasePresenter<PieceContract.IPieceView> implements PieceContract.IPiecePresenter {
    private RetrofitHelper retrofitHelper;

    @Inject
    public MoviePiecePresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void loadMvDetail(String timeStr) {
        addSubscription(retrofitHelper.getMoviePieceEntities(timeStr)
                .compose(RxUtil.<List<PieceEntity>>rxSchedulerHelper())
                .subscribe(new Consumer<List<PieceEntity>>() {
                    @Override
                    public void accept(@NonNull List<PieceEntity> pieceEntities) throws Exception {
                        getView().setMvDetail(pieceEntities);
                    }
                }));
    }
}
