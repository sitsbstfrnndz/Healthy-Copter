package com.mygdx.game.collision;

import com.mygdx.game.entities.AbstractEntity;

public interface IBoundaryConstraint {
    void applyBoundaryConstraints(AbstractEntity entity);
}
