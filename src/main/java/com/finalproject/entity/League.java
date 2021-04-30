package com.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "League.findAll", query = "SELECT p FROM League p")
public class League {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @JsonIgnore //Allows team list not to be displayed
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Team> teamList;
}
