package com.miss.web.servlet;

import com.miss.web.annotation.RequestMethod;
import com.miss.web.handler.HandlerManager;
import com.miss.web.handler.MappingHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @project: miss-mvc-web
 * @package: servlet
 * @author: miss
 * @since: 2021/2/27 16:47
 * @history: 1.2021/2/27 created by miss
 */
public class DisPatcherServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.handleController(req, resp, RequestMethod.GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.handleController(req, resp, RequestMethod.POST);
    }

    private void handleController(HttpServletRequest req, HttpServletResponse resp, RequestMethod requestMethod) throws ServletException, IOException {
        String url = req.getRequestURI().replace(req.getContextPath(), "").replaceAll("/+", "/");
        try {
            if (HandlerManager.mappingHandlerMap.get(url) != null) {
                MappingHandler handler = HandlerManager.mappingHandlerMap.get(url);
                if(handler.getSupportMethod().contains(requestMethod)) {
                    handler.handle(req, resp);
                }else {
                    resp.setStatus(405);
                    resp.getWriter().write("405! Miss's Method Not Allowed");
                }
            }else {
                resp.setStatus(400);
                resp.getWriter().write("400! Miss's Not Found");
            }
        }catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("500! Miss's Server Exception");
        }
    }
}
