package com.miss.web;

import com.miss.core.ClassScanner;
import com.miss.core.bean.BeanFactory;
import com.miss.core.utils.PropUtils;
import com.miss.web.handler.HandlerManager;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.miss.web.BannerHelper.banner;


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

        try {
            System.out.println("Root package is: " + this.rootClass.getPackage().getName());
//            "com.miss.core", "com.miss.web",
            URL url =this.rootClass.getClassLoader().getResource("miss.properties");
            if (url != null) {
                Properties properties = PropUtils.loadProperties(new File(url.getFile()));
                BeanFactory.loadProperties(properties);
            }
            List<Class<?>> classList = ClassScanner.loadClassFromPackages(Collections.singletonList(this.rootClass.getPackage().getName()));
            List<Properties> propertiesList = ClassScanner.loadPropConfig("META-INF/miss.factories");
            List<String> classNames = propertiesList.stream()
                    .map(property -> property.getProperty("com.miss.AutoConfiguration"))
                    .collect(Collectors.toList());
            List<Class<?>> configClassList = ClassScanner.loadClassFromNameList(classNames);
            classList.addAll(configClassList);

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
