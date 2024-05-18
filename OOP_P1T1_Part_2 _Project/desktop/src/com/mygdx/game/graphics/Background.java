package com.mygdx.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private Texture texture;
    private float x1, x2, x3; // Use three instances for smoother transition
    public float scrollSpeed; // Speed at which the background scrolls
    private final float acceleration = 5f; // Change this value to control how quickly the speed increases
    public float duration, decceleration;
    private float textureWidth; // Width of the texture



    public Background(String texturePath, float speed) {
        this.texture = new Texture(texturePath);
        this.scrollSpeed = speed;
        this.textureWidth = Gdx.graphics.getWidth(); // Assuming the texture spans the width of the screen
        this.x1 = 0;
        this.x2 = textureWidth;
        this.x3 = textureWidth * 2; // Initialize the third instance off-screen to the right
        System.out.println("Initial coordinates - x1: " + x1 + ", x2: " + x2 + ", x3: " + x3);
    }
    public void update(float deltaTime) {

        // Increase the scroll speed over time

        if (duration > 0) {
            scrollSpeed += decceleration * deltaTime; // Decrease speed
            duration -= deltaTime; // Decrease duration
            if (scrollSpeed < 0) scrollSpeed = 0;
        }
        else {
            scrollSpeed += acceleration * deltaTime;
        }


        x1 -= scrollSpeed * deltaTime;
        x2 -= scrollSpeed * deltaTime;
        x3 -= scrollSpeed * deltaTime;


        // Check each background position and reset if moved out of view
        if (x1 + textureWidth <= -100) x1 = x3 + textureWidth; // If x1 is out, place it after x3
        if (x2 + textureWidth <= -100) x2 = x1 + textureWidth; // If x2 is out, place it after x1
        if (x3 + textureWidth <= -100) x3 = x2 + textureWidth; // If x3 is out, place it after x2
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x1, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(texture, x2, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(texture, x3, 0, textureWidth, Gdx.graphics.getHeight());

    }

    public void dispose() {
        texture.dispose();
    }

    public float getScrollSpeed() {
        return this.scrollSpeed;
    }

    public void setScrollSpeed(float scrollSpeed){
        this.scrollSpeed = scrollSpeed;
    }

    public void slowDown(float decceleration, float duration) {
        this.decceleration = decceleration;
        this.duration = duration;
    }
}
