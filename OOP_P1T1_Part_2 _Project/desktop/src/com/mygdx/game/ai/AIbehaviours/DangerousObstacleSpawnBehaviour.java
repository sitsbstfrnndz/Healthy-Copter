package com.mygdx.game.ai.AIbehaviours;

import com.mygdx.game.entities.dynamicEntities.AIEntity;

public class DangerousObstacleSpawnBehaviour extends RandomSpawnBehaviour {
    private float respawnTime;
    private float respawnTimer = 0;
    private boolean isWaitingForRespawn;

    public DangerousObstacleSpawnBehaviour(float respawnTime) {
        this.respawnTime = respawnTime;
        this.isWaitingForRespawn = true;
    }

    @Override
    public void update(AIEntity entity) {
        super.update(entity);
    }
}
