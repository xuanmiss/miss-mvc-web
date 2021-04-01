package com.miss.core;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @project: miss-web
 * @package: com.miss.core
 * @author: miss
 * @since: 2021/2/26 16:36
 * @history: 1.2021/2/26 created by miss
 */
public class ClassScanner {

    public static List<Class<?>> loadClassFromPackages(List<String> packageNames) throws IOException, ClassNotFoundException{
        List<Class<?>> classList = new ArrayList<>();
        for (String packageName : packageNames) {
            classList.addAll(scannClasses(packageName));
        }
        return classList;
    }

    public static List<Class<?>> scannClasses(String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace(".", "/");
        //获取类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            //处理资源类型是jar包的情况
            if (resource.getProtocol().contains("jar")) {
                JarURLConnection jarURLConnection =
                        (JarURLConnection) resource.openConnection();
                String jarFilePath = jarURLConnection.getJarFile().getName();
                classes.addAll(getClassFromJar(jarFilePath, path));
            } else {
                getClassFromDir(classLoader, packageName, classes);
            }
        }
        return classes;
    }

    public static List<Class<?>> scannClasses(ClassLoader classLoader, String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            //处理资源类型是jar包的情况
            if (resource.getProtocol().contains("jar")) {
                JarURLConnection jarURLConnection =
                        (JarURLConnection) resource.openConnection();
                String jarFilePath = jarURLConnection.getJarFile().getName();
                classes.addAll(getClassFromJar(jarFilePath, path));
            } else {
                getClassFromDir(classLoader, packageName, classes);
            }
        }
        return classes;
    }

    /**
     * @return List<Class < ?>>
     * @Author miss
     * @Description 从jar包中获取资源
     * @Date 17:37 2019-06-08
     * @Param
     **/
    public static List<Class<?>> getClassFromJar(String jarFilePath, String path) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        //获取jar实例
        JarFile jarFile = new JarFile(jarFilePath);
        Enumeration<JarEntry> jarEntrys = jarFile.entries();
        while (jarEntrys.hasMoreElements()) {
            JarEntry jarEntry = jarEntrys.nextElement();
            //获取类路径名  如  com/miss/core/ClassScanner.class
            String entryName = jarEntry.getName();
            //获取的
            if (entryName.startsWith(path) && entryName.endsWith(".class")) {
                //路径替换
                String classFullName = entryName.replace("/", ".").substring(0, entryName.length() - 6);
                //反射获取类信息并添加至list
                classes.add(Class.forName(classFullName));
            }
        }
        return classes;
    }

    public static void getClassFromDir(ClassLoader classLoader, String packageName, List<Class<?>> classNames) throws ClassNotFoundException {
        System.out.println(classLoader.getResource(""));
        URL url = classLoader.getResource(packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                getClassFromDir(classLoader, packageName + "." + file.getName(), classNames);
            } else {
                String className = packageName + "." + file.getName().replace(".class", "");
                classNames.add(Class.forName(className));
            }
        }
    }
}
