package edu.eci.multirex.dinopower.model;

public abstract class Obstacle extends GameObject{

    Obstacle() {
        xPos = 1350;
    }

    /**
     * Updates the position of the object based on the given speed.
     *
     * @param  speed  the speed at which the object should be updated
     */
    public void update(int speed) {
        xPos -= speed;
    }

    /**
     * A description of the is_offscreen function.
     *
     * @return         	description of return value
     */
    public boolean isOffscreen() {
        return xPos + objWidth < 0;
    }
}

