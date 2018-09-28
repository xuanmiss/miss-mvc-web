package com.miss.mymvc.core.controller;

import com.miss.mymvc.annotation.MissController;
import com.miss.mymvc.annotation.MissRequestMapping;
import com.miss.mymvc.annotation.MissRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created By peishengzhang
 * <p>
 * At 2018/9/27
 */
@MissController
@MissRequestMapping(value = "miss")
public class TestController {

    @MissRequestMapping("/test")
    public void test(HttpServletRequest request,
                     HttpServletResponse response,
                     @MissRequestParam("param")String param,
                     @MissRequestParam("param2")String param2){
        System.out.println(param+"***"+param2);
        try{
            response.getWriter().write("miss test method success params:"+param+"***"+param2);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @MissRequestMapping("/test2")
    public void test2(HttpServletRequest request,
                      HttpServletResponse response){
        try{
            response.getWriter().println("miss test2 method success");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
