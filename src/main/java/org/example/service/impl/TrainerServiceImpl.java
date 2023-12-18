package org.example.service.impl;

import org.example.dto.TrainerDTO;
import org.example.dto.UserDTO;
import org.example.entity.Trainer;
import org.example.entity.TrainingType;
import org.example.entity.User;
import org.example.repository.TrainerRepository;
import org.example.repository.TrainingTypeRepository;
import org.example.service.TrainerService;
import org.example.service.UserService;
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
    private UserService userService;

    @Autowired
    private TrainingTypeRepository trainingTypeRepository;

    @Override
    public String create(TrainerDTO trainerDTO) {
        User user = userService.create(
                new UserDTO(
                        trainerDTO.getFirstName(),
                        trainerDTO.getLastName()));

        TrainingType trainingType = null;
        Optional<TrainingType> byId = trainingTypeRepository.findById(trainerDTO.getTrainingTypeId());
        if(byId.isPresent()){
           trainingType = byId.get();
        }
        Trainer trainer = new Trainer(
                user,
                trainingType
        );
        logger.info("Creating Trainer: {} {}", trainer.getUser().getFirstName(), trainer.getUser().getLastName());

        Trainer saveTrainer = trainerRepository.save(trainer);

        logger.info("Trainer created: {}", saveTrainer.getId());

        return saveTrainer.getUser().getUsername()+ " " + saveTrainer.getUser().getPassword();
    }
}
