package dev.abdullahhassan.movie_trackr.repository;

import dev.abdullahhassan.movie_trackr.entity.MovieEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MovieRepository {
    private static List<MovieEntity> movies = new ArrayList<>();

    public void addMovie(MovieEntity movie){
        movies.add(movie);
    }

    public List<MovieEntity> findAll(){
        if(!movies.isEmpty()){
            return movies;
        }
        throw new RuntimeException("No movie in database.");
    }

    public MovieEntity findMovieById(UUID movieId){
        if(!movies.isEmpty()){
            return movies.stream().filter(
                    (movie) -> movie.getMovieId().equals(movieId)
            ).toList().getFirst();
        }
        throw new RuntimeException("No movie in database.");
    }

    public List<MovieEntity> findMoviesByTitle(String title){
        if(!movies.isEmpty()){
            return movies.stream().filter(
                    (movie) -> movie.getTitle().equals(title)
            ).toList();
        }
        throw new RuntimeException("No movie in database.");
    }

    public List<MovieEntity> findMoviesByCast(String cast){
        if(!movies.isEmpty()){
            return movies.stream().filter(
                    (movie) -> movie.getCast().contains(cast)
            ).toList();
        }
        throw new RuntimeException("No movie in database.");
    }

    public List<MovieEntity> findMoviesByCategory(String category){
        if(!movies.isEmpty()){
            return movies.stream().filter(
                    (movie) -> movie.getCategory().equals(category)
            ).toList();
        }
        throw new RuntimeException("No movie in database.");
    }
}
