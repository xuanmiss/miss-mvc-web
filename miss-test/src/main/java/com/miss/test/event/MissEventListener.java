package com.miss.test.event;

import java.util.EventListener;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test.event
 * @author: miss
 * @date: 2021/5/24 15:42
 * @since:
 * @history: 1.2021/5/24 created by miss
 */

@FunctionalInterface
public interface MissEventListener<E extends MissEvent> extends EventListener {
    /**
     * @param e event
     */
    void onMissEvent(E e);
}
