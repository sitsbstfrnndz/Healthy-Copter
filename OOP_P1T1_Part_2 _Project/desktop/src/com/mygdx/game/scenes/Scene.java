package com.mygdx.game.scenes;

public interface Scene {
    void initialize(); // Initialize scene resources and setups
    void update(float deltaTime); // Update game logic, deltaTime is the time between frames
    void render(); // Render the scene
    void dispose(); // Dispose of scene resources
    void handleInput(); // (Optional) Handle user inputs
	void handleClick(int x, int y);
}