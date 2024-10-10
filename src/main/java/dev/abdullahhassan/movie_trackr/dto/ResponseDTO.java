package dev.abdullahhassan.movie_trackr.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    HttpStatus status;
    String message;
    T data;
}
