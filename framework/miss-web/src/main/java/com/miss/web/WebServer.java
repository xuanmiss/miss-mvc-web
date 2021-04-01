package com.miss.web;

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
     * @throws Exception
     */
    void start() throws Exception;

    void stop();
}
