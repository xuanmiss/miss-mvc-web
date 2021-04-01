package com.miss.server;

import com.miss.core.bean.BeanFactory;
import com.miss.web.WebServer;
import com.miss.web.servlet.DisPatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * @project: miss-mvc-web
 * @package: com.miss.server
 * @author: miss
 * @since: 2021/3/1 12:13
 * @history: 1.2021/3/1 created by miss
 */
public class TomcatServer implements WebServer {

    private Tomcat tomcat;

    private String[] args;

    public TomcatServer(String[] args) {
        this.args = args;
    }

    public void startServer() throws Exception {
        tomcat = new Tomcat();
        Connector connector = new Connector();
        // 监听地址
        connector.setProperty("address", "0.0.0.0");
        connector.setPort(Integer.parseInt(BeanFactory.getProperty("server.port", "8080")));
        Host host = tomcat.getHost();
        host.setName("localhost");
        tomcat.setConnector(connector);
        tomcat.start();

        Context context = new StandardContext();

        context.setPath(BeanFactory.getProperty("server.path", "/miss"));
        context.addLifecycleListener(new Tomcat.FixContextListener());

        DisPatcherServlet servlet = new DisPatcherServlet();
        Tomcat.addServlet(context, "dispatcherServlet", servlet).setAsyncSupported(true);

        // 添加映射
        context.addServletMappingDecoded("/", "dispatcherServlet");
        tomcat.getHost().addChild(context);

        Thread awaitThread = new Thread("tomcat_await_thread.") {
            @Override
            public void run() {
                TomcatServer.this.tomcat.getServer().await();
            }
        };

        awaitThread.setDaemon(false);
        awaitThread.start();

        System.out.println(tomcat.getServer().getAddress());


    }

    @Override
    public void start() throws Exception {
        this.startServer();
    }

    @Override
    public void stop() {

    }
}
