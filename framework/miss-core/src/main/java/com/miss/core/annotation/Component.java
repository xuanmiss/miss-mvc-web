package com.miss.core.annotation;

import java.lang.annotation.*;

/**
 * @project: miss-web
 * @package: annotation
 * @author: miss
 * @since: 2021/2/26 16:43
 * @history: 1.2021/2/26 created by miss
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";
}
