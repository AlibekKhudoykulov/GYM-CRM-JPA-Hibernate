package org.example.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Trainer trainer;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Trainee trainee;

    @NotNull
    private String trainingName;

    @ManyToOne
    private TrainingType trainingType;

    @NotNull
    private Date TrainingDate;

    @NotNull
    private int TrainingDuration;

    public Training(Trainer trainer,
                    Trainee trainee,
                    String trainingName,
                    TrainingType trainingType,
                    Date trainingDate,
                    int trainingDuration) {
        this.trainer = trainer;
        this.trainee = trainee;
        this.trainingName = trainingName;
        this.trainingType = trainingType;
        TrainingDate = trainingDate;
        TrainingDuration = trainingDuration;
    }
}
