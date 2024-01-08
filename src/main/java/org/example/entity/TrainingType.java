package org.example.entity;

import lombok.Data;
import org.example.entity.template.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class TrainingType extends BaseEntity {
    private String name;

}
