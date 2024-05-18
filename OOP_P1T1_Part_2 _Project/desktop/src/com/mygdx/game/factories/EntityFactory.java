package com.mygdx.game.factories;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ai.AIbehaviours.AIBehaviour;
import com.mygdx.game.ai.AIbehaviours.CollectableSpawnBehaviour;
import com.mygdx.game.ai.AIbehaviours.DangerousObstacleSpawnBehaviour;
import com.mygdx.game.graphics.Camera;
import com.mygdx.game.scenes.SceneManager;
import com.mygdx.game.ai.AIbehaviours.*;
import com.mygdx.game.entities.dynamicEntities.AIEntity;
import com.mygdx.game.entities.dynamicEntities.CollidableDynamicEntity;
import com.mygdx.game.entities.dynamicEntities.DynamicEntity;
import com.mygdx.game.entities.gameEntities.*;
import com.mygdx.game.entities.staticEntities.CollidableStaticEntity;
import com.mygdx.game.entities.staticEntities.StaticEntity;
import com.mygdx.game.graphics.Renderer;

import static com.mygdx.game.scenes.GameLevelScene.*;

public class EntityFactory {

    /**
     * Creates a player entity with specified properties.
     *
     * @param texturePath The path to the texture image for the player.
     * @param width The width of the player entity.
     * @param height The height of the player entity.
     * @param x The initial x-coordinate of the player entity.
     * @param y The initial y-coordinate of the player entity.
     * @param speed The movement speed of the player entity.
     * @return A new PlayerEntity instance with the specified properties.
     * @throws IllegalArgumentException If texturePath is null or empty, width or height is not positive, or speed is not positive.
     */
    // Template to create a Player Entity
    public static PlayerEntity createPlayerEntity(String texturePath, float width, float height, float x, float y, float speed) {
        if (texturePath == null || texturePath.isEmpty()) // Check if texture path given is empty
            throw new IllegalArgumentException("Texture path cannot be null or empty");
        if (width <= 0 || height <= 0) // Check if width and height is valid (positive values)
            throw new IllegalArgumentException("Width and height must be positive");
        if (speed <= 0) // Check if speed is valid (non-negative)
            throw new IllegalArgumentException("Speed must be positive");

        String effectiveTexturePath = validateTexturePath(texturePath, "textures/default/entity-defaults/player-entity-default.png");

        return new PlayerEntity(effectiveTexturePath, width, height, x, y, speed);
    }

    public static StaticEntity createStaticEntity(String texturePath, float width, float height, float x, float y) {

        if (texturePath == null || texturePath.isEmpty()) // Check if texture path given is empty
            throw new IllegalArgumentException("Texture path cannot be null or empty");
        if (width <= 0 || height <= 0) // Check if width and height  is valid (positive values)
            throw new IllegalArgumentException("Width and height must be positive");

        // Validate texturePath and assign the default texture respectively it to effectiveTexturePath
        String effectiveTexturePath = validateTexturePath(texturePath, "textures/default/entity-defaults/static-entity-default.png");

        return new StaticEntity(effectiveTexturePath, width, height, x, y);
    }

    public static KnifeDangerousObstacleEntity createDangerousObstacle(String texturePath, float width, float height, float x, float y, float speed, SceneManager sceneManager) {
        if (texturePath == null || texturePath.isEmpty())
            throw new IllegalArgumentException("Texture path cannot be null or empty");
        if (width <= 0 || height <= 0)

            throw new IllegalArgumentException("Width and height must be positive");
        if (speed < 0)
            throw new IllegalArgumentException("Speed must be non-negative");
        if (x < leftBound) // Check if X is within the horizontal play area
            throw new IllegalArgumentException("X coordinate must be within the play area");
        if (y < floorHeight || y + height > floorHeight + playAreaHeight) // Check if Y is within the vertical play area
            throw new IllegalArgumentException("Y coordinate must be within the play area");

        DangerousObstacleSpawnBehaviour behaviour = new DangerousObstacleSpawnBehaviour(10); // Example respawn time
        // Validate texturePath and assign the default texture if necessary
        String effectiveTexturePath = validateTexturePath(texturePath, "textures/obstacles/knife-dangerous-obstacle.png");


        return new KnifeDangerousObstacleEntity(effectiveTexturePath, width, height, x, y, speed, behaviour, sceneManager);
        }

