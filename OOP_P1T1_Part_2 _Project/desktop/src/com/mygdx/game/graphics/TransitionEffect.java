package com.mygdx.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TransitionEffect {
    private float alpha = 0;
    private boolean isTransitioning = false;
    private float transitionDuration;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public void startTransition(float duration) {
        this.transitionDuration = duration;
        this.isTransitioning = true;
        this.alpha = 0; // Reset alpha at the start of a transition
    }

    public boolean update(float deltaTime) {
        if (!isTransitioning) return false;

        alpha += deltaTime / transitionDuration;
        alpha = Math.min(alpha, 1); // Clamp alpha to not exceed 1
        if (alpha >= 1) {
            isTransitioning = false;
        }
        return isTransitioning;
    }

    public void render() {
        if (!isTransitioning) return;
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, alpha);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public boolean isTransitioning() {
        return isTransitioning;
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
