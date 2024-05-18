package com.mygdx.game.collision;

import com.mygdx.game.entities.AbstractEntity;
import com.mygdx.game.collision.IBoundaryConstraint;

public class ScreenBoundaryManager implements IBoundaryConstraint {
    private float screenWidth;
    private float screenHeight;

    public ScreenBoundaryManager(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void applyBoundaryConstraints(AbstractEntity entity) {
        // Adjust entity's position to keep it within screen bounds
        if (entity.getX() < 0) entity.setX(0);
        else if (entity.getX() + entity.getWidth() > screenWidth) entity.setX(screenWidth - entity.getWidth());

        if (entity.getY() < 0) entity.setY(0);
        else if (entity.getY() + entity.getHeight() > screenHeight) entity.setY(screenHeight - entity.getHeight());
    }
}
