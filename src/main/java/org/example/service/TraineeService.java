package org.example.service;

import org.example.dto.TraineeDTO;
import org.example.entity.Trainee;

public interface TraineeService {
    String create(TraineeDTO traineeDTO);
    Trainee getByUsername(String username);
    boolean changePassword(String username,String password, String newPassword);
    boolean update(Integer id,String username, String password, TraineeDTO traineeDTO) throws Exception;
}
