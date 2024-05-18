package com.mygdx.game.entities.gameEntities;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.graphics.Camera;
import com.mygdx.game.ai.AIbehaviours.AIBehaviour;
import com.mygdx.game.entities.entityInterfaces.CollectibleEffect;
import com.mygdx.game.entities.dynamicEntities.CollectibleAIEntity;

public class CollectibleCarrotEntity extends CollectibleAIEntity implements CollectibleEffect<PlayerEntity> {
    // This class represents a specific type of collectible: a carrot.
    private float cameraWidthIncrease = 200f; // The amount to increase the camera width
    private float cameraAheadIncrease = 500f; // The amount to increase the camera width

    private float effectDuration = 20f; // How long the effect should last
    private Camera cameraManager;

    public CollectibleCarrotEntity(String texturePath, float width, float height, float x, float y, float speed, AIBehaviour behaviour, Camera camera) {
        super(texturePath, width, height, x, y, speed, behaviour);
        this.cameraManager = camera;
    }
    @Override
    public void applyEffect(PlayerEntity player) {
        Vector3 focusPoint = new Vector3(player.getX()+ cameraAheadIncrease, player.getY(), 0); // The point to focus on when zooming
        cameraManager.startCameraWidthTransition(cameraWidthIncrease, effectDuration, focusPoint);
    }
    // If coins have specific behaviors or effects when collected, override the relevant methods her
}


