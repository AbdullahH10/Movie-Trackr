package dev.abdullahhassan.movie_trackr.service;

import dev.abdullahhassan.movie_trackr.dto.MovieDTO;
import dev.abdullahhassan.movie_trackr.entity.MovieEntity;
import dev.abdullahhassan.movie_trackr.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public void addMovie(MovieDTO movie){
        MovieEntity movieEntity = MovieEntity.builder()
                .movieId(UUID.randomUUID())
                .title(movie.getTitle())
                .cast(movie.getCast())
                .category(movie.getCategory())
                .releaseDate(movie.getReleaseDate())
                .budget(movie.getBudget())
                .build();

        movieRepository.addMovie(movieEntity);
    }

    public List<MovieEntity> getMovies(){
        return movieRepository.findAll().stream().sorted(
                (movie1,movie2) -> Character.compare(
                        movie1.getTitle().charAt(0),
                        movie2.getTitle().charAt(0)
                )
        ).toList();
    }

    public MovieEntity getMovie(UUID movieId){
        return movieRepository.findMovieById(movieId);
    }

    public List<MovieEntity> getMovies(
            String title,
            String cast,
            String category
    ){
        List<MovieEntity> result = new ArrayList<>();

        if(title != null && !title.isBlank()){
            result.addAll(movieRepository.findMoviesByTitle(title));
        }
        if(cast != null && !cast.isBlank()){
            result.addAll(movieRepository.findMoviesByCast(cast));
        }
        if(category != null && !category.isBlank()){
            result.addAll(movieRepository.findMoviesByCategory(category));
        }

        if(!result.isEmpty())
            return result.stream()
                    .distinct()
                    .sorted(
                        (movie1,movie2) -> Character.compare(
                                movie1.getTitle().charAt(0),
                                movie2.getTitle().charAt(0)
                        )
                    ).toList();
        throw new RuntimeException("No movie found.");
    }
}
