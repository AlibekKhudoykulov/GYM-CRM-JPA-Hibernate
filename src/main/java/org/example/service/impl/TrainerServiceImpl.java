package org.example.service.impl;

import org.example.dto.TraineeDTO;
import org.example.dto.TrainerDTO;
import org.example.dto.UserDTO;
import org.example.entity.Trainee;
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
        logger.debug("Creating Trainer: {} {}", trainer.getUser().getFirstName(), trainer.getUser().getLastName());

        Trainer saveTrainer = trainerRepository.save(trainer);

        logger.info("Trainer created: {}", saveTrainer.getId());

        return saveTrainer.getUser().getUsername()+ " " + saveTrainer.getUser().getPassword();
    }

    @Override
    public Trainer getByUsername(String username) {
        return trainerRepository.getTrainerByUser_Username(username);
    }

    @Override
    public boolean changePassword(String username, String password, String newPassword) {
        return userService.changePassword(username,password,newPassword);
    }

    @Override
    public boolean update(Integer id, String username, String password, TrainerDTO trainerDTO) throws Exception {
        Trainer trainerById = trainerRepository.findById(id)
                .orElseThrow(() -> new Exception("Not Found"));

        if (trainerById.getUser().getUsername().equals(username) && trainerById.getUser().getPassword().equals(password)) {
            User user = trainerById.getUser();
            user.setFirstName(trainerDTO.getFirstName());
            user.setLastName(trainerDTO.getLastName());
            User edit = userService.edit(user.getId(), new UserDTO(trainerDTO.getFirstName(), trainerDTO.getLastName()));
            trainerById.setUser(edit);

            TrainingType trainingType = null;
            Optional<TrainingType> byId = trainingTypeRepository.findById(trainerDTO.getTrainingTypeId());
            if(byId.isPresent()){
                trainingType = byId.get();
            }
            trainerById.setTrainingType(trainingType);

            logger.debug("Updating Trainer: {}", trainerById.getId());

            trainerRepository.save(trainerById);

            logger.info("Updated Trainer: {}", trainerById.getId());
            return true;

        }
        logger.info("Password is incorrect");
        return false;
    }

    @Override
    public boolean activateOrDeActivate(String username, String password, boolean isActive) {
        Trainer trainer = trainerRepository.getTrainerByUser_Username(username);
        if (trainer != null && trainer.getUser().getPassword().equals(password)) {
            trainerRepository.updateTrainerActivationStatus(username, isActive);
            return true;
        }
        return false;
    }
}
