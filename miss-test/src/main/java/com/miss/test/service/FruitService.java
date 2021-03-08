package com.miss.test.service;

import com.miss.core.annotation.Component;

import java.util.Map;
import java.util.UUID;

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

    public Map<String, Object> addFruit(Map<String, Object> fruit) {
        fruit.put("requestId", UUID.randomUUID().toString());
        return fruit;
    }
}
