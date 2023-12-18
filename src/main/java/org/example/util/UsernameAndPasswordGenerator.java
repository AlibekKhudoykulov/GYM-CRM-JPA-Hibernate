package org.example.util;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
public class UsernameAndPasswordGenerator {
    @Autowired
    private UserRepository userRepository;

    public String generateUsername(User user) {
        String baseUsername = user.getFirstName() + "." + user.getLastName();
        String username = baseUsername;
        int count = 1;

        // Check and append a serial number if the username exists
        while (userRepository.existsByUsername(username)) {
            username = baseUsername + count;
            count++;
        }
        return username;
    }
    public String generateRandomPassword() {
        // Logic to generate a random password
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random rnd = new Random();
        while (password.length() < 10) {
            int index = (int) (rnd.nextFloat() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
}
