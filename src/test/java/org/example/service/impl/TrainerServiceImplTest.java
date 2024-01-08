package org.example.service.impl;

import org.example.dto.TrainerDTO;
import org.example.dto.UserDTO;
import org.example.entity.Trainer;
import org.example.entity.TrainingType;
import org.example.entity.User;
import org.example.repository.TrainerRepository;
import org.example.repository.TrainingTypeRepository;
import org.example.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.BeforeEach;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceImplTest {

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private UserService userService;

    @Mock
    private TrainingTypeRepository trainingTypeRepository;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    User user;
    TrainingType trainingType;
    TrainerDTO trainerDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("john");
        user.setPassword("123");
        user.setId(1);

        trainingType = new TrainingType();
        trainingType.setId(1);

        trainerDTO = new TrainerDTO();
        trainerDTO.setFirstName("John");
        trainerDTO.setLastName("Doe");
        trainerDTO.setTrainingTypeId(1);
    }

    @Test
    void create() {
        when(userService.create(any(UserDTO.class))).thenReturn(user);
        when(trainingTypeRepository.findById(anyInt())).thenReturn(Optional.of(trainingType));
        when(trainerRepository.save(any(Trainer.class))).thenAnswer(i -> i.getArguments()[0]);

        String expectedResult = "john 123";
        String actualResult = trainerService.create(trainerDTO);

        assertThat(actualResult, Matchers.equalTo(expectedResult));
        verify(userService, times(1)).create(any(UserDTO.class));
        verify(trainingTypeRepository, times(1)).findById(anyInt());
        verify(trainerRepository, times(1)).save(any(Trainer.class));
    }

    @Test
    void getByUsername() {
        when(trainerRepository.getTrainerByUser_Username(anyString())).thenReturn(new Trainer(user, trainingType));

        Trainer actualResult = trainerService.getByUsername("john");

        assertThat(actualResult.getUser().getUsername(), Matchers.equalTo("john"));
        verify(trainerRepository, times(1)).getTrainerByUser_Username(anyString());
    }

    @Test
    void changePassword() {
        when(userService.changePassword(anyString(), anyString(), anyString())).thenReturn(true);

        boolean actualResult = trainerService.changePassword("john", "123", "456");

        assertThat(actualResult, Matchers.equalTo(true));
        verify(userService, times(1)).changePassword(anyString(), anyString(), anyString());
    }

    @Test
    void update() throws Exception {
        TrainingType newTrainingType = new TrainingType();
        newTrainingType.setId(2);

        Trainer trainer = new Trainer(user, trainingType);
        when(trainerRepository.findById(anyInt())).thenReturn(Optional.of(trainer));
        when(userService.edit(anyInt(), any(UserDTO.class))).thenReturn(user);
        when(trainingTypeRepository.findById(anyInt())).thenReturn(Optional.of(newTrainingType));

        boolean actualResult = trainerService.update(1, "john", "123", trainerDTO);

        assertThat(actualResult, Matchers.equalTo(true));
        verify(trainerRepository, times(1)).findById(anyInt());
        verify(userService, times(1)).edit(anyInt(), any(UserDTO.class));
        verify(trainingTypeRepository, times(1)).findById(anyInt());
    }

    @Test
    void activateOrDeActivate() {
        Trainer trainer = new Trainer(user, trainingType);
        when(trainerRepository.getTrainerByUser_Username(anyString())).thenReturn(trainer);

        boolean actualResult = trainerService.activateOrDeActivate("john", "123", true);

        assertThat(actualResult, Matchers.equalTo(true));
        verify(trainerRepository, times(1)).getTrainerByUser_Username(anyString());
    }}