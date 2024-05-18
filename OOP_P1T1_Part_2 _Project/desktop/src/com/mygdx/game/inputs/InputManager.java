package com.mygdx.game.inputs;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.scenes.SceneManager;
import com.mygdx.game.scenes.SceneType;
import com.mygdx.game.inputs.playerMovements.PlayerMovementManager;
import com.mygdx.game.inputs.handlers.KeyHandler;
import com.mygdx.game.inputs.handlers.KeyHandlerListener;
import com.mygdx.game.inputs.handlers.Mouse;

public class InputManager implements KeyHandlerListener {
    private SceneManager sceneManager;
    private PlayerMovementManager playerMovementManager;
    private KeyHandler keyHandler;
    private Mouse mouse;
    private ControlScheme currentControlScheme = ControlScheme.ARROW_KEYS;

    public InputManager(SceneManager sceneManager, PlayerMovementManager playerMovementManager) {
        this.sceneManager = sceneManager;
        this.playerMovementManager = playerMovementManager;
        this.keyHandler = new KeyHandler(this);
        this.mouse = new Mouse();
    }

    public enum ControlScheme {
        ARROW_KEYS,
        WASD_KEYS
    }

    public void setControlScheme(ControlScheme controlScheme) {
        this.currentControlScheme = controlScheme;
        this.keyHandler.setControlScheme(controlScheme);
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public Mouse getMouse() {
        return mouse;
    }
    public void update() {
        // Handle transition to the game level scene if currently in the main menu and space is pressed
        if (keyHandler.isSpacePressed() && sceneManager.getCurrentSceneType() == SceneType.MAIN_MENU) {
            onGameLevelRequested(); // This method transitions to the game level
        }

        // Independent of the scene type, handle gameplay-related inputs
        if (sceneManager.getCurrentSceneType() == SceneType.GAME_LEVEL) {
            if (keyHandler.isSpacePressed()) {
                playerMovementManager.activateJetpack();
            } else {
                playerMovementManager.deactivateJetpack();
            }
        }

        if (sceneManager.getCurrentSceneType() == SceneType.GAME_END) {
            if (keyHandler.isSpacePressed()) {

                onMainMenuRequested(); // This method transitions to the game level
            }
        }

        // Handle mouse click input
        if (mouse.isClicked()) {
            // Convert clickY because LibGDX's y-axis is inverted compared to screen coordinates
            int convertedY = Gdx.graphics.getHeight() - mouse.getY();
            mouse.resetClick();
        }
    }



    @Override
    public void onMainMenuRequested() {
        sceneManager.transitionTo(SceneType.MAIN_MENU, 2);
    }

    @Override
    public void onGameLevelRequested() {
        sceneManager.transitionTo(SceneType.GAME_LEVEL, 2);
    }

    @Override
    public void onPauseMenuRequested() {
        sceneManager.transitionTo(SceneType.GAME_END, 2);
    }

    public void render() {
    }

    public void dispose() {
    }
}

