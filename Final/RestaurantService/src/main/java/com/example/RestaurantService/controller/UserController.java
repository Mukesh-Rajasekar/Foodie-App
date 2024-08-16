package com.example.RestaurantService.controller;

import com.example.RestaurantService.domain.Cuisine;
import com.example.RestaurantService.domain.Restaurant;
import com.example.RestaurantService.domain.User;
import com.example.RestaurantService.exception.CusineNotFound;
import com.example.RestaurantService.exception.RestaurantNotFoundException;
import com.example.RestaurantService.exception.UserAlreadyExists;
import com.example.RestaurantService.exception.UserNotFoundException;
import com.example.RestaurantService.service.IUserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
//@RequestMapping("api/v2")
public class UserController {

    private IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws UserAlreadyExists {
        User registeredUser = iUserService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PutMapping("api/v2/updateUsernameAndEmail/{userId}")
    public ResponseEntity<User> updateUsernameAndEmail(@PathVariable String userId,
                                                       @RequestBody User updatedUser) throws UserNotFoundException {
        User result = iUserService.updateUser(userId, updatedUser);
        if (result == null) {
            return ResponseEntity.badRequest().body(null); // Return 400 if update fails
        }
        return ResponseEntity.ok(result); // Return 200 OK with updated user
    }

    @DeleteMapping("/api/v2/deleterestaurant/{restaurantId}")
    public ResponseEntity<?> deleteRestaurantFromFavouriteRestaurantList(@PathVariable String restaurantId, HttpServletRequest request)
            throws UserNotFoundException, RestaurantNotFoundException
    {
        System.out.println("header" +request.getHeader("Authorization"));
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");
        System.out.println(userId);
        User user =  iUserService.deleteFavouriteRestaurant(userId,restaurantId);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/api/v2/deletecuisine/{cuisineId}")
    public ResponseEntity<?> deleteCuisineFromFavouriteCuisineList(@PathVariable Long cuisineId, HttpServletRequest request)
            throws UserNotFoundException, CusineNotFound
    {
        System.out.println("header" +request.getHeader("Authorization"));
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = (String) claims.get("userId");
        System.out.println(userId);
        User user =  iUserService.deleteFavouriteCuisine(userId,cuisineId);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    @PostMapping("/api/v2/{userId}/favorite-cuisine")
    public ResponseEntity<User> addFavoriteCuisine(
            @PathVariable String userId,
            @RequestBody Cuisine cuisine
    ) throws UserNotFoundException {

        User updatedUser = iUserService.addFavoriteCuisine(userId, cuisine);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/api/v2/{userId}/favorite-restaurant")
    public ResponseEntity<User> addFavoriteRestaurant(
            @PathVariable String userId,
            @RequestBody Restaurant restaurant
    ) throws UserNotFoundException {

        User updatedUser = iUserService.addFavoriteRestaurant(userId, restaurant);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/api/v2/display-all-fav-restaurant/{userId}")
    public ResponseEntity displayAllFavRestaurant(@PathVariable String userId) throws UserNotFoundException {
        List<Restaurant> favRest = iUserService.favRestaurants(userId);
        return new ResponseEntity(favRest,HttpStatus.OK);
    }



}
