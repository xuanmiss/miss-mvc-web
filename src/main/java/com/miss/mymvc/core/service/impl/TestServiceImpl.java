package com.miss.mymvc.core.service.impl;

import com.miss.mymvc.annotation.MissService;
import com.miss.mymvc.core.service.TestService;

/**
 * Created By peishengzhang
 * <p>
 * At 2018/9/28
 */
@MissService
public class TestServiceImpl implements TestService {

    @Override
    public String testParam(String p, String q)
    {
        return p +"is the first "+q +"is the second";
    }
}
