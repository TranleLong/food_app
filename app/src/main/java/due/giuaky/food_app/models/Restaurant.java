package com.example.delingon.model;

public class Restaurant {
    private String name;
    private String cuisineType1;
    private String cuisineType2;
    private String deliveryTime;
    private String price;
    private float rating;
    private int imageResourceId;

    public Restaurant(String name, String cuisineType1, String cuisineType2,
                      String deliveryTime, String price, float rating, int imageResourceId) {
        this.name = name;
        this.cuisineType1 = cuisineType1;
        this.cuisineType2 = cuisineType2;
        this.deliveryTime = deliveryTime;
        this.price = price;
        this.rating = rating;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getCuisineType1() {
        return cuisineType1;
    }

    public String getCuisineType2() {
        return cuisineType2;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}