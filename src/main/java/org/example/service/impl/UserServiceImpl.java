package org.example.service.impl;

import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.util.UsernameAndPasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsernameAndPasswordGenerator generator;

    @Override
    public User create(UserDTO userDTO) {
        User user = new User(userDTO.getFirstName(), userDTO.getLastName());
        user.setUsername(generator.generateUsername(user));
        user.setPassword(generator.generateRandomPassword());
        user.setActive(true);
        logger.debug("Creating User: {} {}", user.getFirstName(), user.getLastName());

        User save = userRepository.save(user);

        logger.info("User created: {} {}", user.getId(), user.getUsername());

        return save;
    }

    @Override
    public User edit(Integer id,UserDTO userDTO) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            User user = byId.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setUsername(generator.generateUsername(user));
            logger.debug("Updating User: {} {}", user.getFirstName(), user.getLastName());

            User save = userRepository.save(user);

            logger.info("User updated: {} {}", user.getId(), user.getUsername());
            return save;
        }
        return null;
    }

    @Override
    public boolean checkingUsernameAndPassword(String username, String password) {
        return userRepository.existsUserByUsernameAndPassword(username, password);
    }

    @Override
    public boolean changePassword(String username, String password, String newPassword) {
        boolean b = checkingUsernameAndPassword(username, password);
        if (!b) return false;

        User byUsername = userRepository.findByUsername(username);
        if (byUsername.getPassword().equals(password)) {
            byUsername.setPassword(newPassword);
        } else {
            return false;
        }

        logger.debug("Updating Password: {}{}", byUsername.getUsername(), byUsername.getPassword());
        userRepository.save(byUsername);
        logger.info("Updated Password: {}{}", byUsername.getUsername(), byUsername.getPassword());
        return true;
    }
}
