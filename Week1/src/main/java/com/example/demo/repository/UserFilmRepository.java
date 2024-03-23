package com.example.demo.repository;

import com.example.demo.model.UserFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFilmRepository extends JpaRepository<UserFilm, Long> {
    List<UserFilm> findByUserId(Long userId);
    // You can add custom query methods here if needed
}

