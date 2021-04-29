package com.finalproject.service;

import com.finalproject.entity.Player;
import com.finalproject.entity.PlayerUpdate;
import com.finalproject.entity.Team;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PlayerServiceImpl implements PlayerService {

    @PersistenceContext //contains info about database. Translates Java Code to SQL code
    private EntityManager em; //gives result of database in Java terms. Communicates with Persistence Context

//    @Override
//    public void clearList() {
//        Query deleteFromPlayer = em.createNamedQuery("Player.clearAll");
//        deleteFromPlayer.executeUpdate();
//    }

    //obtain players from list of players
    @Override
    public List<Player> getPlayerList() {
        List<Player> playerList =  em.createNamedQuery("Player.findAll", Player.class)
                .getResultList();

        return playerList.stream()
                .collect(Collectors.toList());
    }

    //add player to the list
    @Override
    public void addToList(Player player) {
        em.persist(player);
    }

    //remove player from list
    @Override
    public void removeFromList(Player player) {
        //Player playerWithId = em.find(Player.class, com.kovunov.player.getId());
        Player correspondingPlayer = em
                .createNamedQuery("Player.getByFirstName", Player.class)
                .setParameter("firstName", player.getFirstName())
                .getSingleResult();
        em.remove(correspondingPlayer);
    }

    //retrieve player based on id
    @Override
    public Player getById(Long id) {
        return em.find(Player.class, id);
    }

    //update the player
    @Override
    public Player updatePlayer(PlayerUpdate dto, Player playerToUpdate) {
        if (dto.getFirstName() != null) {
            playerToUpdate.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            playerToUpdate.setLastName(dto.getLastName());
        }
        if (dto.getSignedUpDate() != null) {
            playerToUpdate.setSignedUpDate(dto.getSignedUpDate());
        }
        if (dto.getDateOfBirth() != null) {
            playerToUpdate.setDateOfBirth(dto.getDateOfBirth());
        }
        em.merge(playerToUpdate);
        return playerToUpdate;
    }

}
