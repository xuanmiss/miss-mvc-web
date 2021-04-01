package com.miss.core.annotation;

import java.lang.annotation.*;

/**
 * @project: miss-core
 * @package: com.miss.core.annotation
 * @author: miss
 * @since: 2021/3/9 16:49
 * @history: 1.2021/3/9 created by miss
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface AliasFor {

    String value() default "";

    Class<? extends Annotation> annotation() default Annotation.class;
}
