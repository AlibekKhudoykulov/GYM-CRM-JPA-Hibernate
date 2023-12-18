package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
