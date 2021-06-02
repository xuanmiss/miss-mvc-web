package com.miss.test.annotation;

import java.lang.annotation.*;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test.annotation
 * @author: miss
 * @date: 2021/5/25 18:47
 * @since:
 * @history: 1.2021/5/25 created by miss
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ListenerConfiguration {
}
