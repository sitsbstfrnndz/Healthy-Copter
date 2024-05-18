package com.mygdx.game.entities.staticEntities;

import com.mygdx.game.entities.AbstractEntity;

public class StaticEntity extends AbstractEntity {
    public StaticEntity( String texturePath, float width, float height, float x, float y) {
        super(texturePath, width, height, x, y);
    }

    @Override
    public void update() {
        // Implementation for static entities, if needed
        /*
        If static entities might eventually need specific update behaviors
        (e.g., responding to environmental changes without moving),
        consider keeping the override of update but implement those behaviors as needed.
         */
    }

    @Override
    public void applyBoundaryConstraints(AbstractEntity entity) {

    }
}
