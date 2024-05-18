package com.mygdx.game.ai.AIbehaviours;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.entities.dynamicEntities.AIEntity;

import static com.mygdx.game.scenes.GameLevelScene.floorHeight;
import static com.mygdx.game.scenes.GameLevelScene.playAreaHeight;

public class CollectableSpawnBehaviour extends RandomSpawnBehaviour {
    private float respawnTime;
    private float respawnTimer = 0;
    private boolean isWaitingForRespawn = false;

    public CollectableSpawnBehaviour(float respawnTime) {
        this.respawnTime = respawnTime;
    }

    @Override
    public void update(AIEntity entity) {
        super.update(entity);

        float deltaTime = Gdx.graphics.getDeltaTime();
        if (this.isWaitingForRespawn) {
            this.respawnTimer += deltaTime;
            if (this.respawnTimer >= this.respawnTime) {
                // Respawn the entity just beyond the right edge of the screen
                float newX = screenWidth;  // Set X just outside the right edge

                // Adjust newY so the entity spawns between the elevated floor and the top of the play area
                float newY = getRandomYWithinPlayArea(entity.getHeight(), floorHeight, playAreaHeight);

                entity.setX(newX);
                entity.setY(newY);
                entity.setVisible(true);
                this.isWaitingForRespawn = false;
                this.respawnTimer = 0;
            }
        }
    }



    public void startRespawnTimer() {
        this.isWaitingForRespawn = true;
        this.respawnTimer = 0; // Initialize the timer
    }

    @Override
    protected float getRandomYWithinPlayArea(float entityHeight, float floorHeight, float playAreaHeight) {
        // Ensure the random Y position is between the elevated floor and the upper limit of the play area
        return MathUtils.random(floorHeight, playAreaHeight - entityHeight);
    }

}
