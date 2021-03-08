package com.miss.web.annotation;

import java.lang.annotation.*;

/**
 * @project: miss-core
 * @package: com.miss.web.annotation
 * @author: miss
 * @since: 2021/3/8 15:24
 * @history: 1.2021/3/8 created by miss
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestBody {
}
