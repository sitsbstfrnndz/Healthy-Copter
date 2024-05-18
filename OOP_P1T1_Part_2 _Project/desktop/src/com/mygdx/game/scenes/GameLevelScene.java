package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.audio.AudioManager;
import com.mygdx.game.entities.*;
import com.mygdx.game.entities.dynamicEntities.AIEntity;
import com.mygdx.game.entities.gameEntities.*;
import com.mygdx.game.entities.staticEntities.StaticEntity;
import com.mygdx.game.graphics.Background;
import com.mygdx.game.graphics.Camera;
import com.mygdx.game.graphics.Renderer;
import com.mygdx.game.factories.EntityFactory;
import com.mygdx.game.graphics.ScoreManager;


import static com.mygdx.game.DesktopLauncher.height;
import static com.mygdx.game.DesktopLauncher.width;


public class GameLevelScene implements Scene {
    private final Camera camera1;
    private EntityManager entityManager;
    private Renderer renderer;
    private AudioManager audioManager;
    private Vector3 position = new Vector3();
    private SpriteBatch spriteBatch;
    private ScoreManager scoreManager;
    public static final int playAreaHeight = 480; // Play area height, smaller than total screen height
    public static final float floorHeight = 50; // Adjust based on your game's specific elevated floor height
    public static final int leftBound = width;
    private SceneManager sceneManager;
    private float popupDuration = 10f; // Duration to show the pop-up
    private float elapsedTime = 0f; // Time elapsed since the scene started
    private boolean showPopup = true; // Whether to show the pop-up
    private StaticEntity popupEntity;



    public GameLevelScene(EntityManager entityManager, Renderer renderer, AudioManager audioManager, SceneManager scenemanager, SpriteBatch spriteBatch, Camera camera1, ScoreManager scoreManager) {
        this.entityManager = entityManager;
        this.renderer = renderer;
        this.audioManager = audioManager;
        this.camera1 = camera1;
        this.spriteBatch = spriteBatch;
        this.sceneManager = scenemanager;
        this.scoreManager = scoreManager;
    }

    @Override
    public void initialize() {
        setupBackground();
        setupBackgroundMusic();
        setupEntities(); // Set up entities specific to this scene
        scoreManager.resetScore();
    }

    private void setupEntities() {
        // Initialize specific entities for the game level
        PlayerEntity player = EntityFactory.createPlayerEntity("textures/characters/player-entities/propboy.png", 50, 80, 100, 0, 200);
        entityManager.addEntity(player);

        popupEntity = EntityFactory.createStaticEntity("textures/UI/game-info.png", width - 40, height - 40, 20, 20);
        entityManager.addEntity(popupEntity);

        CollectibleBroccoliEntity collectibleBroccoliEntity = EntityFactory.createCollectibleBroccoli("textures/characters/ai-entities/powerUp-Broccoli.png", 50, 50, 3500, 55, 0, renderer);
        entityManager.addEntity(collectibleBroccoliEntity);
        CollectibleBroccoliEntity collectibleBroccoliEntity1 = EntityFactory.createCollectibleBroccoli("textures/characters/ai-entities/powerUp-Broccoli.png", 50, 50, 3500, 55, 0, renderer);
        entityManager.addEntity(collectibleBroccoliEntity1);
        CollectibleBroccoliEntity collectibleBroccoliEntity2 = EntityFactory.createCollectibleBroccoli("textures/characters/ai-entities/powerUp-Broccoli.png", 50, 50, 3500, 55, 0, renderer);
        entityManager.addEntity(collectibleBroccoliEntity2);
        CollectibleBroccoliEntity collectibleBroccoliEntity3 = EntityFactory.createCollectibleBroccoli("textures/characters/ai-entities/powerUp-Broccoli.png", 50, 50, 3500, 55, 0, renderer);
        entityManager.addEntity(collectibleBroccoliEntity3);
        CollectibleBroccoliEntity collectibleBroccoliEntity4 = EntityFactory.createCollectibleBroccoli("textures/characters/ai-entities/powerUp-Broccoli.png", 50, 50, 3500, 55, 0, renderer);
        entityManager.addEntity(collectibleBroccoliEntity4);


        CollectibleChickenEntity collectibleChickenEntity_1 = EntityFactory.createCollectibleChicken("textures/characters/ai-entities/powerDown-Chicken.png", 50, 50, 4000, 300, 0);
        entityManager.addEntity(collectibleChickenEntity_1);

        CollectibleDonutEntity collectibleDonutEntity_1 = EntityFactory.createCollectibleDonut("textures/characters/ai-entities/powerDown-Donut.png", 50, 50, 4500, 70, 0);
        entityManager.addEntity(collectibleDonutEntity_1);

        KnifeDangerousObstacleEntity knifeDangerousObstacleEntity = EntityFactory.createDangerousObstacle("textures/obstacles/knife-dangerous-obstacle.png", 50, 150, 5000, 170, 0, this.sceneManager);
        entityManager.addEntity(knifeDangerousObstacleEntity);
    }

