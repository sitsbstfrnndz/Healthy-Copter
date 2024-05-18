package com.mygdx.game.collision;

import com.badlogic.gdx.math.Rectangle;

public interface Collidable {
    Rectangle getBoundingBox();
    void onCollision(Collidable other);

    // Provide a default implementation for update
    default void update() {
        // Default implementation does nothing
    }

    // Provide a default implementation for dispose
    default void dispose() {
        // Default implementation does nothing
    }
}