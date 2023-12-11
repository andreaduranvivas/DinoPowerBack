package edu.eci.multirex.dinopower.services;

import edu.eci.multirex.dinopower.model.Player;
import edu.eci.multirex.dinopower.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServices {
    //@Autowired
    private Room room = new Room();

    public Room getRoom() {
        return this.room;
    }

    public int newRoom(Player player) {
        this.room = new Room(player);
        return player.getNumber();
    }
}
