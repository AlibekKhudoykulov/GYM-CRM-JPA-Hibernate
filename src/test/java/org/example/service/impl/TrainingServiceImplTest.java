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
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingServiceImplTest {

    @Mock
    private TrainingRepository trainingRepository;
    @Mock
    private TraineeRepository traineeRepository;
    @Mock
    private TrainerRepository trainerRepository;
    @Mock
    private TrainingTypeRepository trainingTypeRepository;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    Trainee trainee;
    Trainer trainer;
    TrainingType trainingType;
    TrainingDTO trainingDTO;

    @BeforeEach
    void setUp() {
        trainee = new Trainee();
        trainer = new Trainer();
        trainingType = new TrainingType();
        trainingDTO = new TrainingDTO();
        trainingDTO.setTraineeId(1);
        trainingDTO.setTrainerId(1);
        trainingDTO.setTrainingTypeId(1);
        trainingDTO.setTrainingName("Training 1");
        trainingDTO.setTrainingDate(new Date());
        trainingDTO.setTrainingDuration(2);
    }

    @Test
    public void create() throws Exception {
        when(traineeRepository.findById(anyInt())).thenReturn(Optional.of(trainee));
        when(trainerRepository.findById(anyInt())).thenReturn(Optional.of(trainer));
        when(trainingTypeRepository.findById(anyInt())).thenReturn(Optional.of(trainingType));
        when(trainingRepository.save(any(Training.class))).thenAnswer(i -> i.getArguments()[0]);

        trainingService.create(trainingDTO);

        verify(traineeRepository, times(1)).findById(anyInt());
        verify(trainerRepository, times(1)).findById(anyInt());
        verify(trainingTypeRepository, times(1)).findById(anyInt());
        verify(trainingRepository, times(1)).save(any(Training.class));
    }

    @Test
    public void getTraineeTrainings() {
        String username = "username";
        Training training = new Training();
        when(traineeRepository.getTraineeByUser_Username(username)).thenReturn(trainee);
        when(trainingRepository.getTrainingByTraineeId(trainee.getId())).thenReturn(Arrays.asList(training));

        List<Training> result = trainingService.getTraineeTrainings(username);

        verify(traineeRepository, times(1)).getTraineeByUser_Username(username);
        verify(trainingRepository, times(1)).getTrainingByTraineeId(trainee.getId());
        assertThat(result, hasSize(1));
    }

    @Test
    public void getTrainerTrainings() {
        String username = "username";
        Training training = new Training();
        when(trainerRepository.getTrainerByUser_Username(username)).thenReturn(trainer);
        when(trainingRepository.getTrainingByTrainerId(trainer.getId())).thenReturn(Arrays.asList(training));

        List<Training> result = trainingService.getTrainerTrainings(username);

        verify(trainerRepository, times(1)).getTrainerByUser_Username(username);
        verify(trainingRepository, times(1)).getTrainingByTrainerId(trainer.getId());
        assertThat(result, hasSize(1));
    }
}