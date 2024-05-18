package com.mygdx.game.entities.dynamicEntities;

import com.mygdx.game.ai.AIbehaviours.AIBehaviour;
import com.mygdx.game.ai.AIbehaviours.CollectableSpawnBehaviour;
import com.mygdx.game.collision.Collidable;
import com.mygdx.game.entities.gameEntities.PlayerEntity;

// This class extends AIEntity and will represent all collectible items in the game.
public class CollectibleAIEntity extends AIEntity implements Collidable {
    private AIBehaviour behaviour; // Add this line to hold the behavior

    public CollectibleAIEntity(String texturePath, float width, float height, float x, float y, float speed, AIBehaviour behaviour) {
        super(texturePath, width, height, x, y, speed,behaviour);
        this.behaviour = behaviour; // Set the behavior here
    }

    @Override
    public void onCollision(Collidable other) {
        if (other instanceof PlayerEntity) {
            this.setVisible(false); // Hide the collectible immediately upon collision
            // Check if the behaviour is of the correct type before casting
            if (this.getBehaviour() instanceof CollectableSpawnBehaviour) {
                CollectableSpawnBehaviour spawnBehaviour = (CollectableSpawnBehaviour) this.getBehaviour();
                spawnBehaviour.startRespawnTimer(); // Trigger the respawn process in the behaviour
            }
        }
    }

    // Implement the getBehaviour() method to return the AI behaviour
    public AIBehaviour getBehaviour() {
        return this.behaviour;
    }

}
