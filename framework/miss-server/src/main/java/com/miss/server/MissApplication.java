package com.miss.server;

import com.miss.core.ClassScanner;
import com.miss.core.bean.BeanFactory;
import com.miss.web.handler.HandlerManager;

import java.util.Arrays;
import java.util.List;

import static com.miss.server.BannerHelper.banner;

/**
 * @project: miss-mvc-web
 * @package: com.miss.server
 * @author: miss
 * @since: 2021/3/1 11:54
 * @history: 1.2021/3/1 created by miss
 */
public class MissApplication {

    public  Class<?> rootClass;

    public MissApplication(Class<?> rootClass) {
        this.rootClass = rootClass;
    }

    public static void run(Class<?> clazz, String[] args) {
        new MissApplication(clazz).run(args);

    }
    public void run(String[] args) {
        System.out.println("Hello Miss-Web application! ");
        banner(this.getClass().getClassLoader().getResource("banner.txt").getPath(), 4);

//        ((ProtectionDomain)this.getClass().getClassLoader().domains.toArray()[0]).codesource.getLocation().getPath();


        try {
            System.out.println("Root package is: " + this.rootClass.getPackage().getName());
            List<Class<?>> classList = ClassScanner.loadClassFromPackages(Arrays.asList("com.miss.server", "com.miss.core", "com.miss.web",this.rootClass.getPackage().getName()));
            BeanFactory.initBean(classList);
            HandlerManager.resolveMappingHandler();
            WebServerFactory webServerFactory = this.getWebServerFactory();
            WebServer webServer = webServerFactory.getWebServer(args);
            webServer.start();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WebServerFactory getWebServerFactory() {
        return (WebServerFactory) BeanFactory.getBeanOfType(WebServerFactory.class);
    }
}