    public static CollectibleBroccoliEntity createCollectibleBroccoli(String texturePath, float width, float height, float x, float y, float speed, Renderer renderer) {

        if (texturePath == null || texturePath.isEmpty())
            throw new IllegalArgumentException("Texture path cannot be null or empty");
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Width and height must be positive");
        if (speed < 0)
            throw new IllegalArgumentException("Speed must be non-negative");
        if (x < leftBound) // Check if X is within the horizontal play area
            throw new IllegalArgumentException("X coordinate must be within the play area");
        if (y < floorHeight || y + height > floorHeight + playAreaHeight) // Check if Y is within the vertical play area
            throw new IllegalArgumentException("Y coordinate must be within the play area");

        CollectableSpawnBehaviour behaviour = new CollectableSpawnBehaviour(7); // Different example respawn time for carrots

        // Validate texturePath and assign the default texture if necessary
        String effectiveTexturePath = validateTexturePath(texturePath, "textures/default/entity-defaults/ai-entity-default.png");

        return new CollectibleBroccoliEntity(effectiveTexturePath, width, height, x, y, speed, behaviour, renderer);
    }

    public static CollectibleCarrotEntity createCollectibleCarrot(String texturePath, float width, float height, float x, float y, float speed, Camera camera) {

        if (texturePath == null || texturePath.isEmpty())
            throw new IllegalArgumentException("Texture path cannot be null or empty");
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Width and height must be positive");
        if (speed < 0)
            throw new IllegalArgumentException("Speed must be non-negative");
        if (x < leftBound) // Check if X is within the horizontal play area
            throw new IllegalArgumentException("X coordinate must be within the play area");
        if (y < floorHeight || y + height > floorHeight + playAreaHeight) // Check if Y is within the vertical play area
            throw new IllegalArgumentException("Y coordinate must be within the play area");

        CollectableSpawnBehaviour behaviour = new CollectableSpawnBehaviour(7); // Different example respawn time for carrots

        // Validate texturePath and assign the default texture if necessary
        String effectiveTexturePath = validateTexturePath(texturePath, "textures/default/entity-defaults/ai-entity-default.png");

        return new CollectibleCarrotEntity(effectiveTexturePath, width, height, x, y, speed, behaviour, camera);
    }
    
    public static CollectibleDonutEntity createCollectibleDonut(String texturePath, float width, float height, float x, float y, float speed) {

        if (texturePath == null || texturePath.isEmpty())
            throw new IllegalArgumentException("Texture path cannot be null or empty");
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Width and height must be positive");
        if (speed < 0)
            throw new IllegalArgumentException("Speed must be non-negative");
        if (x < leftBound) // Check if X is within the horizontal play area
            throw new IllegalArgumentException("X coordinate must be within the play area");
        if (y < floorHeight || y + height > floorHeight + playAreaHeight) // Check if Y is within the vertical play area
            throw new IllegalArgumentException("Y coordinate must be within the play area");

        CollectableSpawnBehaviour behaviour = new CollectableSpawnBehaviour(7); // Different example respawn time for carrots

        // Validate texturePath and assign the default texture if necessary
        String effectiveTexturePath = validateTexturePath(texturePath, "textures/default/entity-defaults/ai-entity-default.png");

        return new CollectibleDonutEntity(effectiveTexturePath, width, height, x, y, speed, behaviour);
    }

    public static CollectibleChickenEntity createCollectibleChicken(String texturePath, float width, float height, float x, float y, float speed) {

        if (texturePath == null || texturePath.isEmpty())
            throw new IllegalArgumentException("Texture path cannot be null or empty");
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Width and height must be positive");
        if (speed < 0)
            throw new IllegalArgumentException("Speed must be non-negative");
        if (x < leftBound) // Check if X is within the horizontal play area
            throw new IllegalArgumentException("X coordinate must be within the play area");
        if (y < floorHeight || y + height > floorHeight + playAreaHeight) // Check if Y is within the vertical play area
            throw new IllegalArgumentException("Y coordinate must be within the play area");

        CollectableSpawnBehaviour behaviour = new CollectableSpawnBehaviour(7); // Different example respawn time for carrots

        // Validate texturePath and assign the default texture if necessary
        String effectiveTexturePath = validateTexturePath(texturePath, "textures/default/entity-defaults/ai-entity-default.png");

        return new CollectibleChickenEntity(effectiveTexturePath, width, height, x, y, speed, behaviour);
    }


