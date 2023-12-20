package org.example.repository;

import org.example.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository< Trainer,Integer> {
    Trainer getTrainerByUser_Username(String username);

    @Modifying
    @Query("UPDATE Trainer t SET t.user.isActive = :isActive WHERE t.user.username = :username")
    void updateTrainerActivationStatus(@Param("username") String username, @Param("isActive") boolean isActive);
}
