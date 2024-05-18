package com.mygdx.game.inputs.handlers;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.inputs.InputManager.ControlScheme;

public class KeyHandler implements InputProcessor {
    private boolean spacePressed = false; // Tracks the spacebar press state
    private KeyHandlerListener listener;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;

    // Variables for WASD key handling
    private boolean aPressed = false;
    private boolean wPressed = false;
    private boolean dPressed = false;
    private ControlScheme controlScheme = ControlScheme.ARROW_KEYS;

    //Takes a KeyHandlerListener and assigns it to an internal listener variable.
    public KeyHandler(KeyHandlerListener listener) {
        this.listener = listener;
    }

    public void setControlScheme(ControlScheme controlScheme) {
        this.controlScheme = controlScheme;
    }

    // Checks if the listener is not null and updates the boolean flags for directional keys when pressed. It also triggers listener methods based on certain key presses (Z, X, C).
    @Override
    public boolean keyDown(int keycode) {
        if (listener == null) return false;

        switch (keycode) {
            case Keys.LEFT:
                leftPressed = true;
                break;
            case Keys.RIGHT:
                rightPressed = true;
                break;
            case Keys.UP:
                upPressed = true;
                break;
            case Keys.DOWN:
                downPressed = true;
                break;
            // Handle SPACE key
            case Keys.SPACE:
                spacePressed = true;
                break;

            // Add other case statements if needed for other key events
        }
        return true;
    }


    public void update() {
        // Update key states as before
    }

    //Resets the directional flags to false when the corresponding keys are released, ensuring accurate tracking of the current state.
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.Z:
                listener.onMainMenuRequested();
                break;
            case Keys.X:
                listener.onGameLevelRequested();
                break;
            case Keys.C:
                listener.onPauseMenuRequested();
                break;
            case Keys.LEFT:
                leftPressed = false;
                break;
            case Keys.RIGHT:
                rightPressed = false;
                break;
            case Keys.UP:
                upPressed = false;
                break;
            case Keys.DOWN:
                downPressed = false;
                break;
            // Handle SPACE keys
            case Keys.SPACE:
                spacePressed = false;
                break;

        }
        return true;
    }

    //Provide public access to the state of each directional key, allowing other parts of the game to check if a movement key is pressed.
    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isSpacePressed() {
        return spacePressed;
    }
}