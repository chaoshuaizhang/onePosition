package cn.shopin.oneposition.di.component;

import android.app.Activity;
import android.support.v4.app.Fragment;

import javax.inject.Singleton;

import cn.shopin.oneposition.di.module.FragmentModule;
import cn.shopin.oneposition.di.scope.FragmentScope;
import cn.shopin.oneposition.fragments.heartfrag.HeartFragment;
import cn.shopin.oneposition.fragments.moviefrag.MovieFragment;
import cn.shopin.oneposition.fragments.moviefrag.collection.CollectionFrag;
import cn.shopin.oneposition.fragments.moviefrag.nostalgic.NostalgicFrag;
import cn.shopin.oneposition.fragments.moviefrag.piecerate.MoviePieceFrag;
import cn.shopin.oneposition.fragments.webdetail.WebDetailFrag;
import dagger.Component;

/**
 * Created by zcs on 2017/4/19.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {
    // DESC:  可以在Fragment中 通过 getFragmentComponent().getActivity() 来获得对应实例
    Activity getActivity();

    Fragment getFragment();

    void inject(MovieFragment movieFragment);

    void inject(CollectionFrag collectionFrag);

    void inject(NostalgicFrag nostalgicFrag);

    void inject(MoviePieceFrag pieceFrag);

    void inject(WebDetailFrag detailFrag);

    void inject(HeartFragment heartFrag);
}
