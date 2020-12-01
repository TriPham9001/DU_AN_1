package com.example.duan1.Model;

public class Cart {
    private String name;
    private Double price;
    private String images;

    public Cart() {
    }

    public Cart(String name, Double price, String images) {
        this.name = name;
        this.price = price;
        this.images = images;
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
}
