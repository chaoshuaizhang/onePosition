package cn.shopin.oneposition.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

/**
 * Created by zcs on 2017/4/19.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MovieUrl {
}
