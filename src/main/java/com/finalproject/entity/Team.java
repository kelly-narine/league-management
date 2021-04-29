package com.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")
@NamedQuery(name = "Team.getByName", query = "SELECT t from Team t where t.name = :name")
@NamedQuery(name = "Team.clearAll", query = "DELETE FROM Team")
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @JsonIgnore //Will not enable player list to display
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Player> playerList;

    @ManyToOne
    @JoinColumn(name = "league_id_fk") //foreign key column
    private League league; //bidirectional entity management

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    private Date nextGameTime;


    public Team(String name, List<Player> playerList, Date nextGameTime) {
        this.name = name;
        this.playerList = playerList;
        this.nextGameTime = nextGameTime;
    }
}
