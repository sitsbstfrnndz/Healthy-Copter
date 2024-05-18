package com.mygdx.game.scenes;
import java.util.HashMap;
import java.util.Map;

import com.mygdx.game.factories.SceneFactory;
import com.mygdx.game.graphics.TransitionEffect;


public class SceneManager {
    private Map<SceneType, Scene> scenes = new HashMap<>();
    private Scene currentScene;
    private SceneType nextSceneType;
    private SceneType currentSceneType;
    private SceneFactory sceneFactory;
    private TransitionEffect transitionEffect = new TransitionEffect();

    //Constructor
    public SceneManager() {
        System.out.println("SceneManager initialized");

    }

    public void setSceneFactory(SceneFactory sceneFactory) {
        this.sceneFactory = sceneFactory;
    }

    public void initializeScenes() {
        addScene(SceneType.MAIN_MENU, sceneFactory.createMainMenuScene());
        addScene(SceneType.GAME_LEVEL, sceneFactory.createGameLevelScene());
        addScene(SceneType.GAME_END, sceneFactory.createGameEndScene());
    }

    public void addScene(SceneType type, Scene scene) {
        scenes.put(type, scene);
    }


    public void setCurrentScene(SceneType type) {
        Scene newScene = scenes.get(type);
        if (newScene == null) {
            throw new IllegalArgumentException("Scene does not exist: " + type);
        }
        if (currentScene != null) {
            currentScene.dispose();
        }
        currentSceneType = type;
        currentScene = newScene;
        currentScene.initialize();
    }

    // method for transitioning with a fade effect
    public void transitionTo(SceneType type, float duration) {
        if (!scenes.containsKey(type)) {
            System.out.println("Scene does not exist: " + type);
            return;
        }
        transitionEffect.startTransition(duration);
        nextSceneType = type;
    }

    public void handleClick(int x, int y) {
    }

    public void update(float deltaTime) {
        boolean transitioning = transitionEffect.update(deltaTime);
        if (!transitioning && nextSceneType != null) {
            System.out.println("Transition completed. Switching to scene: " + nextSceneType);
            setCurrentScene(nextSceneType);
            nextSceneType = null; // Clear nextSceneType to prevent re-setting the scene
        }

        if (currentScene != null && !transitionEffect.isTransitioning()) {
            currentScene.update(deltaTime);
        }
    }

    public SceneType getCurrentSceneType(){
        return this.currentSceneType;
    }


    public void render() {
        if (currentScene != null) {
            currentScene.render();
        }
        transitionEffect.render();
    }



    public void dispose() {
        if (currentScene != null){
            currentScene.dispose();
        }
        transitionEffect.dispose();
    }

}
