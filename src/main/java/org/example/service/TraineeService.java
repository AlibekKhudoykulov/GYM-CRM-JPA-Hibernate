package org.example.service;

import org.example.dto.TraineeDTO;
import org.example.entity.Trainee;
import org.springframework.stereotype.Service;

@Service
public interface TraineeService {
    String create(TraineeDTO traineeDTO);
    Trainee getByUsername(String username);
    boolean changePassword(String username,String password, String newPassword);
    boolean update(Integer id,String username, String password, TraineeDTO traineeDTO) throws Exception;
    boolean activateOrDeActivate(String username,String password, boolean isActive);

    void delete(Integer id,String username, String password);

}
