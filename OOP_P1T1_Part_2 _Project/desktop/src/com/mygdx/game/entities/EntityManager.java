package com.mygdx.game.entities;

import com.mygdx.game.ai.AIManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mygdx.game.collision.CollisionManager;
import com.mygdx.game.entities.gameEntities.PlayerEntity;
import com.mygdx.game.inputs.playerMovements.PlayerMovementManager;
import com.mygdx.game.scenes.SceneManager;


public class EntityManager {
    private List<AbstractEntity> entityList = new CopyOnWriteArrayList<>();

    // AIManager and CollisionManager are passed in, following Dependency Injection principle
    private AIManager aiManager;
    private CollisionManager collisionManager;
    private SceneManager sceneManager;
    private PlayerEntity playerEntity;
    private PlayerMovementManager playerMovementManager;

    public EntityManager(AIManager aiManager, CollisionManager collisionManager) {
        this.aiManager = aiManager;
        this.collisionManager = collisionManager;
    }


    public void addEntity(AbstractEntity e) {
        entityList.add(e);
        if (e instanceof PlayerEntity) {
            setPlayerEntity((PlayerEntity) e); // Set the player entity for direct access, if needed
        }
    }
    
    public PlayerEntity getPlayerEntity() {
        return this.playerEntity;
    }
    public void setPlayerEntity(PlayerEntity player) {
        this.playerEntity = player;
    }


    public void update() {
        // Update all entities
        entityList.forEach(AbstractEntity::update);

        collisionManager.checkCollisions(entityList);

        // Update AI for all entities
        aiManager.update(entityList);
    }
    public List<AbstractEntity> getEntityList() {
        return entityList;
    }

    public void dispose() {
        // Dispose of resources if necessary for entities
        entityList.forEach(AbstractEntity::dispose);
        entityList.clear();
    }

    public void applyBoundaryConstraints(float screenWidth, float screenHeight) {
        for (AbstractEntity entity : entityList) {
            // Apply boundary constraints to each entity
            entity.applyBoundaryConstraints(screenWidth, screenHeight);
        }
    }

    public void removeEntity(AbstractEntity e) {
        entityList.remove(e);
    }

    // Additional methods as needed...
}
