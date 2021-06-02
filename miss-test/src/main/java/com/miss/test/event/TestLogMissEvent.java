package com.miss.test.event;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test.event
 * @author: miss
 * @date: 2021/5/25 18:25
 * @since:
 * @history: 1.2021/5/25 created by miss
 */
public class TestLogMissEvent extends MissEvent{
    private String logContent;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TestLogMissEvent(Object source, String logContent) {
        super(source);
        this.logContent = logContent;
    }

    public String getLogContent() {
        return logContent;
    }
}
