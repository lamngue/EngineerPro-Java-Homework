package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

interface UserInt {
    void addUser(User user);

    List<User> getAllUsers();
    User getUserById(Long userId);
    void likeFilm(User user, Film film);
    List<Film> getFavoriteFilms(User user);
    void removeLikedFilm(User user, Film film);
}
@Service
public class UserService implements UserInt {
    private final List<User> users = new ArrayList<>(); // Assuming you're storing users in a list

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }


    @Override
    public User getUserById(Long userId) {
        return users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }

    @Override
    public void likeFilm(User user, Film film) {
        user.getLikedFilms().add(film);
        film.getLikedBy().add(user);
    }

    @Override
    public List<Film> getFavoriteFilms(User user) {
        return user.getLikedFilms();
    }

    @Override
    public void removeLikedFilm(User user, Film film) {
        user.getLikedFilms().remove(film);
        film.getLikedBy().remove(user);
    }

    public boolean isUserExists(User user) {
        Optional<User> first = users.stream()
                .filter(u -> u.getName().equals(user.getName()))
                .findFirst();
        return first.isPresent();
    }
}
