package org.example.service;

import org.example.dto.TrainingDTO;
import org.example.entity.Training;

import java.util.List;

public interface TrainingService {
    void create(TrainingDTO trainingDTO) throws Exception;
    List<Training> getTraineeTrainings(String username);
    List<Training> getTrainerTrainings(String username);
}
