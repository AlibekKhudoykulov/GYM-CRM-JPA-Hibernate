package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO {
    private String firstName;
    private String lastName;
    private int trainingTypeId;
}
