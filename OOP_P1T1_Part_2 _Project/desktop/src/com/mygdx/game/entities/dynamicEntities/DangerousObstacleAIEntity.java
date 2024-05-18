package com.mygdx.game.entities.dynamicEntities;

import com.mygdx.game.ai.AIbehaviours.AIBehaviour;
import com.mygdx.game.collision.Collidable;

// This class extends AIEntity and will represent all collectible items in the game.
public class DangerousObstacleAIEntity extends AIEntity implements Collidable {

    // Additional attributes specific to collectibles could be added here.
    public DangerousObstacleAIEntity(String texturePath, float width, float height, float x, float y, float speed, AIBehaviour behaviour) {
        super(texturePath, width, height, x, y, speed, behaviour);
    }
}

