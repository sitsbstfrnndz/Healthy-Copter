package com.mygdx.game.entities.gameEntities;

import com.mygdx.game.ai.AIbehaviours.AIBehaviour;
import com.mygdx.game.entities.entityInterfaces.CollectibleEffect;
import com.mygdx.game.entities.dynamicEntities.CollectibleAIEntity;

public class CollectibleDonutEntity extends CollectibleAIEntity implements CollectibleEffect<PlayerEntity> {
	
	// This class represents a specific type of collectible: a carrot.
	public CollectibleDonutEntity(String texturePath, float width, float height, float x, float y, float speed, AIBehaviour behaviour) {
		super(texturePath, width, height, x, y, speed, behaviour);
	}

	// If coins have specific behaviors or effects when collected, override the relevant methods her
	@Override
	public void applyEffect(PlayerEntity player) {
		player.activateTemporaryJetpackForce(450f, 5);
	}
}
