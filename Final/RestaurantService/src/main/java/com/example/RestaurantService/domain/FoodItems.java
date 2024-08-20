package com.example.RestaurantService.domain;

public class FoodItems {

    private String dishName;
    private double price;
    private float rating;
    private String dishDiscription;

    public FoodItems(String dishName, double price, float rating, String dishDiscription) {
        this.dishName = dishName;
        this.price = price;
        this.rating = rating;
        this.dishDiscription = dishDiscription;
    }

    public FoodItems(){

    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDishDiscription() {
        return dishDiscription;
    }

    public void setDishDiscription(String dishDiscription) {
        this.dishDiscription = dishDiscription;
    }
}
