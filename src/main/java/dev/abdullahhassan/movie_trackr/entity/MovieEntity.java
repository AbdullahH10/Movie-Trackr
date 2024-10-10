package dev.abdullahhassan.movie_trackr.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {
    UUID movieId;
    String title;
    String cast;
    String category;
    LocalDate releaseDate;
    double budget;
}
