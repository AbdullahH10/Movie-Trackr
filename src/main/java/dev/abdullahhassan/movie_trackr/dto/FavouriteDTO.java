package dev.abdullahhassan.movie_trackr.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteDTO {
    int userId;
    String title;
}
