package dev.abdullahhassan.movie_trackr.controller;

import dev.abdullahhassan.movie_trackr.dto.MovieDTO;
import dev.abdullahhassan.movie_trackr.dto.ResponseDTO;
import dev.abdullahhassan.movie_trackr.dto.UserDTO;
import dev.abdullahhassan.movie_trackr.entity.MovieEntity;
import dev.abdullahhassan.movie_trackr.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping()
    public ResponseEntity<?> addMovie(@RequestBody MovieDTO movie){
        try{
            movieService.addMovie(movie);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.OK)
                            .message("Movie added successfully.")
                            .data(null)
                            .build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("Failed to add movie: "+e.getMessage())
                            .data(null)
                            .build());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllMovies(){
        try{
            List<MovieEntity> movies = movieService.getMovies();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.OK)
                            .message(movies.size()+" movie(s) found.")
                            .data(movies)
                            .build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("Failed to find movie: "+e.getMessage())
                            .data(null)
                            .build());
        }
    }


    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovie(@PathVariable("movieId") UUID movieId){
        try{
            MovieEntity movie = movieService.getMovie(movieId);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.OK)
                            .message("Movie found.")
                            .data(movie)
                            .build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("Failed to find movie: "+e.getMessage())
                            .data(null)
                            .build());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMovies(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "cast",required = false) String cast,
            @RequestParam(value = "category",required = false) String category
    ){
        try{
            List<MovieEntity> movies = movieService.getMovies(
                    title,
                    cast,
                    category
            );

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.OK)
                            .message("Movies found.")
                            .data(movies)
                            .build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("Failed to search for movies: "+e.getMessage())
                            .data(null)
                            .build());
        }
    }

}
