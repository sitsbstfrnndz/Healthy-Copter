package com.mygdx.game.ai.AIbehaviours;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.entities.dynamicEntities.AIEntity;
import com.mygdx.game.DesktopLauncher;

import static com.mygdx.game.scenes.GameLevelScene.floorHeight;
import static com.mygdx.game.scenes.GameLevelScene.playAreaHeight;

public abstract class RandomSpawnBehaviour extends AIBehaviour {
    protected int screenWidth = DesktopLauncher.width;
    protected int screenHeight = DesktopLauncher.height; // Ensure these are set correctly
    protected float respawnTimer = 0;
    protected boolean needsRespawn = false;

    public RandomSpawnBehaviour() {
        // Abstract class constructor.
    }

    protected void respawnEntity(AIEntity entity) {
        // This method now completely controls the respawn process
        if (needsRespawn) {
            float newX = screenWidth + 100;
            float newY = getRandomYWithinPlayArea(entity.getHeight(), floorHeight, playAreaHeight);

            entity.setX(newX);
            entity.setY(newY);
            entity.setVisible(true);

            needsRespawn = false; // Confirm respawn, reset the flag
            respawnTimer = 0; // Reset the timer
        }
    }

    @Override
    public void update(AIEntity entity) {
        if (needsRespawn) {
            respawnTimer += Gdx.graphics.getDeltaTime();
            if (respawnTimer >= MathUtils.random(1.0f, 3.0f)) { // Dynamic respawn timing
                respawnEntity(entity);
                needsRespawn = false; // Reset the flag after respawning
                respawnTimer = 0; // Reset the timer after respawning
            }
        } else if (entity.getX() + entity.getWidth() < 0) { // Checks if entity moves past left screen edge
            needsRespawn = true;
        }
    }

    @Override
    public void move(AIEntity entity) {
        // Implement specific movement logic for the AI entity if necessary.
    }

    // Overload or replace the above method (depending on your needs) to account for play area
    protected float getRandomYWithinPlayArea(float entityHeight, float floorHeight, float playAreaHeight) {
        // Ensure the random Y position is between the elevated floor and the upper limit of the play area
        return MathUtils.random(floorHeight, playAreaHeight - entityHeight);
    }
}
