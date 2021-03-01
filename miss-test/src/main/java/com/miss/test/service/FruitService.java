package com.miss.test.service;

import com.miss.core.annotation.Component;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test.service
 * @author: miss
 * @since: 2021/3/1 12:31
 * @history: 1.2021/3/1 created by miss
 */
@Component
public class FruitService {

    public String detailFruit(String name, String color) {
        return "Fruit is: " + name + " " + color;
    }
}
