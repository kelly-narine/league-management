package com.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
// used for updating Team in the list
public class TeamUpdate {
    private Long id;
    private String name;
    private Player player;
    private List<Player> playerList;
    private League league;
    private Date nextGameTime;
}
