package org.example.service.impl;

import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.util.UsernameAndPasswordGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UsernameAndPasswordGenerator generator;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void create() {
        UserDTO userDTO = new UserDTO("John", "Doe");
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        when(generator.generateUsername(any(User.class))).thenReturn("john");
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.create(userDTO);

        verify(generator, times(1)).generateUsername(any(User.class));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void edit() {
        UserDTO userDTO = new UserDTO("John", "Doe");
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.edit(1, userDTO);

        verify(userRepository, times(1)).findById(anyInt());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void checkingUsernameAndPassword() {
        when(userRepository.existsUserByUsernameAndPassword(anyString(), anyString())).thenReturn(true);

        userService.checkingUsernameAndPassword("john", "password");

        verify(userRepository, times(1)).existsUserByUsernameAndPassword(anyString(), anyString());
    }

    @Test
    void changePassword() {
        User user = new User();
        user.setUsername("john");
        user.setPassword("password");

        when(userRepository.existsUserByUsernameAndPassword(anyString(), anyString())).thenReturn(true);
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        userService.changePassword("john", "password", "newPassword");

        verify(userRepository, times(1)).existsUserByUsernameAndPassword(anyString(), anyString());
        verify(userRepository, times(1)).findByUsername(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }
}

