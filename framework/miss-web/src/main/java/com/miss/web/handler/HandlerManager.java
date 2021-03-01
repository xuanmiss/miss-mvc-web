package com.miss.web.handler;

import com.miss.core.annotation.Controller;
import com.miss.core.bean.BeanFactory;
import com.miss.web.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @project: miss-mvc-web
 * @package: handler
 * @author: miss
 * @since: 2021/2/27 15:43
 * @history: 1.2021/2/27 created by miss
 */
public class HandlerManager {


    public static List<MappingHandler> mappingHandlerList = new ArrayList<>();

    public static Map<String, MappingHandler> mappingHandlerMap = new ConcurrentHashMap<>();

    public static void resolveMappingHandler() {
        for(Map.Entry bean : BeanFactory.classToBeanMap.entrySet()) {
            Class<?> clazz = bean.getValue().getClass();
            if (clazz.isAnnotationPresent(Controller.class)) {
                presentHandlerFromController(clazz);
            }
        }
    }

    private static void presentHandlerFromController(Class<?> clazz) {
        String baseUrl = "";
        if(clazz.isAnnotationPresent(RequestMapping.class)) {
            baseUrl = clazz.getAnnotation(RequestMapping.class).value();
        }
        baseUrl = addDelimiterToPath(baseUrl);

        Method[] methods = clazz.getMethods();
        for(Method method : methods) {
            if (!method.isAnnotationPresent(RequestMapping.class)) {
                continue;
            }

            String url = method.getAnnotation(RequestMapping.class).value();

            url = (baseUrl + addDelimiterToPath(url)).replaceAll("/+", "/");

            MappingHandler mappingHandler = new MappingHandler(url, method, clazz);
            HandlerManager.mappingHandlerList.add(mappingHandler);
            HandlerManager.mappingHandlerMap.put(url, mappingHandler);
        }
    }

    private static String addDelimiterToPath(String url) {
        if (!url.startsWith("/")) {
            url = "/" + url;
        }
        return url;
    }

}
