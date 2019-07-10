package com.saleswar.game.repository;

import java.util.ArrayList;
import java.util.List;

import com.saleswar.game.entities.Player;

public class PlayerRepository {

    private List<Player> rooms;

    public PlayerRepository() {

        this.rooms = new ArrayList<Player>();

        rooms.add(new Player(1, "fufu"));
       
    }

    public List<Player> getRooms() {

        return rooms;
    }

    
    public Player getRoomById(int id) {

        for(Player room : rooms) {

            if(room.getId() == id) {

                return room;
            }
        }
        return null;
    }
}