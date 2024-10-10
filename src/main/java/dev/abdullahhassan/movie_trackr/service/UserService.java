package dev.abdullahhassan.movie_trackr.service;

import dev.abdullahhassan.movie_trackr.dto.UserDTO;
import dev.abdullahhassan.movie_trackr.entity.UserEntity;
import dev.abdullahhassan.movie_trackr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoderService passwordEncoderService;

    public void saveUser(UserDTO user){
        UserEntity userEntity = UserEntity.builder()
                .email(user.getEmail())
                .password(passwordEncoderService.encode(
                        user.getPassword())
                )
                .favourites(new ArrayList<>())
                .build();
        userRepository.saveUser(userEntity);
    }

    public UserEntity getUser(int userId){
        return userRepository.getUser(userId);
    }
}
