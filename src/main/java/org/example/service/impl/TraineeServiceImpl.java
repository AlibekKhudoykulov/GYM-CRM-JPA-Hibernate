package org.example.service.impl;

import org.example.dto.TraineeDTO;
import org.example.dto.UserDTO;
import org.example.entity.Trainee;
import org.example.entity.User;
import org.example.repository.TraineeRepository;
import org.example.repository.UserRepository;
import org.example.service.TraineeService;
import org.example.service.UserService;
import org.example.util.UsernameAndPasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImpl implements TraineeService {
    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private UserService userService;
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
        logger.info("Creating Trainee: {}",trainee);

        Trainee save = traineeRepository.save(trainee);

        logger.info("Created Trainee: {} {}", save.getId(),trainee.getUser().getUsername());
        return save.getUser().getUsername() + " " + save.getUser().getPassword();
    }
}
