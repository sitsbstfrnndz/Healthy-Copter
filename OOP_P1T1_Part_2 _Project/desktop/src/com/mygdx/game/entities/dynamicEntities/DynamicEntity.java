package com.mygdx.game.entities.dynamicEntities;

import com.mygdx.game.entities.AbstractEntity;

public class DynamicEntity extends AbstractEntity {
    private float speed;

    public DynamicEntity(String texturePath, float width, float height, float x, float y, float speed) {
        super(texturePath, width, height, x, y);
        this.speed = speed;
    }

    public void movement() {
    }

    @Override
    public void update() {
                /*
        consider whether any update logic common across dynamic entities
        (not just player movement) could be moved into the DynamicEntity class,
        such as collision detection.

         */
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }
    public float getSpeed()
    {
        return this.speed;
    }

    @Override
    public void applyBoundaryConstraints(AbstractEntity entity) {

    }
}
