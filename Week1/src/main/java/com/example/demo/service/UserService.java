package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.model.User;
import com.example.demo.model.UserFilm;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.UserFilmRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

interface UserInt {
    void addUser(User user);

    List<User> getAllUsers();
    User getUserById(Long userId);
    void likeFilm(Long userId, Long filmId);
    List<Film> getFavoriteFilms(Long userId);
    void removeLikedFilm(Long userId, Long filmId);
}
@Service
public class UserService implements UserInt {
    private final UserRepository userRepository;
    private final UserFilmRepository userFilmRepository;
    private final FilmRepository filmRepository;
    private final Map<Long, User> userCache = new ConcurrentHashMap<>(); // ConcurrentHashMap for caching users


    public UserService(UserRepository userRepository, UserFilmRepository userFilmRepository, FilmRepository filmRepository) {
        this.userRepository = userRepository;
        this.userFilmRepository = userFilmRepository;
        this.filmRepository = filmRepository;
        loadUsersIntoCache();
    }

    private void loadUsersIntoCache() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> userCache.put(user.getId(), user));
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
        userCache.putIfAbsent(user.getId(), user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User getUserById(Long userId) {
        return userCache.computeIfAbsent(userId, id -> userRepository.findById(id).orElse(null));
    }

    @Override
    public void likeFilm(Long userId,  Long filmId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Film not found with id: " + filmId));
        UserFilm userFilm = UserFilm.builder().user(user).film(film).build();
        userFilmRepository.save(userFilm);
    }

    @Override
    public List<Film> getFavoriteFilms(Long userId) {
        List<UserFilm> userFilms = userFilmRepository.findByUserId(userId);
        List<Long> filmIds = userFilms.stream().map(userFilm -> userFilm.getFilm().getId()).collect(Collectors.toList());
        return filmRepository.findAllById(filmIds);
    }

    @Override
    public void removeLikedFilm(Long userId, Long filmId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new NotFoundException("Film not found with id: " + filmId));
        UserFilm userFilm = UserFilm.builder().user(user).film(film).build();
        userFilmRepository.delete(userFilm);
    }

    public boolean isUserExists(String name) {
        Optional<User> userOptional = userRepository.findByName(name);
        return userOptional.isPresent();
    }
}
