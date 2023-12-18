package org.example.service.impl;

import org.example.dto.TraineeDTO;
import org.example.repository.TraineeRepository;
import org.example.repository.UserRepository;
import org.example.service.TraineeService;
import org.example.util.UsernameAndPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImpl implements TraineeService {
    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private UsernameAndPasswordGenerator generator;

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Override
    public String create(TraineeDTO traineeDTO) {
        return null;
    }
}
