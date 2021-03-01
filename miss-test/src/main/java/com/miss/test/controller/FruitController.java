package com.miss.test.controller;

import com.miss.core.annotation.Autowired;
import com.miss.core.annotation.Controller;
import com.miss.test.service.FruitService;
import com.miss.web.annotation.RequestMapping;
import com.miss.web.annotation.RequestParam;

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
}
