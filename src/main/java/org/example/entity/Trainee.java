package org.example.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    private Date dateOfBirth;
    private String address;
}
