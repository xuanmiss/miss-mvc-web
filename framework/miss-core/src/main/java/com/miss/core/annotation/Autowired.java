package com.miss.core.annotation;

import java.lang.annotation.*;

/**
 * @project: miss-web
 * @package: annotation
 * @author: miss
 * @since: 2021/2/26 18:42
 * @history: 1.2021/2/26 created by miss
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

//    String value() default "";

    boolean required() default true;

}