package com.miss.test;

import com.miss.core.ClassScanner;
import com.miss.test.annotation.ListenerConfiguration;
import com.miss.test.event.MissEvent;
import com.miss.test.event.MissEventListener;
import com.miss.test.event.TestLogMissEvent;
import com.miss.test.event.TestLogMissEventListener;
import com.miss.web.MissApplication;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test
 * @author: miss
 * @since: 2021/3/1 12:28
 * @history: 1.2021/3/1 created by miss
 */
public class Application {

    private static Map<String, MissEventListener<MissEvent>> eventHandleMap = new HashMap<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        MissApplication.run(Application.class, args);
        List<Class<?>> classList = ClassScanner.scannClasses("com.miss.test.event");
        for (Class<?> clazz : classList) {
            if (Arrays.stream(clazz.getAnnotations()).map(annotation -> annotation.annotationType().getName()).collect(Collectors.toSet()).contains(ListenerConfiguration.class.getName())) {
                Type paramType = Arrays.stream((((ParameterizedTypeImpl) clazz.getGenericInterfaces()[0]).getActualTypeArguments())).filter(type -> {
                    return MissEvent.class.isAssignableFrom((Class) type);
                }).findAny().get();
                eventHandleMap.put(paramType.getTypeName(), (MissEventListener<MissEvent>) clazz.newInstance());
            }
        }

        System.out.println(eventHandleMap);

        eventHandleMap.get(TestLogMissEvent.class.getName()).onMissEvent(new TestLogMissEvent("this", "this is a log"));
    }
}
