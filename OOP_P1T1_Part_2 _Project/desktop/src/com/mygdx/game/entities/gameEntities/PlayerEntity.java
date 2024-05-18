package com.mygdx.game.entities.gameEntities;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entities.entityInterfaces.CollectibleEffect;
import com.mygdx.game.collision.Collidable;
import com.mygdx.game.entities.dynamicEntities.CollidableDynamicEntity;

import static com.mygdx.game.scenes.GameLevelScene.floorHeight;
import static com.mygdx.game.scenes.GameLevelScene.playAreaHeight;

public class PlayerEntity extends CollidableDynamicEntity {
    private float yVelocity = 0;
    private final float normalGravity = -9.8f; // Normal gravity
    private final float strongGravity = -130f; // Stronger gravity for faster descent
    private final float maxFallSpeed = -160f; // Maximum falling speed

    private final float jetpackForce = 160f;
    private final float strongJetpackForce = 150f; // Stronger jetpack thrust for quick ascent
    private float currentJetpackForce = jetpackForce;
    private float effectDuration;
    private boolean isJetpackActive = false;
    private boolean justDeactivatedJetpack = false; // Flag to check if jetpack was just turned off
    private boolean usingStrongJetpack = false; // Indicates if strong jetpack force is being used

    public PlayerEntity(String texturePath, float width, float height, float x, float y, float speed) {
        super(texturePath, width, height, x, y, speed);
    }

    public void activateJetpack() {
        isJetpackActive = true;
        justDeactivatedJetpack = false;
        usingStrongJetpack = true; // Activate strong jetpack force initially or under certain conditions
    }

    public void deactivateJetpack() {
        isJetpackActive = false;
        justDeactivatedJetpack = true;
        usingStrongJetpack = false; // Reset when jetpack is deactivated
    }

    @Override
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        // Determine which gravity to apply
        float appliedGravity = isJetpackActive ? normalGravity : (justDeactivatedJetpack ? strongGravity : normalGravity);

        // Apply gravity
        yVelocity += appliedGravity * deltaTime;

        // Apply jetpack force if active
        if (isJetpackActive) {
            float appliedJetpackForce = usingStrongJetpack ? strongJetpackForce : jetpackForce;
            yVelocity = Math.min(yVelocity + currentJetpackForce * deltaTime, currentJetpackForce);
        }

        // Ensure max falling speed is not exceeded
        if (!isJetpackActive) {
            yVelocity = Math.max(yVelocity, maxFallSpeed);
        }

        // Update player position
        float newY = getY() + yVelocity * deltaTime;

        // Check vertical screen boundaries (example assumes 0 is the bottom and screenHeight is the top)
        if (newY < floorHeight) {
            newY = floorHeight; // Ensure entity is on or above the elevated floor
            yVelocity = 0; // Stop downward movement
        } else if (newY > playAreaHeight - this.getHeight()) {
            newY = playAreaHeight - this.getHeight(); // Ensure entity doesn't go above the play area
            yVelocity = 0; // Stop upward movement
        }


        // Apply the new position
        setY(newY);

        // Reset the justDeactivatedJetpack flag if necessary
        if (justDeactivatedJetpack && yVelocity <= maxFallSpeed) {
            justDeactivatedJetpack = false;
        }

        // Update the effect timer
        if (effectDuration > 0) {
            effectDuration -= deltaTime;
            if (effectDuration <= 0) {
                // Time's up, revert to normal jetpack force
                currentJetpackForce = jetpackForce;
            }
        }
    }
    public void applyEffect(CollectibleEffect effect) {
        effect.applyEffect(this);
    }

    public void activateTemporaryJetpackForce(float newForce, float duration) {
        currentJetpackForce = newForce;
        effectDuration = duration;
    }

    @Override
    public void onCollision(Collidable other) {
        // Handle collision logic here
        if (other instanceof CollectibleEffect) {
            ((CollectibleEffect) other).applyEffect(this);
        }
        // Other collision handling...
    }
}
