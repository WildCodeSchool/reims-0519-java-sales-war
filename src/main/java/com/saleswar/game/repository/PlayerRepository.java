package com.saleswar.game.repository;

import java.util.ArrayList;
import java.util.List;

import com.saleswar.game.entities.Player;

public class PlayerRepository {

    private List<Player> players;

    public PlayerRepository() {

        this.players = new ArrayList<Player>();

        players.add(new Player(1, "fufu"));
       
    }

    public List<Player> getPlayer() {

        return players;
    }

    
    public Player getPlayerById(int id) {

        for(Player player : players) {

            if(player.getId() == id) {

                return player;
            }
        }
        return null;
    }
}