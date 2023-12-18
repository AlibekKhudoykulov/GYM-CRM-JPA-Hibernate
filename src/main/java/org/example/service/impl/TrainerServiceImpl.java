package org.example.service.impl;

import org.example.dto.TrainerDTO;
import org.example.entity.Trainer;
import org.example.entity.TrainingType;
import org.example.entity.User;
import org.example.repository.TrainerRepository;
import org.example.repository.TrainingType;
import org.example.repository.TrainingTypeRepository;
import org.example.repository.UserRepository;
import org.example.service.TrainerService;
import org.example.util.UsernameAndPasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {
    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingTypeRepository trainingTypeRepository;

    @Autowired
    private UsernameAndPasswordGenerator generator;
    @Override
    public String create(TrainerDTO trainerDTO) {
        User user = new User(trainerDTO.getFirstName(), trainerDTO.getLastName());
        user.setUsername(generator.generateUsername(user));
        user.setPassword(generator.generateRandomPassword());
        user.setActive(true);
        User save = userRepository.save(user);

        logger.info("Username and Password created: {} {}",user.getUsername(),user.getPassword());

        TrainingType trainingType = null;
        Optional<TrainingType> byId = trainingTypeRepository.findById(trainerDTO.getTrainingTypeId());
        if(byId.isPresent()){
           trainingType = byId.get();
        }
        Trainer trainer = new Trainer(
                save,
                trainingType
        );
        logger.info("Creating Trainer: {} {}", trainer.getUser().getFirstName(), trainer.getUser().getLastName());

        Trainer saveTrainer = trainerRepository.save(trainer);

        logger.info("Trainer created: {}", saveTrainer.getId());

        return saveTrainer.getUser().getUsername()+ " " + saveTrainer.getUser().getPassword();
    }
}
