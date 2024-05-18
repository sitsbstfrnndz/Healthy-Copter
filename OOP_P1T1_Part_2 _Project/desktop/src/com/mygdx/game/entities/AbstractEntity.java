package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.scenes.SceneType;
import com.mygdx.game.scenes.SceneManager;
import com.mygdx.game.collision.Collidable;
import com.mygdx.game.collision.IBoundaryConstraint;
import com.mygdx.game.entities.gameEntities.PlayerEntity;

import static com.mygdx.game.scenes.GameLevelScene.playAreaHeight;

public abstract class AbstractEntity implements IBoundaryConstraint {
    private float x,y;
    private float width,height;
    private Texture texture; // Add texture attribute
    private boolean visible = true; // Visibility flag, default to true
    protected SceneManager sceneManager;


    public AbstractEntity(SceneManager sceneManager)// Overloading sees data type and chooses method based on data type (Nothing)
    {
        this.sceneManager = sceneManager;
    }

    public AbstractEntity(String texturePath, float width, float height, float x, float y) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if (texturePath != null && !texturePath.isEmpty()) {
            this.texture = new Texture(texturePath); // Load texture
        }
    }
    // Texture management
    public void setTexture(String texturePath) {
        if (this.texture != null) {
            this.texture.dispose(); // Dispose the old texture if exists
        }
        this.texture = new Texture(texturePath); // Load new texture
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setX(float x)
    {
        this.x = x;
    }
    public void setY(float y)
    {
        this.y = y;
    }
    public float getX()
    {
        return this.x;
    }
    public float getY()
    {
        return this.y;
    }
    public void draw(SpriteBatch batch) {
        if (texture != null) {
            batch.draw(texture, x, y, width, height);
        }
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public abstract void update(); // Entity's abstract method, therefore instantiated children must implement update method

    public void movement()
    {
        //do nothing implementation
    }

    public void dispose(){
        texture.dispose();
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void scaleSize(float scaleX, float scaleY) {
        this.width *= scaleX;
        this.height *= scaleY;
    }

    public void applyBoundaryConstraints(float screenWidth, float screenHeight) {
        // Ensure the entity is within the horizontal bounds of the screen
        if (x < 0) x = 0;
        else if (x + width > screenWidth) x = screenWidth - width;

        // Ensure the entity is within the vertical bounds of the PLAY AREA, not the entire screen
        if (y < 0) y = 0;
        else if (y + height > playAreaHeight) y = playAreaHeight - height; // Use playAreaHeight here
    }


    // Visibility control methods
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void onCollision(Collidable other) {
        // Logic that determines what happens when a collectible collides with another object.
        // For example, if it collides with the player, it might increase score or provide a power-up.
        if (other instanceof PlayerEntity) {
            sceneManager.transitionTo(SceneType.GAME_END, 2);
        }
    }
}
