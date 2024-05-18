package com.mygdx.game.graphics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;

public class Camera {
    public OrthographicCamera camera;

    private final float SCALE = 1f;
    private float originalWidth;
    private float originalHeight;
    private Vector3 originalPosition;
    private boolean isEffectActive = false;
    private float targetWidth;
    private float targetHeight;
    // Adjust the transitionDuration to reflect just the zooming in or out phase
    private float transitionDuration;
    private float elapsedTime;
    private boolean isZoomingIn = false;
    private boolean isZoomingOut = false;
    private boolean isHoldingZoom = false; // New state to handle holding the zoom
    private final float holdDuration = 5f; // Duration to hold the zoomed state

    public Camera(float width, float height) {
        camera = new OrthographicCamera();
        originalWidth = width / SCALE;
        originalHeight = height / SCALE;
        camera.setToOrtho(false, originalWidth, originalHeight);
        originalPosition = new Vector3(camera.position);
    }

    public void startCameraWidthTransition(float widthIncrease, float duration, Vector3 focusPoint) {
        if (isEffectActive) {
            return;
        }

        isEffectActive = true;
        isZoomingIn = true;
        targetWidth = originalWidth + widthIncrease;
        targetHeight = originalHeight * (targetWidth / originalWidth);
        transitionDuration = duration; // This is now just the duration of the zoom in or out
        elapsedTime = 0;
    }
    public void reset() {
        camera.position.set(originalPosition);
        camera.zoom = SCALE;
        camera.update();
    }

    public void update(float deltaTime) {
        if (isEffectActive) {
            elapsedTime += deltaTime;

            if (isZoomingIn) {
                float progress = Math.min(1f, elapsedTime / transitionDuration);
                camera.viewportWidth = originalWidth + (targetWidth - originalWidth) * progress;
                camera.viewportHeight = originalHeight + (targetHeight - originalHeight) * progress;
                camera.viewportWidth = (float)Math.round(camera.viewportWidth);
                camera.viewportHeight = (float)Math.round(camera.viewportHeight);

                if (progress >= 1f) {
                    isZoomingIn = false;
                    isHoldingZoom = true;
                    elapsedTime = 0; // Reset the elapsed time for holding the zoom
                }
            } else if (isHoldingZoom) {
                if (elapsedTime >= holdDuration) {
                    isHoldingZoom = false;
                    isZoomingOut = true;
                    elapsedTime = 0; // Reset the elapsed time for zooming out
                }
            } else if (isZoomingOut) {
                float progress = Math.min(1f, elapsedTime / transitionDuration);
                camera.viewportWidth = targetWidth - (targetWidth - originalWidth) * progress;
                camera.viewportHeight = targetHeight - (targetHeight - originalHeight) * progress;
                camera.viewportWidth = (float)Math.round(camera.viewportWidth);
                camera.viewportHeight = (float)Math.round(camera.viewportHeight);

                if (progress >= 1f) {
                    isZoomingOut = false;
                    isEffectActive = false;
                    camera.viewportWidth = originalWidth; // Reset to the original dimensions
                    camera.viewportHeight = originalHeight;
                }
            }

            camera.update();
        }
    }
}
