package com.example.duan1.Model;

import java.io.Serializable;

public class ProductDrink implements Serializable {
    private String nameDrink;
    private String codeDrink;
    private Double priceDrink;
    private String describeDrink;
    private int imagesDrink;

    public ProductDrink() {
    }



    public ProductDrink(String nameDrink, String codeDrink, Double priceDrink, String describeDrink) {
        this.nameDrink = nameDrink;
        this.codeDrink = codeDrink;
        this.priceDrink = priceDrink;
        this.describeDrink = describeDrink;
    }

    public ProductDrink(int imagesDrink, String nameDrink) {
        this.nameDrink = nameDrink;
        this.imagesDrink = imagesDrink;
    }

    public ProductDrink(String nameDrink, String codeDrink, Double priceDrink, String describeDrink, int imagesDrink) {
        this.nameDrink = nameDrink;
        this.codeDrink = codeDrink;
        this.priceDrink = priceDrink;
        this.describeDrink = describeDrink;
        this.imagesDrink = imagesDrink;
    }

    public String getNameDrink() {
        return nameDrink;
    }

    public void setNameDrink(String nameDrink) {
        this.nameDrink = nameDrink;
    }

    public String getCodeDrink() {
        return codeDrink;
    }

    public void setCodeDrink(String codeDrink) {
        this.codeDrink = codeDrink;
    }

    public Double getPriceDrink() {
        return priceDrink;
    }

    public void setPriceDrink(Double priceDrink) {
        this.priceDrink = priceDrink;
    }

    public String getDescribeDrink() {
        return describeDrink;
    }

    public void setDescribeDrink(String describeDrink) {
        this.describeDrink = describeDrink;
    }

    public int getImagesDrink() {
        return imagesDrink;
    }

    public void setImagesDrink(int imagesFod) {
        this.imagesDrink = imagesFod;
    }
}