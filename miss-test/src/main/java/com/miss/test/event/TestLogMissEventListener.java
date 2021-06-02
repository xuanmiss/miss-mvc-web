package com.miss.test.event;

import com.miss.test.annotation.ListenerConfiguration;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test.event
 * @author: miss
 * @date: 2021/5/25 18:25
 * @since:
 * @history: 1.2021/5/25 created by miss
 */
@ListenerConfiguration
public class TestLogMissEventListener implements MissEventListener<TestLogMissEvent> {

    @Override
    public void onMissEvent(TestLogMissEvent testLogMissEvent) {
        System.out.println("this is a log");
    }
}
