package com.mist.cloudtestingplatform.annotation;

import java.lang.annotation.*;

/**
 * Created by Prophet on 2017/12/27.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DeviceAuth {

    boolean value() default true;

}
