package com.baihy.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: multidatasource
 * @packageName: com.baihy.core.annotation
 * @Description:
 * @author: huayang.bai
 * @date: 2018-12-10 19:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataSource {

    String value() default "";
}
