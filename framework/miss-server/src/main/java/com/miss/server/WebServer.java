package com.miss.server;

import org.apache.catalina.LifecycleException;

/**
 * @project: miss-core
 * @package: com.miss.server
 * @author: miss
 * @since: 2021/3/10 15:43
 * @history: 1.2021/3/10 created by miss
 */
public interface WebServer {

    /**
     * webserver start method
     * @throws LifecycleException
     */
    void start() throws LifecycleException;

    void stop();
}
