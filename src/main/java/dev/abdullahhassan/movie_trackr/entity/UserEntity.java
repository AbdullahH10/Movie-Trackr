package dev.abdullahhassan.movie_trackr.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    String email;
    String password;
    List<String> favourites;
}
