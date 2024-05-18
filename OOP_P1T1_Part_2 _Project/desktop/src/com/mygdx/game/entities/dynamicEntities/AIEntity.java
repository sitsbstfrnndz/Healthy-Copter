package com.mygdx.game.entities.dynamicEntities;

import com.mygdx.game.ai.AIbehaviours.AIBehaviour;

public class AIEntity extends CollidableDynamicEntity {
    private AIBehaviour behaviour; // The AI behaviour
    private float backgroundScrollSpeed = 0; // Scroll speed of the background

    public AIEntity(String texturePath, float width, float height, float x, float y, float speed, AIBehaviour behaviour) {
        super(texturePath, width, height, x, y, speed);
        this.behaviour = behaviour;
    }

    @Override
    public void movement() {
        // AIEntity does not use the base DynamicEntity movement logic tied to player input.
        // Here you could adjust the entity's position based on backgroundScrollSpeed,
        // if its movement should be influenced by the scrolling background.
    }

    @Override
    public void update() {
        super.update(); // Call DynamicEntity update if necessary
        if (behaviour != null) {
            behaviour.update(this); // Update according to the AI behaviour
            behaviour.move(this); // Pass self to move based on AI behaviour
        }
        // Apply boundary constraints to keep the AI entity within the screen
    }

    // Method to set the background scroll speed
    public void setBackgroundScrollSpeed(float backgroundScrollSpeed) {
        this.backgroundScrollSpeed = backgroundScrollSpeed;
    }
}