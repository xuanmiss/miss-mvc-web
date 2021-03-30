package com.miss.web.annotation;

import com.miss.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @project: miss-core
 * @package: com.miss.web.annotation
 * @author: miss
 * @since: 2021/3/9 16:17
 * @history: 1.2021/3/9 created by miss
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(method = RequestMethod.GET)
public @interface GetMapping {

    @AliasFor(annotation = RequestMapping.class)
    String value() default "";
}