    private void setupBackground() {
        renderer.setCurrentBackground(new Background("textures/UI/gameSceneBackground.JPEG",100));
    }

    private void setupBackgroundMusic() {
        audioManager.playMusic("gameMusic", true);
    }

    public void update(float deltaTime) {
        // Update the timer
        elapsedTime += deltaTime;

        // Remove the pop-up entity after the popupDuration has elapsed
        if (elapsedTime > popupDuration && popupEntity != null) {
            entityManager.removeEntity(popupEntity);
            popupEntity = null; // Avoid further operations on the now-removed entity
        }

        // Background scrolling speed
        float backgroundScrollSpeed = renderer.getCurrentBackground().getScrollSpeed() * deltaTime;
        scoreManager.incrementScore(backgroundScrollSpeed);

        entityManager.update();

        for (AbstractEntity entity : entityManager.getEntityList()) {
            if ((entity instanceof AIEntity)) { // Check that it's not the player entity
                entity.setX(entity.getX() - backgroundScrollSpeed); // Move entity with background

                // Instead of removing, you can:
                // 1. Wrap around to the other side of the screen
                 if (entity.getX() + entity.getWidth() < -500) {
                    entity.setX(Gdx.graphics.getWidth());
                 }
            }
        }

        // Update background and other necessary components
        if (renderer.getCurrentBackground() != null) {
            renderer.getCurrentBackground().update(deltaTime);
        }

        position.x = entityManager.getPlayerEntity().getX(); // Follow x pos of player entity
        position.y = 0;
        camera1.update(deltaTime); // Time Sens Camera
        spriteBatch.setProjectionMatrix(camera1.camera.combined);
    }

    @Override
    public void render() {
        ScreenUtils.clear(197f/255f, 223f/255f, 230f/255f, 1); // Clear the screen
        renderer.draw(entityManager.getEntityList()); // Assumes draw() does NOT call batch.begin/end
        renderer.drawScore((int)scoreManager.getScore()); // Adjust this to draw without beginning/ending the batch
        camera1.update(Gdx.graphics.getDeltaTime());
        spriteBatch.setProjectionMatrix(camera1.camera.combined);
    }

    @Override
    public void dispose() {
        entityManager.dispose(); // Dispose entities
        audioManager.stopMusic("gameMusic"); // Stop background music
    }

    @Override
    public void handleInput() {
        // Handle player inputs for game level scene
    }

    public void addEntityToScene(AbstractEntity entity) {
        entityManager.addEntity(entity); // Add an entity to the scene
    }

    public void removeEntityFromScene(AbstractEntity entity) {
        entityManager.removeEntity(entity); // Remove an entity from the scene
    }

    @Override
    public void handleClick(int x, int y) {
        // Handle click events within the game level scene
    }

}
