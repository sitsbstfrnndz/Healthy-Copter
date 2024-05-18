package com.mygdx.game.entities.staticEntities;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entities.dynamicEntities.DynamicEntity;
import com.mygdx.game.collision.Collidable;

public class CollidableStaticEntity extends StaticEntity implements Collidable {
    // Constructor
    public CollidableStaticEntity(String texturePath, float width, float height, float x, float y) {
        super(texturePath, width, height, x, y);
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void onCollision(Collidable other) {
        if (other instanceof DynamicEntity) {
            System.out.println("Dynamic entity has collided with a static object!");

            // Example of a simple collision response:
            // Here, you might want to stop the dynamic entity's movement or bounce it back.
            // This is just a placeholder; actual response would depend on your game's mechanics.
            DynamicEntity dynamicEntity = (DynamicEntity) other;
            // For example, stop the entity's movement
            dynamicEntity.setSpeed(0);

            // If you have a method to "bounce back" the entity, you might call it here
            // dynamicEntity.bounceBack();
        }
    }
}
