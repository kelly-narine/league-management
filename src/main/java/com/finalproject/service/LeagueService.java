package com.finalproject.service;

import com.finalproject.entity.League;
import com.finalproject.entity.Player;
import com.finalproject.entity.Team;

import java.util.List;

public interface LeagueService {
    
    void createLeague(League league);

    List<League> getLeagueist();

}
