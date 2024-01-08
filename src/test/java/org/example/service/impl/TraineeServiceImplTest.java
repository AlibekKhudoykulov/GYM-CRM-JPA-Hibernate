package org.example.service.impl;

import org.example.dto.TraineeDTO;
import org.example.dto.UserDTO;
import org.example.entity.Trainee;
import org.example.entity.User;
import org.example.repository.TraineeRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TraineeServiceImplTest {

    @Mock
    private TraineeRepository traineeRepository;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TraineeServiceImpl traineeService;

    private Trainee trainee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        trainee = new Trainee(
                new User("John", "Doe", "johndoe", "password1", true),
                new Date(2020,11,12), "New York");
    }

    @Test
    void create() {
        when(userService.create(any(UserDTO.class))).thenReturn(trainee.getUser());
        when(traineeRepository.save(any(Trainee.class))).thenReturn(trainee);

        String result = traineeService.create(new TraineeDTO("John", "Doe", "New York", new Date(2020,11,12)));

        assertEquals("johndoe password1", result);
    }

    @Test
    void getByUsername() {
        when(traineeRepository.getTraineeByUser_Username(anyString())).thenReturn(trainee);

        Trainee result = traineeService.getByUsername("johndoe");

        assertEquals(trainee, result);
    }

    @Test
    void update() throws Exception {
        when(traineeRepository.findById(anyInt())).thenReturn(Optional.of(trainee));
        when(userService.edit(anyInt(), any(UserDTO.class))).thenReturn(trainee.getUser());

        boolean result = traineeService.update(1, "johndoe", "password1", new TraineeDTO("John", "Doe", "New York",new Date(2020,11,12)));

        assertEquals(true, result);
    }
    @Test
    void changePassword() {
        when(userService.changePassword(anyString(), anyString(), anyString())).thenReturn(true);

        boolean result = traineeService.changePassword("johndoe", "password1", "password2");

        assertEquals(true, result);
    }
    @Test
    void activateOrDeActivate() {
        when(traineeRepository.getTraineeByUser_Username(anyString())).thenReturn(trainee);
        when(userRepository.save(any(User.class))).thenReturn(trainee.getUser());

        boolean result = traineeService.activateOrDeActivate("johndoe", "password1", false);

        assertEquals(true, result);
    }

    @Test
    void delete() {
        User user = new User();
        user.setId(1);
        user.setUsername("johndoe");
        user.setPassword("password1");

        Trainee trainee = new Trainee(user, new Date(),"New York");

        when(traineeRepository.findById(anyInt())).thenReturn(Optional.of(trainee));

        assertDoesNotThrow(() -> traineeService.delete(1, user.getUsername(),user.getPassword()));
        verify(traineeRepository, times(1)).deleteById(anyInt());
        verify(userRepository, times(1)).deleteById(anyInt());
    }
}