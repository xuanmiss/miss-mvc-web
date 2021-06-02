package com.miss.test.event;

import java.util.EventObject;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test.event
 * @author: miss
 * @date: 2021/5/24 15:40
 * @since:
 * @history: 1.2021/5/24 created by miss
 */
public abstract class MissEvent extends EventObject {

    private static final long serialVersionUID = -6421873664014346565L;
    private final long timestamp = System.currentTimeMillis();
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MissEvent(Object source) {
        super(source);
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
