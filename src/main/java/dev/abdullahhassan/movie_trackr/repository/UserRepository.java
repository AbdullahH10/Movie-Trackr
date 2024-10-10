package dev.abdullahhassan.movie_trackr.repository;

import dev.abdullahhassan.movie_trackr.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {
    private static List<UserEntity> users = new ArrayList<>();

    public void saveUser(UserEntity user){
        users.add(user);
    }

    //Assuming list index as user id
    public UserEntity getUser(int userId){
        if(userId<users.size()){
            return users.get(userId);
        }
        throw new RuntimeException("User does not exist.");
    }
}
