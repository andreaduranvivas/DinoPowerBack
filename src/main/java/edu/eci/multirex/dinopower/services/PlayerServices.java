package edu.eci.multirex.dinopower.services;

import edu.eci.multirex.dinopower.model.Player;
import edu.eci.multirex.dinopower.model.Room;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerServices {


    public int addPlayer(Player player, Room room){
        room.addPlayer(player);
        return player.getNumber();
    }
    public List<Player> getPlayerList(Room room){
        return room.getPlayerList();
    }
    public void removePlayer(Player player, Room room){
        room.removePlayer(player);
    }
    public int speedPower(Player player, Room room){
        room.setPlayerPoints(player.getNumber(), player.getPoints());
        return player.getNumber();
    }
    public int deadPlayer(Player player, Room room){
        room.setDeadPlayer(player.getNumber());
        return player.getNumber();
    }
}
