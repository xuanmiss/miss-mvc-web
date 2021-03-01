package com.miss.web.annotation;

import java.lang.annotation.*;

/**
 * @project: miss-web
 * @package: annotation
 * @author: miss
 * @since: 2021/2/27 14:28
 * @history: 1.2021/2/27 created by miss
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {

    String value() default "";
}
