package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.template.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Trainee extends BaseEntity {

    @OneToOne
    private User user;

    private Date dateOfBirth;
    private String address;

    public Trainee(User user, Date dateOfBirth, String address) {
        this.user = user;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
}
