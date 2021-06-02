package com.miss.test.event;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test.event
 * @author: miss
 * @date: 2021/5/26 16:52
 * @since:
 * @history: 1.2021/5/26 created by miss
 */
public class MissWebEventPublisher implements MissEventPublisher{


    @Override
    public void publishEvent(MissEvent e) {
        MissEventPublisher.super.publishEvent(e);
    }

    @Override
    public void publishEvent(Object o) {

    }

    protected void publish Event(Object event, @Null)


}
