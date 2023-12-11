package edu.eci.multirex.dinopower.controller;


import edu.eci.multirex.dinopower.model.Player;
import edu.eci.multirex.dinopower.model.Room;
import edu.eci.multirex.dinopower.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/room")
public class RoomAPIController {

    @Autowired
    RoomServices roomServices = null;

    @GetMapping()
    public ResponseEntity<?> getRoom() {
        Room data = roomServices.getRoom();
        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);

    }

    @PostMapping
    public ResponseEntity<?> newRoom(@RequestBody Player player) {

        int playerNumber = roomServices.newRoom(player);
        return new ResponseEntity<>(playerNumber, HttpStatus.CREATED);
    }

}