    public static AIEntity createAIEntity(String texturePath, float width, float height, float x, float y, float speed, AIBehaviour behaviour) {

        if (texturePath == null || texturePath.isEmpty())
            throw new IllegalArgumentException("Texture path cannot be null or empty");
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Width and height must be positive");
        if (speed < 0)
            throw new IllegalArgumentException("Speed must be non-negative");
        if (behaviour == null)
            throw new IllegalArgumentException("AI Behaviour is NULL");
        if (x < leftBound) // Check if X is within the horizontal play area
            throw new IllegalArgumentException("X coordinate must be within the play area");
        if (y < floorHeight || y + height > floorHeight + playAreaHeight) // Check if Y is within the vertical play area
            throw new IllegalArgumentException("Y coordinate must be within the play area");

        // Validate texturePath and assign the default texture if necessary
        String effectiveTexturePath = validateTexturePath(texturePath, "textures/default/entity-defaults/ai-entity-default.png");

        // Here, you would check the type of behaviour and return the corresponding subclass of AIEntity.
        return new AIEntity(effectiveTexturePath, width, height, x, y, speed, behaviour);
    }


    public static CollidableStaticEntity createCollidableStaticEntity(String texturePath, float width, float height, float x, float y) {
        if (texturePath == null || texturePath.isEmpty()) // Check if texture path given is empty
            throw new IllegalArgumentException("Texture path cannot be null or empty");
        if (width <= 0 || height <= 0) // Check if width and height is valid (positive values)
            throw new IllegalArgumentException("Width and height must be positive");
        if (x < leftBound) // Check if X is within the horizontal play area
            throw new IllegalArgumentException("X coordinate must be within the play area");
        if (y < floorHeight || y + height > floorHeight + playAreaHeight) // Check if Y is within the vertical play area
            throw new IllegalArgumentException("Y coordinate must be within the play area");

        // Validate texturePath and assign the default texture respectively it to effectiveTexturePath
        String effectiveTexturePath = validateTexturePath(texturePath, "textures/default/entity-defaults/collidable-static-entity-default.png");

        return new CollidableStaticEntity(effectiveTexturePath, width, height, x, y);
    }

    public static DynamicEntity createDynamicEntity(String texturePath, float width, float height, float x, float y, float speed) {
        if (texturePath == null || texturePath.isEmpty()) // Check if texture path given is empty
            throw new IllegalArgumentException("Texture path cannot be null or empty");
        if (width <= 0 || height <= 0) // Check if width and height is valid (positive values)
            throw new IllegalArgumentException("Width and height must be positive");
        if (speed <= 0) // Check if speed is valid (non-negative)
            throw new IllegalArgumentException("Speed must be positive");
        if (x < leftBound) // Check if X is within the horizontal play area
            throw new IllegalArgumentException("X coordinate must be within the play area");
        if (y < floorHeight || y + height > floorHeight + playAreaHeight) // Check if Y is within the vertical play area
            throw new IllegalArgumentException("Y coordinate must be within the play area");

        // Validate texturePath and assign the default texture respectively it to effectiveTexturePath
        String effectiveTexturePath = validateTexturePath(texturePath, "textures/default/entity-defaults/dynamic-entity-default.png");

        return new DynamicEntity(effectiveTexturePath, width, height, x, y,speed);
    }

    public static CollidableDynamicEntity createCollidableDynamicEntity(String texturePath, float width, float height, float x, float y, float speed) {
        if (texturePath == null || texturePath.isEmpty()) // Check if texture path given is empty
            throw new IllegalArgumentException("Texture path cannot be null or empty");
        if (width <= 0 || height <= 0) // Check if width and height is valid (positive values)
            throw new IllegalArgumentException("Width and height must be positive");
        if (speed <= 0) // Check if speed is valid (non-negative)
            throw new IllegalArgumentException("Speed must be positive");
        if (x < leftBound) // Check if X is within the horizontal play area
            throw new IllegalArgumentException("X coordinate must be within the play area");
        if (y < floorHeight || y + height > floorHeight + playAreaHeight) // Check if Y is within the vertical play area
            throw new IllegalArgumentException("Y coordinate must be within the play area");

        // Validate texturePath and assign the default texture respectively it to effectiveTexturePath
        String effectiveTexturePath = validateTexturePath(texturePath, "textures/default/entity-defaults/collidable-dynamic-entity-default.png");

        return new CollidableDynamicEntity(effectiveTexturePath, width, height, x, y,speed);
    }

    private static String validateTexturePath(String texturePath, String defaultTexturePath) {
        Texture testTexture = null;
        try {
            testTexture = new Texture(Gdx.files.internal(texturePath));
            return texturePath; // Texture loaded successfully
        } catch (Exception e) {
            System.err.println("Warning: Could not load texture at " + texturePath + ", falling back to default texture.");
            return defaultTexturePath; // Use respective default texture
        } finally {
            if (testTexture != null) {
                testTexture.dispose(); // Properly dispose of the test texture to avoid memory leaks
            }
        }
    }
}
