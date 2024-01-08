package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.entity.template.BaseEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Trainer extends BaseEntity {

    @OneToOne
    private User user;

    @ManyToOne
    private TrainingType trainingType;

    public Trainer(User user, TrainingType trainingType) {
        this.user = user;
        this.trainingType = trainingType;
    }
}
