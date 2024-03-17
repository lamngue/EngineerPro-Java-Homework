package com.example.demo.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Builder.Default
    private Long id = 0L;
    private String name;
    @Builder.Default
    private List<Film> likedFilms = new ArrayList<>();
}

