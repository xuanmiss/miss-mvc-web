package com.miss.web.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miss.core.bean.BeanFactory;
import com.miss.web.annotation.RequestBody;
import com.miss.web.annotation.RequestMapping;
import com.miss.web.annotation.RequestParam;
import com.miss.web.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @project: miss-mvc-web
 * @package: handler
 * @author: miss
 * @since: 2021/2/27 15:43
 * @history: 1.2021/2/27 created by miss
 */
public class MappingHandler {

    private String uri;

    private Method method;

    private Class<?> controllerClass;
    private final ObjectMapper objectMapper;

    public MappingHandler(String uri, Method method, Class<?> controllerClass) {
        this.uri = uri;
        this.method = method;
        this.controllerClass = controllerClass;
        this.objectMapper = new ObjectMapper();
    }

    public boolean handle(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String requestUrl = req.getRequestURI();

        String contextPath = req.getContextPath();

        requestUrl = requestUrl.replace(contextPath, "").replaceAll("/+", "/");

        if (!this.uri.equals(requestUrl)) {
            return false;
        }

        // 方法的参数列表
        Parameter[] parameters = this.method.getParameters();

        // 请求参数和值
        Map<String, String[]> parameterMap = req.getParameterMap();

        // 请求体的参数
        Object requestBody;
        // 参数的值
        List<Object> paramValues = new ArrayList<>();

        for (Parameter parameter : parameters) {
            if (parameter.isAnnotationPresent(RequestParam.class)) {
                String value;
                if (!parameter.getAnnotation(RequestParam.class).value().isEmpty()) {
                    value = Arrays.toString(parameterMap.get(parameter.getAnnotation(RequestParam.class).value()))
                            .replaceAll("\\[|\\]", "").
                                    replaceAll(",\\s", ",");
                } else {
                    value = Arrays.toString(parameterMap.get(parameter.getName()))
                            .replaceAll("\\[|\\]", "").
                                    replaceAll(",\\s", ",");
                }
                paramValues.add(value);
            } if (parameter.isAnnotationPresent(RequestBody.class)) {
               requestBody = this.objectMapper.readValue(req.getInputStream(), parameter.getType());
               paramValues.add(requestBody);
            }else if (parameter.getParameterizedType() == ServletRequest.class) {
                paramValues.add(req);
            } else if (parameter.getParameterizedType() == ServletResponse.class) {
                paramValues.add(res);
            } else {
                paramValues.add(null);
            }
        }
        System.out.println("ParamValues is : " + paramValues);
        Object ctl = BeanFactory.getBeanByClass(controllerClass);
        try {
            Object body = method.invoke(ctl, paramValues.toArray());
            Object responseBody = this.convertBody(body, method);
            res.getWriter().write(responseBody.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private Object convertBody(Object body, Method method) throws JsonProcessingException {
        if(method.isAnnotationPresent(ResponseBody.class)) {
            body = this.objectMapper.writeValueAsString(body);
        }
        return body;
    }
}
