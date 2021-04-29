package com.finalproject.service;

import com.finalproject.entity.*;

import java.util.Date;
import java.util.List;

public interface TeamService {
    void createTeam(Team team);
    public List<Team> getTeamList();
    Team getById(Long id);
    Team updateTeam(TeamUpdate dto, Team teamToUpdate);
}
