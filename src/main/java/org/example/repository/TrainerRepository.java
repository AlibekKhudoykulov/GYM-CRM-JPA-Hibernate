package org.example.repository;

import org.example.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository< Trainer,Integer> {
}
