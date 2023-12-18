package org.example.entity;

import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Trainer trainer;

    @ManyToOne
    private Trainee trainee;

    @NotNull
    private String trainingName;

    @ManyToOne
    private TrainingType trainingType;

    @NotNull
    private Date TrainingDate;

    @NotNull
    private int TrainingDuration;
}
