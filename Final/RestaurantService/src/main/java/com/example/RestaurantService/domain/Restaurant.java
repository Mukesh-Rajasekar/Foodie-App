package com.example.RestaurantService.domain;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Restaurant {
    @Id
    private String restaurantId;
    private String name;
    private String location;
    private List<Cuisine> cuisine;
    private List<FoodItems> foodItems;


    public Restaurant(){

    }

    public Restaurant(String restaurantId,FoodItems foodItems, String name, String location, Cuisine cuisine) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.cuisine = (List<Cuisine>) cuisine;
        this.foodItems= (List<FoodItems>) foodItems;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Cuisine getCuisine() {
        return (Cuisine) cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = (List<Cuisine>) cuisine;
    }

    public List<FoodItems> getFoodItems() {
        return foodItems;
    }
    public void setFoodItems(List<FoodItems> foodItems) {
        this.foodItems = foodItems;
    }


}
