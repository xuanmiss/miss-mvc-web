package com.miss.core.annotation;

import java.lang.annotation.*;

/**
 * @project: miss-web
 * @package: annotation
 * @author: miss
 * @since: 2021/2/27 14:37
 * @history: 1.2021/2/27 created by miss
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {

    String[] value() default {};
}
