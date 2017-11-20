package com.mist.cloudtestingplatform.annotation;

import java.lang.annotation.*;

/**
 * Created by Prophet on 2017/11/20.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Auth {

    boolean value() default true;

}
