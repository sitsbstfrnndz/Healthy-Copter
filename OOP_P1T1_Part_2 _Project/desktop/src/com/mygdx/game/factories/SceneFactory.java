package com.mygdx.game.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.EntityManager;
import com.mygdx.game.graphics.ScoreManager;
import com.mygdx.game.scenes.GameLevelScene;
import com.mygdx.game.scenes.MainMenuScene;
import com.mygdx.game.scenes.GameEndScene;
import com.mygdx.game.audio.AudioManager;
import com.mygdx.game.graphics.Camera;
import com.mygdx.game.graphics.Renderer;
import com.mygdx.game.scenes.SceneManager;

public class SceneFactory {
    private AudioManager audioManager;
    private EntityManager entityManager;
    private Renderer renderer;
    private SceneManager sceneManager;
    private Camera camera1;
    private SpriteBatch spriteBatch;
    private ScoreManager scoreManager;

    public SceneFactory(AudioManager audioManager, EntityManager entityManager, Renderer renderer, SpriteBatch spriteBatch, Camera camera1, ScoreManager scoreManager) {
        this.audioManager = audioManager;
        this.entityManager = entityManager;
        this.renderer = renderer;
        this.camera1 = camera1;
        this.spriteBatch = spriteBatch;
        this.scoreManager = scoreManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public MainMenuScene createMainMenuScene() {
        return new MainMenuScene(renderer, audioManager, sceneManager);
    }

    public GameLevelScene createGameLevelScene() {
        return new GameLevelScene(entityManager, renderer, audioManager, sceneManager,spriteBatch, camera1, scoreManager);
    }

    public GameEndScene createGameEndScene() {
        return new GameEndScene(renderer, audioManager, sceneManager, scoreManager);
    }
}
