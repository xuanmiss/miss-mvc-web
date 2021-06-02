package com.miss.test.event;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test.event
 * @author: miss
 * @date: 2021/5/26 15:44
 * @since:
 * @history: 1.2021/5/26 created by miss
 */
@FunctionalInterface
public interface MissEventPublisher {

    default void publishEvent(MissEvent e) {
        publishEvent((Object) e);
    }

    /**
     * @param o 事件对象
     */
    void publishEvent(Object o);
}
