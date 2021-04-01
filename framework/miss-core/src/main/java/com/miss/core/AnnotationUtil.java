package com.miss.core;

import com.miss.core.annotation.Component;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @project: miss-core
 * @package: com.miss.core
 * @author: miss
 * @since: 2021/3/9 16:22
 * @history: 1.2021/3/9 created by miss
 */
public class AnnotationUtil {

    public static void recursivelyCollectMetaAnnotations(Annotation annotation, Set<String> metaAnnotationTypeNames) {
        if(metaAnnotationTypeNames.add(annotation.annotationType().getName())) {
            for(Annotation metaAnnotation : annotation.annotationType().getAnnotations()) {
                recursivelyCollectMetaAnnotations(metaAnnotation, metaAnnotationTypeNames);
            }
        }
    }

    public static boolean checkoutComponentAnnotation(Set<String> metaAnnotations, String metaName) {
        if(metaAnnotations == null || metaAnnotations.size() <= 0) {
            return false;
        }else {
            return metaAnnotations.contains(metaName);
        }
    }
}
