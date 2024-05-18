package com.mygdx.game.ai;

import com.mygdx.game.ai.AIbehaviours.AIBehaviour;
import com.mygdx.game.entities.AbstractEntity;
import com.mygdx.game.entities.dynamicEntities.AIEntity;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Manages AI entities and their behaviors in the game.
 * Updates and assigns behaviors to AI entities based on game logic.
 */
public class AIManager {
    // Maps AI entities to their corresponding behaviors for easy access and management.
    private Map<AIEntity, AIBehaviour> behaviours = new HashMap<>();

    /**
     * Updates all AI entities based on their assigned behaviors.
     * This method should be called every game update cycle.
     *
     * @param entities The list of all entities in the game, used to extract AI entities.
     */
    public void update(List<AbstractEntity> entities) {
        // Filter AIEntities from the list of all entities.
        List<AIEntity> aiEntities = entities.stream()
                .filter(e -> e instanceof AIEntity)
                .map(e -> (AIEntity) e)
                .collect(Collectors.toList());

        // Iterate through each AI entity and update its behavior.
        for (AIEntity entity : aiEntities) {
            AIBehaviour behaviour = behaviours.get(entity);
            if (behaviour != null) {
                behaviour.update(entity); // Update the entity's state based on its behavior.
                behaviour.move(entity); // Move the entity based on its behavior.
            }
        }
    }

    /**
     * Assigns a specific behavior to an AI entity. If the entity already has a behavior assigned,
     * it will be replaced with the new one.
     *
     * @param entity The AI entity to which the behavior is to be assigned.
     * @param behaviour The behavior to assign to the entity.
     */
    public void assignBehaviour(AIEntity entity, AIBehaviour behaviour) {
        if (entity == null || behaviour == null) {
            throw new IllegalArgumentException("Neither the entity nor the behaviour can be null.");
        }
        behaviours.put(entity, behaviour); // Assign the new behavior to the entity.
    }
}
