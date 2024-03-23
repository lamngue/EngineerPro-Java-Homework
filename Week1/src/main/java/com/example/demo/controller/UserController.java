package com.example.demo.controller;

import com.example.demo.model.Film;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/add_user")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        List<User> users = userService.getAllUsers();
        if (userService.isUserExists(user.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        } else {
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
        }
    }

    @PostMapping("/{userId}/like")
    public void likeFilm(@PathVariable Long userId, @RequestBody Long filmId) {
        User user = userService.getUserById(userId);
        userService.likeFilm(userId, filmId);
    }

    @GetMapping("/{userId}/favorites")
    public List<Film> getFavoriteFilms(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return userService.getFavoriteFilms(userId);
    }

    @DeleteMapping("/{userId}/favorites")
    public void removeLikedFilm(@PathVariable Long userId, @RequestBody Long filmId) {
        User user = userService.getUserById(userId);
        userService.removeLikedFilm(userId, filmId);
    }
}

