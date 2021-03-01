package com.miss.mymvc.core.controller;

import com.miss.mymvc.annotation.MissAutowired;
import com.miss.mymvc.annotation.MissController;
import com.miss.mymvc.annotation.MissRequestMapping;
import com.miss.mymvc.annotation.MissRequestParam;
import com.miss.mymvc.core.service.TestService;

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

    @MissAutowired
    private TestService testService;

    @MissRequestMapping("/test")
    public String test(
                     @MissRequestParam("param")String param,
                     @MissRequestParam("param2")String param2)throws IOException{

        System.out.println(param+"***"+param2);
        System.out.println(testService);
        String res = testService.testParam(param,param2);
        return res;
    }

    @MissRequestMapping("/test2")
    public String test2(
            @MissRequestParam("param") String param,
            @MissRequestParam String param2)throws IOException{

        System.out.println(param+"***"+param2);
        System.out.println(testService);
        String res = testService.testParam(param,param2);
        return res;
    }


}
