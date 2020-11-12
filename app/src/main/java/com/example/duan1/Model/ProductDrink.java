package com.example.duan1.Model;

import android.graphics.drawable.Drawable;

public class ProductDrink {

    private String nameDrink;
    private String codeDrink;
    private Double priceDrink;
    private String describeDrink;
    private byte[] imagesDrink;

    public ProductDrink() {
    }

    public ProductDrink(String nameDrink, String codeDrink, Double priceDrink, String describeDrink, byte[] imagesDrink) {
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

    public byte[] getImagesDrink() {
        return imagesDrink;
    }

    public void setImagesDrink(byte[] imagesDrink) {
        this.imagesDrink = imagesDrink;
    }
}
