package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class TraineeDTO {
    private String firstName;
    private String lastName;
    private String address;
    private Date dateOfBirth;
}
