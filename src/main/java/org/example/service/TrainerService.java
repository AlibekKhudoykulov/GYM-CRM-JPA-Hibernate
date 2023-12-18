package org.example.service;

import org.example.dto.TrainerDTO;
import org.example.entity.Trainer;

public interface TrainerService {
    String create(TrainerDTO trainerDTO);
}
