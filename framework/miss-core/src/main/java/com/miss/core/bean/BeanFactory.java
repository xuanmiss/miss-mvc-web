package com.miss.core.bean;

import com.miss.core.annotation.Autowired;
import com.miss.core.annotation.Component;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.miss.core.AnnotationUtil.checkoutComponentAnnotation;
import static com.miss.core.AnnotationUtil.recursivelyCollectMetaAnnotations;

/**
 * @project: miss-web
 * @package: bean
 * @author: miss
 * @since: 2021/2/27 14:41
 * @history: 1.2021/2/27 created by miss
 */
public class BeanFactory {

    // ConcurrentHashMap 保存 Bean的容器
    public static Map<Class<?>, Object> classToBeanMap = new ConcurrentHashMap<>();

    /**
     * @param clazz
     * @return object
     */
    public static Object getBeanByClass(Class<?> clazz) {
        return classToBeanMap.get(clazz);
    }

    public static void initBean(List<Class<?>> classList) throws Exception {
        List<Class<?>> toCreateBeanClass = new ArrayList<>(classList);

        while (toCreateBeanClass.size() > 0) {
            System.out.println("循环初始化bean");

            int remainSize = toCreateBeanClass.size();
            for (int i = 0; i < toCreateBeanClass.size(); i++) {
                System.out.println("待初始化为bean的class: " + toCreateBeanClass.size());
                System.out.println("正在处理第 " + i + " 个class");
                System.out.println("当前正在处理Class: " + toCreateBeanClass.get(i));

                if (finishCreate(toCreateBeanClass.get(i))) {
                    System.out.println("Remove Class: " + toCreateBeanClass.get(i));
                    toCreateBeanClass.remove(i);
                    i--;
                }
            }
            if (toCreateBeanClass.size() == remainSize) {
                System.out.println(toCreateBeanClass.size());
                throw new Exception("循环依赖异常");
            }
        }
        System.out.println("初始化bean结束: " + toCreateBeanClass.size());

    }

    private static boolean finishCreate(Class<?> clazz) throws IllegalAccessException, InstantiationException {

        // 如果不是bean的话，直接返回true && !clazz.isAnnotationPresent()
        Set<String> metaAnnotationTypeNames = new LinkedHashSet<>();
        for(Annotation annotation : clazz.getAnnotations()) {
            recursivelyCollectMetaAnnotations(annotation, metaAnnotationTypeNames);
        }

        if(!checkoutComponentAnnotation(metaAnnotationTypeNames, Component.class.getName())) {
            return true;
        }




        Object bean = clazz.newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Class<?> fieldType = field.getType();
                Object reliantBean = BeanFactory.getBeanByClass(fieldType);
                if (reliantBean == null) {
                    // 如果没有存在bean, 则返回false，先处理下一个class
                    return false;
                }
                field.setAccessible(true);
                field.set(bean, reliantBean);
            }
        }
        classToBeanMap.put(clazz, bean);
        return true;
    }

//    private static void recursivelyCollectMetaAnnotations(Annotation annotation, Set<String> metaAnnotationTypeNames) {
//        if(metaAnnotationTypeNames.add(annotation.annotationType().getName())) {
//            for(Annotation metaAnnotation : annotation.annotationType().getAnnotations()) {
//                recursivelyCollectMetaAnnotations(metaAnnotation, metaAnnotationTypeNames);
//            }
//        }
//    }
//
//    private static boolean checkoutComponentAnnotation(Set<String> metaAnnotations) {
//       if(metaAnnotations != null && metaAnnotations.size() <= 0) {
//           return false;
//       }
//       return metaAnnotations.contains(Component.class.getName());
//    }


}
