package cn.shopin.oneposition.fragments.moviefrag;

/**
 * Created by zcs on 2016/12/7.
 */
public class MoviePresenter extends MovieContract.IMoviePresenter {
    private MovieContract.IMovieView movieView;
    private MovieContract.IMovieModel movieModel;

    public MoviePresenter(MovieContract.IMovieView iMovieView) {
        super(iMovieView);
        movieView = iMovieView;
        movieModel = new MovieModel();
    }

    @Override
    void getBanners() {
        movieModel.getBanners();
    }

    @Override
    void getBannerAds() {

    }
}
