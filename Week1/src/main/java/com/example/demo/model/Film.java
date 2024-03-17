package com.example.demo.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Film {
    @Builder.Default
    private Long id = 0L;
    private String name;
    private List<Double> ratings;
    @Builder.Default
    private List<User> likedBy = new ArrayList<>();
}
