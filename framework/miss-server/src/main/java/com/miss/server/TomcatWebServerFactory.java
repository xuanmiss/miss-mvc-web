package com.miss.server;

import com.miss.core.annotation.Component;

/**
 * @project: miss-core
 * @package: com.miss.server
 * @author: miss
 * @since: 2021/3/10 15:55
 * @history: 1.2021/3/10 created by miss
 */
@Component
public class TomcatWebServerFactory implements WebServerFactory{
    @Override
    public WebServer getWebServer(String[] args) {
        return new TomcatServer(args);
    }
}
