package dev.abdullahhassan.movie_trackr.controller;

import dev.abdullahhassan.movie_trackr.dto.FavouriteDTO;
import dev.abdullahhassan.movie_trackr.dto.ResponseDTO;
import dev.abdullahhassan.movie_trackr.dto.UserDTO;
import dev.abdullahhassan.movie_trackr.entity.UserEntity;
import dev.abdullahhassan.movie_trackr.service.FavouritesService;
import dev.abdullahhassan.movie_trackr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FavouritesService favouritesService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUpUser(@RequestBody UserDTO user){
        try{
            userService.saveUser(user);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.OK)
                            .message("User registered successfully.")
                            .data(null)
                            .build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("Failed to register user.")
                            .data(null)
                            .build());
        }
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") int userId){
        try{
            UserEntity user = userService.getUser(userId);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.OK)
                            .message("User found.")
                            .data(user)
                            .build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("Failed to get user: "+e.getMessage())
                            .data(null)
                            .build());
        }
    }

    @PostMapping("/favourite")
    public ResponseEntity<?> addFavourite(@RequestBody FavouriteDTO favourite){
        try{
            favouritesService.addFavourite(
                    favourite.getUserId(),
                    favourite.getTitle()
            );

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.OK)
                            .message("Added to favourites.")
                            .data(null)
                            .build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("Failed to add movie in favourites: "+e.getMessage())
                            .data(null)
                            .build());
        }
    }

    @GetMapping("/favourite/{userId}")
    public ResponseEntity<?> getFavourites(@PathVariable("userId") int userId){
        try{
           List<String> favourites = favouritesService.getFavourites(userId);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.OK)
                            .message("Fetched all favourites.")
                            .data(favourites)
                            .build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("Failed to get favourites: "+e.getMessage())
                            .data(null)
                            .build());
        }
    }

    @GetMapping("/favourite/{userId}/search")
    public ResponseEntity<?> getFavourites(
            @PathVariable("userId") int userId,
            @RequestParam(value = "Query") String query
    ){
        try{
            List<String> favourites = favouritesService.searchFavourites(userId,query);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.OK)
                            .message("Fetched all favourites matching query.")
                            .data(favourites)
                            .build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("Failed to get favourites: "+e.getMessage())
                            .data(null)
                            .build());
        }
    }

    @DeleteMapping("/favourite/{userId}/{title}")
    public ResponseEntity<?> deleteFavourite(
            @PathVariable("userId") int userId,
            @PathVariable("title") String title
    ){
        try{
            favouritesService.removeFavorite(userId,title);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.OK)
                            .message("Movie deleted from favourites.")
                            .data(null)
                            .build());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("Failed to delete favourites: "+e.getMessage())
                            .data(null)
                            .build());
        }
    }
}
