package edu.eci.multirex.dinopower.model;

import java.util.Objects;

public class Player {
    private int number;
    private int points;
    private Boolean alive = true;

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public Player(){
        this.points = 0;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString(){
        return "{number: "+ number + " points: " +points+ " alive: "+alive +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return number == player.number && points == player.points && Objects.equals(alive, player.alive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, points, alive);
    }
}
