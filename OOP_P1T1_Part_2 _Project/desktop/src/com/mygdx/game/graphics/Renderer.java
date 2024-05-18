package com.mygdx.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.AbstractEntity;

import java.util.List;

public class Renderer {
    private SpriteBatch batch;
    private Background currentBackground;
    private BitmapFont font;

    public Renderer(SpriteBatch batch) {
        this.batch = batch;
        this.font = new BitmapFont(); // Initialize the font
        this.font.getData().setScale(1.5f);
    }

    public void setCurrentBackground(Background background) {
        this.currentBackground = background;
    }

    public void draw() {
        batch.begin();
        if (currentBackground != null) {
            currentBackground.draw(batch);
        }
        batch.end();
    }

    public void drawTexture(Texture texture, float x, float y, float width, float height) {
        if (batch != null && texture != null) {
            batch.begin();
            batch.draw(texture, x, y, width, height);
            batch.end();
        }
    }

    public void draw(List<AbstractEntity> entities) {
        batch.begin();
        if (currentBackground != null) {
            currentBackground.draw(batch);
        }
        // Draw each entity
        for (AbstractEntity entity : entities) {
            if (entity.isVisible()) {
                entity.draw(batch);
            }
        }
        batch.end();
    }

    public void drawScore(int score) {
        String scoreText = "Score: " + score;
        float margin = 10; // Margin from the edges of the screen

        // Use GlyphLayout to measure text width
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, scoreText);
        float textWidth = layout.width;

        // Calculate the x position based on the screen width, text width, and desired margin
        float xPosition = Gdx.graphics.getWidth() - textWidth - margin;
        float yPosition = Gdx.graphics.getHeight() - margin;

        batch.begin();
        font.setColor(Color.BLACK); // Set font color to white
        font.draw(batch, scoreText, xPosition, yPosition); // Draw the score text
        font.setColor(Color.WHITE); // Reset font color to white after drawing
        batch.end();
    }



    public void drawCenteredScore(int score) {
        // Initialize GlyphLayout with the final score text
        GlyphLayout scoreLayout = new GlyphLayout();
        scoreLayout.setText(font, "Final Score: " + score);

        // Calculate the position of the score text to be centered horizontally
        float x = (Gdx.graphics.getWidth() - scoreLayout.width) / 2;
        // Position the score text lower than the middle of the screen, adjust 'yOffset' as needed
        float yOffset = 100; // This is an arbitrary value; adjust it to fit the layout of your screen
        float y = ((float) Gdx.graphics.getHeight() / 2) - yOffset;

        batch.begin();
        font.setColor(Color.WHITE); // Set font color to white
        font.draw(batch, scoreLayout, x, y);
        batch.end();
    }

    public Background getCurrentBackground(){
        return currentBackground;
    }
}
