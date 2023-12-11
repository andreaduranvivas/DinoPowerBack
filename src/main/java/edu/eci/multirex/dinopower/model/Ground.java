package edu.eci.multirex.dinopower.model;

public class Ground extends GameObject {
    public Ground() {
        xPos = 2400;
        yPos = 515;
        sprite = "ground";
    }

    /**
     * Updates the position of the object based on the provided speed.
     *
     * @param  speed  the speed at which the object should be updated
     */
    public void update(int speed) {
        xPos -= speed;
        if (xPos <= 0) {
            xPos = 2400;
        }
    }
}
