package com.mygdx.game.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ai.AIManager;
import com.mygdx.game.audio.AudioManager;
import com.mygdx.game.collision.CollisionManager;
import com.mygdx.game.entities.EntityManager;
import com.mygdx.game.graphics.Camera;
import com.mygdx.game.graphics.ScoreManager;
import com.mygdx.game.inputs.InputManager;
import com.mygdx.game.inputs.playerMovements.PlayerMovementManager;
import com.mygdx.game.graphics.Renderer;
import com.mygdx.game.factories.SceneFactory;
import com.mygdx.game.scenes.SceneManager;
import com.mygdx.game.scenes.SceneType;

public class GameMaster extends ApplicationAdapter {
    private AudioManager audioManager;
    private EntityManager entityManager;
    private SpriteBatch spriteBatch;
    private Renderer renderer;
    private SceneFactory sceneFactory;
    private SceneManager sceneManager;
    private ScoreManager scoreManager;
    private AIManager aiManager;
    private CollisionManager collisionManager;
    private PlayerMovementManager playerMovementManager;
    private InputManager inputManager;
    private int width, height;
    private Camera camera1;

    public GameMaster(int width, int height){
        this.width = width;
        this.height = height;
    }
    @Override
    public void create() {
        camera1 = new Camera(width, height);
        audioManager = AudioManager.getInstance();// Singleton
        aiManager = new AIManager();
        scoreManager = new ScoreManager();
        collisionManager = new CollisionManager(width, height, sceneManager);
        entityManager = new EntityManager(aiManager, collisionManager);
        spriteBatch = new SpriteBatch();
        renderer = new Renderer(spriteBatch); //  Renderer is set up to accept a SpriteBatch
        sceneFactory = new SceneFactory(audioManager, entityManager, renderer, spriteBatch, camera1, scoreManager);
        // Initialize SceneManager with SceneFactory
        sceneManager = new SceneManager();
        sceneFactory.setSceneManager(sceneManager); // Set SceneManager in SceneFactory
        sceneManager.setSceneFactory(sceneFactory);

        playerMovementManager = new PlayerMovementManager(entityManager); // Movement manager for player

        // Instantiate UIManager first with a temporary or null InputManager if necessary
        // UIManager's dependency on InputManager might need to be adjusted or temporarily set to null

        // Instantiate InputManager with all its dependencies, including the now instantiated UIManager
        inputManager = new InputManager(sceneManager, playerMovementManager);

        // Now that InputManager is correctly instantiated, set it in UIManager if UIManager needs it

        // Initialize scenes after all managers are set up
        sceneManager.initializeScenes();
        sceneManager.setCurrentScene(SceneType.MAIN_MENU); // Start with the main menu scene

        // Setup input processing
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputManager.getMouse());
        inputMultiplexer.addProcessor(inputManager.getKeyHandler());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render() { // Called when the Application should render itself. run over and over again
        inputManager.update();


        if (sceneManager != null) {
            sceneManager.update(Gdx.graphics.getDeltaTime()); // Update the scene manager
            sceneManager.render(); // Render the scene manager
        }
        if (sceneManager != null) {
            sceneManager.update(Gdx.graphics.getDeltaTime()); // Update the scene manager
            sceneManager.render(); // Render the scene manager
        }
    }

    public void dispose() {
        // Dispose the SceneManager
        if (sceneManager != null) {
            sceneManager.dispose();
            sceneManager = null; // Help with garbage collection
        }

        // Dispose of the SpriteBatch
        if (spriteBatch != null) {
            spriteBatch.dispose();
            spriteBatch = null; // Help with garbage collection
        }

        // Dispose of the AudioManager, if it holds disposable resources
        if (audioManager != null) {
            audioManager.dispose();
            audioManager = null; // Help with garbage collection
        }

        // Assuming EntityManager holds disposable resources
        if (entityManager != null) {
            entityManager.dispose();
            entityManager = null; // Help with garbage collection
        }

        if (inputManager != null) {
        	inputManager.dispose();
        	inputManager = null; // Help with garbage collection
        }
    }
}
