package com.miss.test.entity;

/**
 * @project: miss-mvc-web
 * @package: com.miss.test.entity
 * @author: miss
 * @date: 2021/5/24 12:02
 * @since:
 * @history: 1.2021/5/24 created by miss
 */
public class Fruit {

    private String id;

    private String fruitName;

    private String fruitColor;

    private Double fruitPrice;

    public Fruit() {
    }

    public Fruit(String id, String fruitName, String fruitColor, Double fruitPrice) {
        this.id = id;
        this.fruitName = fruitName;
        this.fruitColor = fruitColor;
        this.fruitPrice = fruitPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getFruitColor() {
        return fruitColor;
    }

    public void setFruitColor(String fruitColor) {
        this.fruitColor = fruitColor;
    }

    public Double getFruitPrice() {
        return fruitPrice;
    }

    public void setFruitPrice(Double fruitPrice) {
        this.fruitPrice = fruitPrice;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id='" + id + '\'' +
                ", fruitName='" + fruitName + '\'' +
                ", fruitColor='" + fruitColor + '\'' +
                ", fruitPrice=" + fruitPrice +
                '}';
    }
}
