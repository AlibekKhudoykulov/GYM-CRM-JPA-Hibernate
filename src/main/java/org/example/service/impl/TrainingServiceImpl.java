package org.example.service.impl;

import org.example.dto.TrainingDTO;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.entity.Training;
import org.example.entity.TrainingType;
import org.example.repository.TraineeRepository;
import org.example.repository.TrainerRepository;
import org.example.repository.TrainingRepository;
import org.example.repository.TrainingTypeRepository;
import org.example.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {
    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    @Autowired
    private TrainingRepository trainingRepository;
    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TrainingTypeRepository trainingTypeRepository;

    @Override
    public void create(TrainingDTO trainingDTO) throws Exception {
        Trainee trainee = traineeRepository.findById(trainingDTO.getTraineeId())
                .orElseThrow(() -> new Exception("Trainee not found"));

        Trainer trainer = trainerRepository.findById(trainingDTO.getTrainerId()).
                orElseThrow(() -> new Exception("Trainer not found"));

        TrainingType trainingType = trainingTypeRepository.findById(trainingDTO.getTrainingTypeId())
                .orElseThrow(() -> new Exception("Training type not found"));

        Training training = new Training(
                trainer,
                trainee,
                trainingDTO.getTrainingName(),
                trainingType,
                trainingDTO.getTrainingDate(),
                trainingDTO.getTrainingDuration()
        );
        logger.debug("Creating Traning:{}",training);
        Training save = trainingRepository.save(training);
        logger.info("Created Training:{}",save.getId());
    }

    @Override
    public List<Training> getTraineeTrainings(String username) {
        logger.debug("Finding trainee:{}",username);
        Trainee traineeByUserUsername = traineeRepository.getTraineeByUser_Username(username);
        logger.info("Found trainee:{}",traineeByUserUsername.getId())
        ;
        return trainingRepository.getTrainingByTraineeId(traineeByUserUsername.getId());
    }

    @Override
    public List<Training> getTrainerTrainings(String username) {
        logger.debug("Finding trainer:{}",username);
        Trainer trainerByUserUsername = trainerRepository.getTrainerByUser_Username(username);
        logger.info("Found trainer:{}",trainerByUserUsername.getId());

        return trainingRepository.getTrainingByTrainerId(trainerByUserUsername.getId());
    }

}
