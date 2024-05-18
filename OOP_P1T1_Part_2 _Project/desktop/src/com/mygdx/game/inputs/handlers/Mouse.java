package com.mygdx.game.inputs.handlers;

import com.badlogic.gdx.InputProcessor;



public class Mouse implements InputProcessor {
    private boolean clicked = false;
    private int x, y;

    public boolean isClicked() {
        return clicked;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void resetClick() {
        clicked = false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // This event signifies the end of a click, drag, or similar action.
        System.out.println("Touch up at: " + screenX + ", " + screenY);
        // If you're detecting clicks on touch up, set clicked to true here.
        clicked = true;
        x = screenX;
        y = screenY;
        return true;
    }
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // This event signifies the start of a click, drag, or similar action.
        System.out.println("Touch down at: " + screenX + ", " + screenY);
        // Set clicked to true here if you're tracking the start of interactions,
        // or simply note the position for drag operations.
        // For a simple click detection, you might wait to set 'clicked' in touchUp instead.
        x = screenX;
        y = screenY;
        return true;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }



    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }
}
