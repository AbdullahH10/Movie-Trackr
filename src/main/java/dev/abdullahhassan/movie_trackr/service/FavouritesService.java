package dev.abdullahhassan.movie_trackr.service;

import dev.abdullahhassan.movie_trackr.entity.UserEntity;
import dev.abdullahhassan.movie_trackr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouritesService {
    @Autowired
    private UserRepository userRepository;

    public void addFavourite(int userId,String title){
        UserEntity user = userRepository.getUser(userId);
        user.getFavourites().add(title);
    }

    public List<String> getFavourites(int userId){
        UserEntity user = userRepository.getUser(userId);
        return user.getFavourites();
    }

    public void removeFavorite(int userId,String title){
        UserEntity user = userRepository.getUser(userId);
         boolean isRemoved = user.getFavourites().remove(title);
         if(!isRemoved)
             throw new RuntimeException("Movie not in favorites");
    }

    public List<String> searchFavourites(int userId,String query){
        UserEntity user = userRepository.getUser(userId);
        return user.getFavourites().stream().filter(
                (title) -> title.contains(query)
        ).toList();
    }
}
