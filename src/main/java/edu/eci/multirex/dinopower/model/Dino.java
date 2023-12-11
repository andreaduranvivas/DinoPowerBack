package edu.eci.multirex.dinopower.model;

public class Dino extends GameObject implements Comparable<Dino>{

    float jumpStage;
    boolean alive = true;
    int score;
    boolean isJumping = false;

    public Dino() {
        xPos = 2400;
        yPos = 515;
        objWidth = 80;
        objHeight = 86;

        jumpStage = 0;
        sprite = "walking_dino_1";
        spriteOffset[0] = -4;
        spriteOffset[1] = -2;
    }

    /**
     * Updates the speed of the object.
     *
     * @param  speed  the new speed to update to
     */
    public void update(int speed) {
        xPos -= speed;
        if ( jumping() ){
            updateJump();
        }
    }

    public void keyPressed(String key){
        if (key == "UP" && !crouching() && !jumping()){
            jump();
        }
        else if (key == "DOWN") {
            if (crouching()){
                stopCrouch();
            }else if (isJumping) {
                stopJump();
            }
            crouch();
        }
    }

    public void keyReleased(String key){
        if (key == "DOWN") {
            stopCrouch();
        }
    }


    /**
     * Updates the jump animation of the character.
     *
     */
    void updateJump() {
        yPos = (int)(450 - ((-4 * jumpStage * (jumpStage - 1)) * 172));
        jumpStage += 0.03;
        if ( jumpStage > 1 ){
            stopJump();
        }
    }

    /**
     * A description of the entire Java function to jump.
     *
     */
    void jump(){
        jumpStage = 0.0001F;
        sprite = "standing_dino";
    }

    /**
     * Stops the jumping action of the character.
     *
     */
    void stopJump() {
        jumpStage = 0;
        yPos = 450;
        sprite = "walking_dino_1";
    }

    /**
     * Crouch action for the character.
     *
     */
    void crouch(){
        if ( !crouching() ) {
            yPos = 484;
            objWidth = 110;
            objHeight = 52;
            sprite = "crouching_dino_1";
        }
    }

    /**
     * Sets the position, dimensions, and sprite of the character to stop crouching.
     */
    void stopCrouch(){
        yPos = 450;
        objWidth = 80;
        objHeight = 86;
        sprite = "walking_dino_1";
    }

    /**
     * Determines if the jump stage is greater than zero.
     *
     * @return true if the jump stage is greater than zero, false otherwise
     */
    boolean jumping() {
        return jumpStage > 0;
    }

    /**
     * Checks if the object is crouching.
     *
     * @return  true if the object width is 110, false otherwise
     */
    boolean crouching() {
        return objWidth == 110;
    }

    /**
     * A function that sets the "alive" variable to false and assigns
     * the given "simScore" to the "score" variable.
     *
     * @param  simScore  the similarity score to assign to "score"
     */
    public void die(int simScore) {
        alive = false;
        score = simScore;
    }

    /**
     * Resets the state of the object.
     *
     */
    void reset() {
        alive = true;
        score = 0;
    }

    /**
     * Toggles the sprite between different states.
     */
    public void toggleSprite() {
        if ( sprite.equals("walking_dino_1") ) {
            sprite = "walking_dino_2";
        } else if ( sprite.equals("walking_dino_2") ) {
            sprite = "walking_dino_1";
        } else if ( sprite.equals("crouching_dino_1") ) {
            sprite = "crouching_dino_2";
        } else if ( sprite.equals("crouching_dino_2") ) {
            sprite = "crouching_dino_1";
        }
    }

    /**
     * Compares this Dino object with the specified Dino object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less than, equal
     * to, or greater than the specified object.
     *
     * @param  otherDino  the Dino object to be compared
     * @return            a negative integer, zero, or a positive integer as this object
     *                    is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(Dino otherDino) {
        return Integer.compare(this.score, otherDino.score);
    }

    public boolean isAlive() {
        return alive;
    }

    public float getJumpStage() {
        return jumpStage;
    }

    public void setJumpStage(float jumpStage) {
        this.jumpStage = jumpStage;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
