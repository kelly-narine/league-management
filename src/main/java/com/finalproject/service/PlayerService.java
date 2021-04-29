package com.finalproject.service;

import com.finalproject.entity.Player;
import com.finalproject.entity.PlayerUpdate;
import com.finalproject.entity.Team;

import java.util.List;

public interface PlayerService {
//    void clearList();
    List<Player> getPlayerList();
    void addToList(Player player);
    void removeFromList(Player player);
    Player getById(Long id);
    Player updatePlayer(PlayerUpdate dto, Player playerToUpdate);
}
