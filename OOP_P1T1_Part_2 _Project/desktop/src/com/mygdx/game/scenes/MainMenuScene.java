package com.mygdx.game.scenes;

import com.mygdx.game.graphics.Background;
import com.mygdx.game.graphics.Renderer;
import com.mygdx.game.audio.AudioManager;

public class MainMenuScene implements Scene {
    private Renderer renderer;
    private AudioManager audioManager;
    private SceneManager sceneManager;


    public MainMenuScene(Renderer renderer, AudioManager audioManager, SceneManager sceneManager) {
        this.renderer = renderer;
        this.audioManager = audioManager;
        this.sceneManager = sceneManager;


    }
    @Override
    public void initialize() {
        setupBackground();
        setupBackgroundMusic();
        System.out.println("MainMenuScene initialized");

    }


    public void handleClick(int x, int y) {
        sceneManager.handleClick(x, y);
        sceneManager.transitionTo(SceneType.GAME_LEVEL, 2);
    }


    @Override
    public void update(float deltaTime) {
        // Handle any animations or transitions in the menu.
    }

    private void setupBackground() {
        renderer.setCurrentBackground(new Background("textures/UI/mainMenuBackground.png",0));
    }

    private void setupBackgroundMusic(){
        audioManager.playMusic("mainMusic", true);
    }

    @Override
    public void render() {
        renderer.draw();
    }
    @Override
    public void dispose() {
        audioManager.stopMusic("mainMusic");
    }

    @Override
    public void handleInput() {
        // Detect touches or clicks on menu items to transition to other scenes.
    }

}