package cn.shopin.oneposition.di.component;

import javax.inject.Singleton;

import cn.shopin.oneposition.Myapplication;
import cn.shopin.oneposition.di.module.AppModule;
import cn.shopin.oneposition.di.module.HttpModule;
import cn.shopin.oneposition.model.net.RetrofitHelper;
import dagger.Component;

/**
 * Created by zcs on 2017/4/19.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    Myapplication getApplicationContext();

    RetrofitHelper provideRetrofitHelper();
}
