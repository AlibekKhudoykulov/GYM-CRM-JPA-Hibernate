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

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsernameAndPasswordGenerator generator;
    @Override
    public User create(UserDTO userDTO) {
        User user = new User(userDTO.getFirstName(),userDTO.getLastName());
        user.setUsername(generator.generateUsername(user));
        user.setPassword(generator.generateRandomPassword());
        user.setActive(true);
        logger.info("Creating User: {} {}", user.getFirstName(), user.getLastName());

        User save = userRepository.save(user);

        logger.info("User created: {} {}",user.getId(),user.getUsername());

        return save;
    }
}
