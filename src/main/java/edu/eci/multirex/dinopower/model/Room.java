package edu.eci.multirex.dinopower.model;

import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class Room {

    private List<Player> playerList;
    private final AtomicBoolean isClosed = new AtomicBoolean(false);
    public static final AtomicInteger lock = new AtomicInteger(0);
    private Player host = null;

    public Room() {

        this.playerList = new ArrayList<>();

    }
    public Room(Player player) {

        this.playerList = new ArrayList<>();
        player.setNumber(this.generatePlayerNumber());
        this.setHost(player);
        this.addPlayer(player);

    }

    public void setHost(Player host) {
        if(this.host == null) {
            this.host = host;
        }
    }
    public Boolean removePlayer(Player player){
        synchronized (playerList){
            for(Player playerR : playerList){
                if(playerR.getNumber() == player.getNumber()){
                    playerList.remove(playerR);
                    break;
                }
            }
            //playerList.remove(player.getNumber());
        }
        return false;
    }
    public Boolean addPlayer(Player player) {
        synchronized (lock) {
            if(!isClosed.get()) {
                player.setNumber(this.generatePlayerNumber());
                setHost(player);
                playerList.add(player);
                return true;
            }
        }
        return false;
    }

    private void setClosed() {
        if(playerList.size() >= 50) {
            isClosed.set(true);
        }
    }

    public List<Player> getPlayerList(){
        return this.playerList;
    }
    public Player getPlayerByNumber(int playerNumber){
        synchronized (playerList){
            for(Player playerR : playerList){
                if(playerNumber == playerR.getNumber()){
                    return playerR;
                }
            }
            //playerList.remove(player.getNumber());
        }
        return null;
    }
    public void setPlayerPoints(int playerNumber, int playerPoints){
        synchronized (playerList){
            for(Player playerR : playerList){
                if(playerNumber == playerR.getNumber()){
                    playerR.setPoints(playerPoints);
                }
            }
        }

    }

    public void setDeadPlayer(int playerNumber){
        synchronized (playerList){
            for(Player playerR : playerList){
                if(playerNumber == playerR.getNumber()){
                    playerR.setAlive(false);
                }
            }
        }

    }

    public int generatePlayerNumber(){
        if (playerList.isEmpty()){
            return 1;
        }
        Player lastPlayer = playerList.get(playerList.size() - 1);
        return lastPlayer.getNumber()+1;
    }


}
