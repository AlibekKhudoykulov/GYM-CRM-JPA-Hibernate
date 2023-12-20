package org.example.dto;

import lombok.*;

import java.util.Date;

@Data
public class TrainingDTO {
        private int id;
        private int traineeId;
        private int trainerId;
        private String trainingName;
        private int trainingTypeId;
        private Date trainingDate;
        private float trainingDuration;
}
