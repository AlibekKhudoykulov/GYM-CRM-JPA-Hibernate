package org.example.repository;

import org.example.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraineeRepository extends JpaRepository<Trainee,Integer> {
    Trainee getTraineeByUser_Username(String username);
}
