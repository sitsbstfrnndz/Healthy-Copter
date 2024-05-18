package com.mygdx.game.entities.dynamicEntities;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.collision.Collidable;

public class CollidableDynamicEntity extends DynamicEntity implements Collidable {
    public CollidableDynamicEntity(String texturePath, float width, float height, float x, float y, float speed) {
        super(texturePath, width, height, x, y, speed);
    }

    @Override
    public void update() {
        super.update(); // Perform the base dynamic entity updates (includes movement)
        // Additional update logic for collidable dynamic entities can go here
    }


    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void onCollision(Collidable other) {
        // Handle collision logic here

        System.out.println("Dynamic Entity Collide with " + other);
        
        
    }
}
