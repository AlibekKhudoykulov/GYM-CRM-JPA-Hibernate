package org.example.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDTO {
        private int id;
        private int traineeId;
        private int trainerId;
        private String trainingName;
        private int trainingTypeId;
        private Date trainingDate;
        private int trainingDuration;

        public TrainingDTO(int traineeId, int trainerId, String trainingName, int trainingTypeId, Date trainingDate, int trainingDuration) {
                this.traineeId = traineeId;
                this.trainerId = trainerId;
                this.trainingName = trainingName;
                this.trainingTypeId = trainingTypeId;
                this.trainingDate = trainingDate;
                this.trainingDuration = trainingDuration;
        }
}
