package com.finalproject.service;

import com.finalproject.entity.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TeamServiceImpl implements TeamService {
    @PersistenceContext
    private EntityManager em;

    //create a team
    @Override
    public void createTeam(Team team) {
        em.persist(team);
    }

    //get teams list
    @Override
    public List<Team> getTeamList() {
        List<Team> teamList =  em.createNamedQuery("Team.findAll", Team.class)
                .getResultList();
        return teamList.stream()
                .collect(Collectors.toList());
    }

    //retrieve team based on id
    @Override
    public Team getById(Long id) {
        return em.find(Team.class, id);
    }

    //update a team
    @Override
    public Team updateTeam(TeamUpdate dto, Team teamToUpdate) {
        if (dto.getName() != null) {
            teamToUpdate.setName(dto.getName());
        }
        em.merge(teamToUpdate);
        return teamToUpdate;
    }

}
