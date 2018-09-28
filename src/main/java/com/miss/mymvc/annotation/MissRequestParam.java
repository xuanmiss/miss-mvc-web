package com.miss.mymvc.annotation;

import java.lang.annotation.*;

/**
 * Created By peishengzhang
 * <p>
 * At 2018/9/27
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MissRequestParam {

    String value();
}
