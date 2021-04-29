package com.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data //generates getters and setters and toString method
@NoArgsConstructor //generates no arg constructor
@AllArgsConstructor //generates parameterized constructor with all fields
@Entity
@NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
@NamedQuery(name = "Player.getByFirstName", query = "SELECT p from Player p where p.firstName = :firstName")
@NamedQuery(name = "Player.clearAll", query = "DELETE FROM Player")
public class Player implements Serializable {
    @Id //specifies field is primary key
    @GeneratedValue //auto increments and auto generates field
    private Long id;
    private String firstName;
    private String lastName;
    private Date signedUpDate;
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "team_id_fk") //foreign key column
    private Team team; //bidirectional entity management

    @PrePersist //sets generated fields
    void createdAt() { //entity listener method that sets generated field
        this.signedUpDate = new Date();
    }

    @PreUpdate //sets generated fields
    void updatedAt() { //entity listener method that sets generated field
        this.signedUpDate = new Date();
    }

    //parameterized constructor with some fields
    public Player(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Player{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

}
