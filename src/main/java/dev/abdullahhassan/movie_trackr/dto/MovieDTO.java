package dev.abdullahhassan.movie_trackr.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    String title;
    String cast;
    String category;
    LocalDate releaseDate;
    double budget;
}
