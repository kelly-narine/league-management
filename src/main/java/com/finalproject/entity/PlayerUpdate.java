package com.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//used for updating player in the list
public class PlayerUpdate {
    private Long id;
    private String firstName;
    private String lastName;
    private Date signedUpDate;
    private Date dateOfBirth;
    private Team team;
}
