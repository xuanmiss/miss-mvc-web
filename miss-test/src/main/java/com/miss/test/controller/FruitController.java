package com.miss.test.controller;

import com.miss.core.annotation.Autowired;
import com.miss.core.annotation.Controller;
import com.miss.test.entity.Fruit;
import com.miss.test.service.FruitService;
import com.miss.test.utils.HttpClientUtil;
import com.miss.web.annotation.*;

import javax.xml.ws.ResponseWrapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Fruit detailFruit(@RequestParam("id") String id) {
        return fruitService.detailFruit(id);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Fruit addFruit(@RequestBody Fruit fruit) {
        return fruitService.addFruit(fruit);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Fruit> listFruit() {
        return fruitService.listFruit();
    }
    
    @RequestMapping("/client")
    @ResponseBody
    public Fruit clientFruit(@RequestBody Fruit fruit) throws IOException {
       return HttpClientUtil.post("http://localhost:8080/miss/fruit/add", fruit, Fruit.class);
    }
}
