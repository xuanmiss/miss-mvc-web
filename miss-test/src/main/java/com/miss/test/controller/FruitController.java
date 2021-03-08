package com.miss.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miss.core.annotation.Autowired;
import com.miss.core.annotation.Controller;
import com.miss.test.service.FruitService;
import com.miss.test.utils.HttpClientUtil;
import com.miss.web.annotation.RequestBody;
import com.miss.web.annotation.RequestMapping;
import com.miss.web.annotation.RequestParam;
import com.miss.web.annotation.ResponseBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test.controller
 * @author: miss
 * @since: 2021/3/1 12:29
 * @history: 1.2021/3/1 created by miss
 */
@RequestMapping("/fruit")
@Controller
public class FruitController {

    @Autowired
    public FruitService fruitService;

    @RequestMapping("/detail")
    public String detailFruit(@RequestParam("name") String name,
                              @RequestParam("color") String color) {
        return fruitService.detailFruit(name, color);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> addFruit(@RequestBody Map<String, Object> fruit) {
        return fruitService.addFruit(fruit);
    }
    
    @RequestMapping("/client")
    @ResponseBody
    public Map clientFruit(@RequestBody Map<String, Object> fruit) throws IOException {
       return HttpClientUtil.post("http://localhost:8080/miss/fruit/add", fruit, Map.class);
    }
}
