package com.mygdx.game.inputs.playerMovements;


// defines a single method, updatePosition, which takes two float parameters: deltaX and deltaY. 
// These parameters represent the amount by which an entity's position should change along the X and Y axes, respectively. 
//By implementing this interface, any class can receive and process updates to its position, enabling dynamic movement within the game environment.

public interface MovementCallback {
    void updatePosition(float deltaX, float deltaY);
}