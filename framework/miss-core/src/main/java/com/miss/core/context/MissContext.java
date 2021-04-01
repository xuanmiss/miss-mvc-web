//package com.miss.core.context;
//
//import com.miss.core.annotation.Autowired;
//import com.miss.core.annotation.Component;
//import com.miss.core.bean.BeanFactory;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//import static com.miss.core.utils.AnnotationUtil.checkoutComponentAnnotation;
//import static com.miss.core.utils.AnnotationUtil.recursivelyCollectMetaAnnotations;
//
///**
// * @project: miss-mvc-web
// * @package: com.miss.core.context
// * @author: miss
// * @date: 2021/4/1 15:49
// * @since:
// * @history: 1.2021/4/1 created by miss
// */
//public class MissContext implements BeanFactory {
//
//    // 1 -> 1 以class 为key，单例
//    public Map<Class<?>, Object> classToBeanMap = new ConcurrentHashMap<>();
//
//    // 1 -> 1 以class类名首字母小写为bean的key，单例
//    public Map<String, Object> nameToBeanMap = new ConcurrentHashMap<>();
//
//    /**
//     * <p>
//     * 1 -> nameToBeanMap 以类型（interface） 为key， Map<name, object> 以class类名首字母写为key
//     * </p>
//     */
//    public Map<Class<?>, Map<String, Object>> typeToBeanMap = new ConcurrentHashMap<>();
//
//
//    public MissContext() {
//        classToBeanMap.put(this.getClass(), this);
//        nameToBeanMap.put(toLowerFirstWord(this.getClass().getSimpleName()), this);
//        Map<String, Object> thisBeanMap = new HashMap<>();
//        thisBeanMap.put(toLowerFirstWord(this.getClass().getSimpleName()), this);
//        typeToBeanMap.put(BeanFactory.class, thisBeanMap);
//    }
//
//    @Override
//    public Map<Class<?>, Object> getClassToBeanMap() {
//        return classToBeanMap;
//    }
//    /**
//     * @param clazz
//     * @return object
//     */
//    @Override
//    public Object getBeanByClass(Class<?> clazz) {
//        return classToBeanMap.get(clazz);
//    }
//
//    /**
//     * <p>
//     *
//     * </p>
//     * @author miss
//     * @name getBeanOfType
//     * @description
//     * @date 11:52 2021/4/1
//     * @since
//     * @param fieldType
//     * @return java.lang.Object
//     */
//    @Override
//    public Object getBeanOfType(Class<?> fieldType) {
//        return Optional.ofNullable(typeToBeanMap.get(fieldType))
//                .map(map -> {
//                    List<Object> beans = Arrays.asList(map.values().toArray());
//
//                    if (!beans.isEmpty()) {
//                        return beans.get(0);
//                    }else {
//                        return null;
//                    }
//                }).orElse(null);
//    }
//
//    @Override
//    public Map<String, Object> getBeansOfType(Class<?> type) {
//        return typeToBeanMap.get(type);
//    }
//
//    @Override
//    public Object getBeanByName(String beanName) {
//        return nameToBeanMap.get(beanName);
//    }
//
//    @Override
//    public void initBean(List<Class<?>> classList) throws Exception {
//        List<Class<?>> toCreateBeanClass = new ArrayList<>(classList);
//
//        while (toCreateBeanClass.size() > 0) {
//            System.out.println("循环初始化bean");
//
//            int remainSize = toCreateBeanClass.size();
//            for (int i = 0; i < toCreateBeanClass.size(); i++) {
//                System.out.println("待初始化为bean的class: " + toCreateBeanClass.size());
//                System.out.println("正在处理第 " + i + " 个class");
//                System.out.println("当前正在处理Class: " + toCreateBeanClass.get(i));
//
//                if (finishCreate(toCreateBeanClass.get(i))) {
//                    System.out.println("Remove Class: " + toCreateBeanClass.get(i));
//                    toCreateBeanClass.remove(i);
//                    i--;
//                }
//            }
//            if (toCreateBeanClass.size() == remainSize) {
//                System.out.println(toCreateBeanClass.size());
//                throw new Exception("循环依赖异常");
//            }
//        }
//        System.out.println("初始化bean结束: " + toCreateBeanClass.size() + "已装入总bean个数: " + classToBeanMap.size());
//
//    }
//
//    private boolean finishCreate(Class<?> clazz) throws IllegalAccessException, InstantiationException {
//
//        if (clazz.isAnnotation()) {
//            return true;
//        }
//
//        // 如果不是bean的话，直接返回true && !clazz.isAnnotationPresent()
//        Set<String> metaAnnotationTypeNames = new LinkedHashSet<>();
//        for(Annotation annotation : clazz.getAnnotations()) {
//            recursivelyCollectMetaAnnotations(annotation, metaAnnotationTypeNames);
//        }
//
//        if(!checkoutComponentAnnotation(metaAnnotationTypeNames, Component.class.getName())) {
//            return true;
//        }
//
//        Object bean = clazz.newInstance();
//        classToBeanMap.put(clazz, bean);
//        nameToBeanMap.put(toLowerFirstWord(clazz.getSimpleName()), bean);
//
//        Class<?> [] interfaces = clazz.getInterfaces();
//        for(Class<?> clasz : interfaces) {
//            if(typeToBeanMap.get(clasz) != null) {
//                typeToBeanMap.get(clasz).put(toLowerFirstWord(clazz.getSimpleName()), bean);
//            }else {
//                Map<String, Object> nameBeanMap = new ConcurrentHashMap<>();
//                nameBeanMap.put(toLowerFirstWord(clazz.getSimpleName()), bean);
//                typeToBeanMap.put(clasz, nameBeanMap);
//            }
//        }
//
//        for (Field field : clazz.getDeclaredFields()) {
//            if (field.isAnnotationPresent(Autowired.class)) {
//                Object reliantBean;
//                Class<?> fieldType = field.getType();
//
//                if (fieldType.isInterface()) {
//                    reliantBean = this.getBeanOfType(fieldType);
//                }else {
//                    reliantBean = this.getBeanByClass(fieldType);
//                }
//                if (reliantBean == null) {
//                    // 如果没有存在bean, 则返回false，先处理下一个class
//                    return false;
//                }
//                field.setAccessible(true);
//                field.set(bean, reliantBean);
//            }
//        }
//        return true;
//    }
//
////    private static void recursivelyCollectMetaAnnotations(Annotation annotation, Set<String> metaAnnotationTypeNames) {
////        if(metaAnnotationTypeNames.add(annotation.annotationType().getName())) {
////            for(Annotation metaAnnotation : annotation.annotationType().getAnnotations()) {
////                recursivelyCollectMetaAnnotations(metaAnnotation, metaAnnotationTypeNames);
////            }
////        }
////    }
////
////    private static boolean checkoutComponentAnnotation(Set<String> metaAnnotations) {
////       if(metaAnnotations != null && metaAnnotations.size() <= 0) {
////           return false;
////       }
////       return metaAnnotations.contains(Component.class.getName());
////    }
//    /**
//     * 把字符串的首字母小写
//     *
//     * @param name
//     * @return
//     */
//    private static String toLowerFirstWord(String name) {
//        char[] charArray = name.toCharArray();
//        charArray[0] += 32;
//        return String.valueOf(charArray);
//    }
//}
