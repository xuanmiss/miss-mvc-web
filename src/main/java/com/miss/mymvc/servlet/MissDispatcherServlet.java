//package com.miss.mymvc.servlet;
//
//import com.miss.mymvc.annotation.MissController;
//import com.miss.mymvc.annotation.MissRequestMapping;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.*;
//import java.util.Map.Entry;
//
///**
// * Created By peishengzhang
// * <p>
// * At 2018/9/27
// */
//public class MissDispatcherServlet extends HttpServlet {
//
//    private Properties properties = new Properties();
//    private List<String> classNames = new ArrayList<>();
//    private Map<String, Object> ioc = new HashMap<>();
//    private Map<String, Method> handlerMapping = new HashMap<>();
//    private Map<String, Object> controllerMap = new HashMap<>();
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//
//        //1.加载配置文件
////        System.out.println(config.getInitParameter("contextConfigLocation"));
//        doLoadConfig(config.getInitParameter("contextConfigLocation"));
//
//
//        //2.初始化所有相关联的类,扫描用户设定的包下面所有的类
//        doScanner(properties.getProperty("scanPackage"));
//
//        //3.拿到扫描到的类,通过反射机制,实例化,并且放到ioc容器中(k-v  beanName-bean) beanName默认是首字母小写
//        doInstance();
//
//        //4.初始化HandlerMapping(将url和method对应上)
//        initHandlerMapping();
//
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
//        this.doPost(req,resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
//        try{
//            doDispatch(req,resp);
//        }catch (Exception e){
//            resp.getWriter().write("500!! Miss's Server Exception");
//        }
//    }
//
//    private void doDispatch(HttpServletRequest req,HttpServletResponse resp) throws Exception{
//        if(handlerMapping.isEmpty()){
//            return;
//        }
//
//
//        String url = req.getRequestURI();
//        String contextPath = req.getContextPath();
//        url = url.replace(contextPath,"").replaceAll("/+","/");
////        System.out.println(url);
////
////        for(Entry<String,Method> entry: handlerMapping.entrySet()){
////            System.out.println("key:"+entry.getKey()+"***value:"+entry.getValue().toString());
////        }
//
//        if(!this.handlerMapping.containsKey(url)){
//            resp.getWriter().write("404 Miss's NOT FOUND");
//            return;
//        }
//
//        Method method = this.handlerMapping.get(url);
//        //方法的参数列表
//        Class<?>[] parameterTypes = method.getParameterTypes();
//        //请求参数
//        Map<String,String[]> parameterMap = req.getParameterMap();
//        //保存参数
//        Object[] paramValues = new Object[parameterTypes.length];
//
//        //方法参数列表
//        for(int i = 0; i < parameterTypes.length; i++){
//            String requestParamType = parameterTypes[i].getSimpleName();
//
//            if(requestParamType.equals("HttpServletRequest")){
//                paramValues[i] = req;
//                continue;
//            }
//            if(requestParamType.equals("HttpServletResponse")){
//                paramValues[i] = resp;
//                continue;
//            }
//            if(requestParamType.equals("String")){
//                for(Entry<String,String[]> param : parameterMap.entrySet()){
//                    String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
//                    paramValues[i] = value;
//                }
//            }
//
//        }
//        try{
//            method.invoke(this.controllerMap.get(url),paramValues);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    private void doLoadConfig(String location) {
//        //把web.xml中的contextConfigLocation对应value值的文件加载到流里面
//        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(location);
//        try {
//            //用Properties文件加载文件里的内容
//            properties.load(resourceAsStream);
//            System.out.println(properties.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            //关流
//            if (null != resourceAsStream) {
//                try {
//                    resourceAsStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private void doScanner(String packageName) {
////        System.out.println(packageName);
//        System.out.println(this.getClass().getClassLoader().getResource(""));
//        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
////        System.out.println(url);
//        File dir = new File(url.getFile());
//        for (File file : dir.listFiles()) {
//            if (file.isDirectory()) {
//                doScanner(packageName + "." + file.getName());
//            } else {
//                String className = packageName + "." + file.getName().replace(".class", "");
//                classNames.add(className);
//            }
//        }
//    }
//
//    private void doInstance() {
//        if (classNames.isEmpty()) {
//            return;
//        }
//        for (String className : classNames) {
//            try {
//                Class<?> clazz = Class.forName(className);
//                if (clazz.isAnnotationPresent(MissController.class)) {
//                    ioc.put(toLowerFirstWord(clazz.getSimpleName()), clazz.newInstance());
//                } else {
//                    continue;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
//    }
//
//    private void initHandlerMapping(){
//        if(ioc.isEmpty()){
//            return;
//        }
//        try{
//            for(Entry<String,Object> entry : ioc.entrySet()){
//                Class<? extends Object> clazz = entry.getValue().getClass();
//                if(!clazz.isAnnotationPresent(MissController.class)){
//                    continue;
//                }
//                String baseUrl = "";
//                if(clazz.isAnnotationPresent(MissRequestMapping.class)){
//                    MissRequestMapping annotation = clazz.getAnnotation(MissRequestMapping.class);
//
//                    baseUrl = annotation.value();
//                }
//                if(!baseUrl.startsWith("/")){
//                    baseUrl = "/"+baseUrl;
//                }
//                Method[] methods = clazz.getMethods();
//                for(Method method : methods){
//                    if(!method.isAnnotationPresent(MissRequestMapping.class))
//                    {
//                        continue;
//                    }
//                    MissRequestMapping annotation = method.getAnnotation(MissRequestMapping.class);
//                    String url = annotation.value();
//                    url = (baseUrl + "/" + url).replaceAll("/+","/");
//                    handlerMapping.put(url,method);
//                    controllerMap.put(url,clazz.newInstance());
//                    System.out.println(url + "," + method);
//
//                }
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 把字符串的首字母小写
//     *
//     * @param name
//     * @return
//     */
//    private String toLowerFirstWord(String name) {
//        char[] charArray = name.toCharArray();
//        charArray[0] += 32;
//        return String.valueOf(charArray);
//    }
//
//
//}
