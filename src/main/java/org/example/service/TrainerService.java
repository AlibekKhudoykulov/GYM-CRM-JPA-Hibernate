package org.example.service;

import org.example.dto.TraineeDTO;
import org.example.dto.TrainerDTO;
import org.example.entity.Trainer;

public interface TrainerService {
    String create(TrainerDTO trainerDTO);
    Trainer getByUsername(String username);

    boolean changePassword(String username,String password, String newPassword);
    boolean update(Integer id,String username, String password, TrainerDTO trainerDTO) throws Exception;

}
