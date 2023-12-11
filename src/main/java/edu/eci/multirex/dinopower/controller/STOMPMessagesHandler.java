package edu.eci.multirex.dinopower.controller;

import edu.eci.multirex.dinopower.model.Dino;
import edu.eci.multirex.dinopower.model.Player;
import edu.eci.multirex.dinopower.services.PlayerServices;
import edu.eci.multirex.dinopower.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

@Controller
public class STOMPMessagesHandler extends StompSessionHandlerAdapter {

    @Autowired
    SimpMessagingTemplate msgt;
    @Autowired
    private RoomServices roomServices;
    @Autowired
    private PlayerServices playerServices;

    @MessageMapping("/newroom")
    public void handleRoomEvent(){
        msgt.convertAndSend("/topic/newroom");
    }

    @MessageMapping("/removePlayer")
    public void handleRemovePlayerEvent(Player player) throws Exception {
        playerServices.removePlayer(player, roomServices.getRoom());
        msgt.convertAndSend("/topic/removePlayer", player);
    }

    @MessageMapping("/joinGame")
    public void handleJoinGameEvent(Player player) throws Exception {
        //playerServices.removePlayer(player, roomServices.getRoom());
        msgt.convertAndSend("/topic/joinGame", player);
    }

    @MessageMapping("/speedPower")
    public void handleSpeedPowerEvent(Player player) throws Exception {
        msgt.convertAndSend("/topic/speedPower", player);
        playerServices.speedPower(player, roomServices.getRoom());
    }

    @MessageMapping("/deadPlayer")
    public void handleDeadPlayerEvent(Player player) throws Exception {
        playerServices.deadPlayer(player, roomServices.getRoom());
        msgt.convertAndSend("/topic/deadPlayer", player);
        List<Player> playerList = roomServices.getRoom().getPlayerList();
        // Filtrar los jugadores que aún están vivos
        List<Player> alivePlayers = playerList.stream()
                .filter(Player::getAlive)
                .collect(Collectors.toList());

        if (alivePlayers.size() == 1) {
            Player winner = alivePlayers.get(0);
            winner.setPoints(10);  // Ajusta la cantidad de puntos según tus necesidades
            msgt.convertAndSend("/topic/notifyWinner", winner);
        }
    }

    @MessageMapping("/notifyWinner")
    public void handleNotifyWinnerEvent() throws Exception {
        List<Player> playerList = roomServices.getRoom().getPlayerList();
        
        // Filtrar los jugadores que aún están vivos
        List<Player> alivePlayers = playerList.stream()
                .filter(Player::getAlive)
                .collect(Collectors.toList());

        if (alivePlayers.size() == 1) {
            Player winner = alivePlayers.get(0);
            winner.setPoints(10);  // Ajusta la cantidad de puntos según tus necesidades
            msgt.convertAndSend("/topic/notifyWinner", winner);
        }
    }


    private Player getPlayerById(String playerId) {
        return roomServices.getRoom().getPlayerList().get(0);
    }
}
