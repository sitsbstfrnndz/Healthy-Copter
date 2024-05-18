package com.mygdx.game.entities.gameEntities;

import com.mygdx.game.ai.AIbehaviours.AIBehaviour;
import com.mygdx.game.entities.entityInterfaces.CollectibleEffect;
import com.mygdx.game.entities.dynamicEntities.CollectibleAIEntity;
import com.mygdx.game.graphics.*;

public class CollectibleBroccoliEntity extends CollectibleAIEntity implements CollectibleEffect<PlayerEntity> {
	private Renderer renderer;
	private float scrollSpeed = -10f;
	private float duration = 4f;

	public CollectibleBroccoliEntity(String texturePath, float width, float height, float x, float y, float speed, AIBehaviour behaviour, Renderer renderer) {
        super(texturePath, width, height, x, y, speed, behaviour);
        this.renderer = renderer;
    }
	
	public void applyEffect(PlayerEntity Player) {
		renderer.getCurrentBackground().slowDown(scrollSpeed, duration);
	}

}
