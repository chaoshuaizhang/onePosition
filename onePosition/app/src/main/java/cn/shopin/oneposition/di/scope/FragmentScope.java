package cn.shopin.oneposition.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by zcs on 2017/4/19.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}
