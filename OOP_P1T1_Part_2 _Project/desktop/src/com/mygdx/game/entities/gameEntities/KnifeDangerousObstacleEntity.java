package com.mygdx.game.entities.gameEntities;

import com.mygdx.game.ai.AIbehaviours.AIBehaviour;
import com.mygdx.game.scenes.SceneManager;
import com.mygdx.game.scenes.SceneType;
import com.mygdx.game.entities.dynamicEntities.DangerousObstacleAIEntity;
import com.mygdx.game.collision.Collidable;

public class KnifeDangerousObstacleEntity extends DangerousObstacleAIEntity {
    public KnifeDangerousObstacleEntity(String texturePath, float width, float height, float x, float y, float speed, AIBehaviour behaviour, SceneManager sceneManager) {
        super(texturePath, width, height, x, y, speed, behaviour);
        this.sceneManager = sceneManager;
    }
    @Override
    public void onCollision(Collidable other) {
        if (other instanceof PlayerEntity) {
            sceneManager.transitionTo(SceneType.GAME_END, 4);
        }
    }
}
