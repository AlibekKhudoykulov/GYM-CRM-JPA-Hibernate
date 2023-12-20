package org.example.repository;

import org.example.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee,Integer> {
    Trainee getTraineeByUser_Username(String username);

    @Modifying
    @Query("UPDATE Trainee t SET t.user.isActive = :isActive WHERE t.user.username = :username")
    void updateTraineeActivationStatus(@Param("username") String username, @Param("isActive") boolean isActive);
}
