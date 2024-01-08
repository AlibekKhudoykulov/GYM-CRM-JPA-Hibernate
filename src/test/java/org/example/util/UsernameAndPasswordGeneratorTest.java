package org.example.util;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.stream.IntStream;


public class UsernameAndPasswordGeneratorTest {

    @InjectMocks
    private UsernameAndPasswordGenerator generator;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateUsername() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        when(userRepository.existsByUsername("John.Doe")).thenReturn(true);
        when(userRepository.existsByUsername("John.Doe1")).thenReturn(false);

        String username = generator.generateUsername(user);
        assertEquals("John.Doe1", username);
    }

    @Test
    public void testGenerateRandomPassword() {
        String password = generator.generateRandomPassword();

        // Check if password length is 10
        assertEquals(10, password.length());

        // Check if password contains only valid characters
        boolean hasInvalidChars = IntStream.range(0, password.length())
                .anyMatch(i -> !Character.isLetterOrDigit(password.charAt(i)));
        assertFalse(hasInvalidChars);
    }
}