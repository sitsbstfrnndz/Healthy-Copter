package com.mygdx.game.scenes;

import com.mygdx.game.graphics.ScoreManager;
import com.mygdx.game.graphics.Background;
import com.mygdx.game.graphics.Renderer;
import com.mygdx.game.audio.AudioManager;


public class GameEndScene implements Scene {
    private  SceneManager sceneManager;
    private Renderer renderer;
    private AudioManager audioManager;
    private Background background;
    private ScoreManager scoreManager;


    public GameEndScene(Renderer renderer, AudioManager audioManager, SceneManager sceneManager, ScoreManager scoreManager) {
        this.renderer = renderer;
        this.audioManager = audioManager;
        this.sceneManager = sceneManager;
        this.scoreManager = scoreManager;
    }


    @Override
    public void initialize() {
        setupBackground();
        setupBackgroundMusic();
    }

    private void setupBackground() {
        renderer.setCurrentBackground(new Background("textures/UI/gameOverBackground.png",0));
    }

    private void setupBackgroundMusic(){
        audioManager.playMusic("mainMusic", true);
    }


    @Override
    public void update(float deltaTime) {
        // Handle logic for the pause menu, such as highlighting selected options.
    }
    @Override
    public void render() {
        // Draw the pause menu UI elements.
        renderer.draw(); // Render all entities
        renderer.drawCenteredScore((int)scoreManager.getScore());
        System.out.println("Current Screen: GameEnd Menu");
    }

    @Override
    public void dispose() {
        audioManager.stopMusic("mainMusic");
        //Clean up resources when leaving the pause menu.
    }

    @Override
    public void handleInput() {
        // Process inputs to resume the game, restart, or return to the main menu.
    }


	@Override
	public void handleClick(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
