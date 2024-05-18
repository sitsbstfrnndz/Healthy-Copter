package com.mygdx.game.inputs.playerMovements;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entities.EntityManager;
import com.mygdx.game.entities.gameEntities.PlayerEntity;

public class PlayerMovementManager implements MovementCallback {
    private Movement movement;
    private EntityManager entityManager;
    public void activateJetpack() {
        PlayerEntity player = entityManager.getPlayerEntity();
        player.activateJetpack();
    }

    public void deactivateJetpack() {
        PlayerEntity player = entityManager.getPlayerEntity();
        player.deactivateJetpack();
    }

    public PlayerMovementManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.movement = new Movement(this);
    }

    @Override
    public void updatePosition(float deltaX, float deltaY) {
        float deltaTime = Gdx.graphics.getDeltaTime(); // Get delta time
        PlayerEntity player = entityManager.getPlayerEntity();
        player.setX(player.getX() + deltaX * deltaTime);
        player.setY(player.getY() + deltaY * deltaTime);
    }


    public void movePlayerLeft() {
        float speed = entityManager.getPlayerEntity().getSpeed();
        movement.moveLeft(speed);
    }

    public void movePlayerRight() {
        float speed = entityManager.getPlayerEntity().getSpeed();
        movement.moveRight(speed);
    }

    public void movePlayerDown() {
        float speed = entityManager.getPlayerEntity().getSpeed();
        movement.moveDown(speed);
    }
}
