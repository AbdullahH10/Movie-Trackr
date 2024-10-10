package dev.abdullahhassan.movie_trackr.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Arrays;

@Service
public class PasswordEncoderService {
    public String encode(String rawPassword){
        try{
            StringBuilder hex = new StringBuilder();

            MessageDigest digest = MessageDigest.getInstance("SHA256");
            digest.update(rawPassword.getBytes());
            byte[] hash = digest.digest();
            for(byte b: hash){
                hex.append(String.format("%02X", b));
            }
            return hex.toString();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
