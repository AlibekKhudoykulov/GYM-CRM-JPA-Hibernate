package org.example.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.entity.template.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training extends BaseEntity {

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

}
