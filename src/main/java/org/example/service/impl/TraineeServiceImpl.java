package org.example.service.impl;

import org.example.dto.TraineeDTO;
import org.example.dto.UserDTO;
import org.example.entity.Trainee;
import org.example.entity.User;
import org.example.repository.TraineeRepository;
import org.example.repository.UserRepository;
import org.example.service.TraineeService;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TraineeServiceImpl implements TraineeService {
    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String create(TraineeDTO traineeDTO) {

        User user = userService.create(
                new UserDTO(
                        traineeDTO.getFirstName(),
                        traineeDTO.getLastName()));

        Trainee trainee = new Trainee(
                user,
                traineeDTO.getDateOfBirth(),
                traineeDTO.getAddress()
        );
        logger.info("Creating Trainee: {}", trainee);

        Trainee save = traineeRepository.save(trainee);

        logger.info("Created Trainee: {} {}", save.getId(), trainee.getUser().getUsername());
        return save.getUser().getUsername() + " " + save.getUser().getPassword();
    }

    @Override
    public Trainee getByUsername(String username) {
        return traineeRepository.getTraineeByUser_Username(username);
    }

    @Override
    public boolean changePassword(String username, String password, String newPassword) {
        return userService.changePassword(username, password, newPassword);
    }

    @Override
    public boolean update(Integer id, String username, String password, TraineeDTO traineeDTO) throws Exception {
        Trainee traineeById = traineeRepository.findById(id)
                .orElseThrow(() -> new Exception("Not Found"));

        if (traineeById.getUser().getUsername().equals(username) && traineeById.getUser().getPassword().equals(password)) {
            User user = traineeById.getUser();
            user.setFirstName(traineeDTO.getFirstName());
            user.setLastName(traineeDTO.getLastName());
            User edit = userService.edit(user.getId(), new UserDTO(traineeDTO.getFirstName(), traineeDTO.getLastName()));
            traineeById.setUser(edit);
            traineeById.setAddress(traineeDTO.getAddress());
            traineeById.setDateOfBirth(traineeDTO.getDateOfBirth());

            logger.info("Updating Trainee: {}", traineeById.getId());

            traineeRepository.save(traineeById);

            logger.info("Updated Trainee: {}", traineeById.getId());

            return true;

        }
        logger.info("Password is incorrect");
        return false;
    }

    @Transactional
    @Override
    public boolean activateOrDeActivate(String username, String password, boolean isActive) {
        Trainee trainee = traineeRepository.getTraineeByUser_Username(username);

        logger.info("Getting trainee for de/activation:{}",trainee);

        if (trainee != null && trainee.getUser().getPassword().equals(password)) {
            trainee.getUser().setActive(isActive);
            userRepository.save(trainee.getUser());
            logger.info("Updated trainee:{}", trainee);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void delete(Integer id, String username, String password) {
        traineeRepository.findById(id)
                .ifPresent(trainee -> {
                    if (trainee.getUser().getUsername().equals(username) && trainee.getUser().getPassword().equals(password)) {
                        traineeRepository.deleteById(id);
                        userRepository.deleteById(trainee.getUser().getId()); // Assumes you have a UserRepository
                    }
                });
    }
}
