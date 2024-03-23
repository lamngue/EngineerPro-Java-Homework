package com.example.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "film")
public class Film {
    @Builder.Default
    private Long id = 0L;
    private String name;
    private List<Double> ratings;
}
