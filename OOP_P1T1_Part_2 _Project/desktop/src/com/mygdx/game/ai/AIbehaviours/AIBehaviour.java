package com.mygdx.game.ai.AIbehaviours;

import com.mygdx.game.entities.dynamicEntities.AIEntity;

public abstract class AIBehaviour {
    public abstract void move(AIEntity entity); // Adjust method signature

    public abstract void update(AIEntity entity); // in update as well
}