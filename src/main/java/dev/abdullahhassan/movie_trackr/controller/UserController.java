package dev.abdullahhassan.movie_trackr.controller;

import dev.abdullahhassan.movie_trackr.dto.ResponseDTO;
import dev.abdullahhassan.movie_trackr.dto.UserDTO;
import dev.abdullahhassan.movie_trackr.entity.UserEntity;
import dev.abdullahhassan.movie_trackr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

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
}
