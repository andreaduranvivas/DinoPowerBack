package edu.eci.multirex.dinopower.controller;

import edu.eci.multirex.dinopower.model.Player;
import edu.eci.multirex.dinopower.model.Room;
import edu.eci.multirex.dinopower.services.PlayerServices;
import edu.eci.multirex.dinopower.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/room/players")
public class PlayersAPIController {

    @Autowired
    PlayerServices playerServices = null;
    @Autowired
    RoomServices roomServices = null;

    @GetMapping()
    public ResponseEntity<?> getPlayers() {
        Room room = roomServices.getRoom();
        List<Player> data = playerServices.getPlayerList(room);
        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);

    }

    @PostMapping
    public synchronized ResponseEntity<?> addPlayer(@RequestBody Player player) {
        Room room = roomServices.getRoom();
        int data = playerServices.addPlayer(player, room);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
}
