package com.example.duan1.Model;

public class Favorite {

    private String name;
    private Double price;
    private String images;
    private String describe;

    public Favorite() {
    }

    public Favorite(String name, Double price, String images, String describe) {
        this.name = name;
        this.price = price;
        this.images = images;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
