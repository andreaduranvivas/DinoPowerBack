package edu.eci.multirex.dinopower.model;

import java.awt.*;
import java.util.HashMap;

public abstract class GameObject{
    float xPos, yPos;
    float objWidth, objHeight;
    String sprite; // representación gráfica
    int[] spriteOffset = {0, 0}; // desplazamiento
    static HashMap<String, Image> gameSprites = new HashMap<>();

    /**
     * Draw the specified sprite onto the graphics context.
     *
     */

    /**
     * Determines whether this GameObject is colliding with another GameObject.
     *
     * @param  anObject  the other GameObject to check for collision
     * @return           true if there is a collision, false otherwise
     */
    public boolean isCollisioningWith(GameObject anObject) {
        return (xPos + objWidth > anObject.xPos && xPos < anObject.xPos + anObject.objWidth) &&
                (yPos + objHeight > anObject.yPos && yPos < anObject.yPos + anObject.objHeight);
    }

    public void toggleSprite() {}

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public float getObjWidth() {
        return objWidth;
    }

    public void setObjWidth(float objWidth) {
        this.objWidth = objWidth;
    }

    public float getObjHeight() {
        return objHeight;
    }

    public void setObjHeight(float objHeight) {
        this.objHeight = objHeight;
    }
}
