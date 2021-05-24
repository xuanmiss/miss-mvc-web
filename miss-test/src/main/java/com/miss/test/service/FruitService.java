package com.miss.test.service;

import com.miss.core.annotation.Component;
import com.miss.test.entity.Fruit;
import com.miss.test.utils.CusStringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test.service
 * @author: miss
 * @since: 2021/3/1 12:31
 * @history: 1.2021/3/1 created by miss
 */
@Component
public class FruitService {

    private final Map<String, Fruit> fruitMaps = new HashMap<>();

    public Fruit detailFruit(String id) {
        return fruitMaps.get(id);
    }

    public Fruit addFruit(Fruit fruit) {
        if (fruit.getId() == null || fruit.getId().isEmpty()) {
            fruit.setId(CusStringUtils.generateId());
        }
        fruitMaps.put(fruit.getId(), fruit);
        return fruit;
    }

    public List<Fruit> listFruit() {
        return new ArrayList<>(fruitMaps.values());
    }
}
