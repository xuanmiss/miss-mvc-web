package com.miss.web;

/**
 * @project: miss-core
 * @package: com.miss.server
 * @author: miss
 * @since: 2021/3/10 15:42
 * @history: 1.2021/3/10 created by miss
 */
@FunctionalInterface
public interface WebServerFactory {

    WebServer getWebServer(String[] args);
}
