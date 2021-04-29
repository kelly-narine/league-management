package com.finalproject.service;

import com.finalproject.entity.League;
import com.finalproject.entity.Player;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class LeagueServiceImpl implements LeagueService {
    @PersistenceContext
    private EntityManager em;

    //create a league
    @Override
    public void createLeague(League league) {
        em.persist(league);
    }

    //get list of leagues
    @Override
    public List<League> getLeagueist() {
        List<League> leagueList =  em.createNamedQuery("League.findAll", League.class)
                .getResultList();
        return leagueList.stream()
                .collect(Collectors.toList());
    }
}
