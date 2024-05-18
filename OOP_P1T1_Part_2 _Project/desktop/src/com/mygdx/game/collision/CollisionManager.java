package com.mygdx.game.collision;


import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entities.AbstractEntity;
import com.mygdx.game.scenes.SceneManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class CollisionManager {
    private int width, height;
    // Assuming this list is maintained and updated elsewhere, like when entities are added or removed
    //private ScreenBoundaryManager boundaryManager = new ScreenBoundaryManager(width, height);
    private SceneManager sceneManager;

    private List<AbstractEntity> entities = new CopyOnWriteArrayList<>();

    public CollisionManager(int width, int height, SceneManager sceneManager) {
        this.width = width;
        this.height = height;
        this.sceneManager = sceneManager;
        // The CollisionManager starts with an empty list of entities
    }

    public void checkCollisions(List<AbstractEntity> entities) {
        // Filter for collidable entities internally
        List<Collidable> collidables = entities.stream()
                .filter(e -> e instanceof Collidable)
                .map(e -> (Collidable) e)
                .collect(Collectors.toList());

        for (int i = 0; i < collidables.size(); i++) {
            for (int j = i + 1; j < collidables.size(); j++) {
                Collidable collidableA = collidables.get(i);
                Collidable collidableB = collidables.get(j);

                if (collidesWith(collidableA, collidableB)) {
                    collidableA.onCollision(collidableB);
                    collidableB.onCollision(collidableA);
                }
            }
        }
    }

    private boolean collidesWith(Collidable a, Collidable b) {
        Rectangle rectA = a.getBoundingBox();
        Rectangle rectB = b.getBoundingBox();
        return rectA.overlaps(rectB);
    }
}