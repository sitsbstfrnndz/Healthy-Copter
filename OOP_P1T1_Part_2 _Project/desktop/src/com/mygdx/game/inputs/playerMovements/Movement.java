package com.mygdx.game.inputs.playerMovements;

public class Movement {
	private MovementCallback callback;

	// Removed the speed attribute

	// Constructor
	public Movement(MovementCallback callback) {
		this.callback = callback;
	}

	public void moveLeft(float speed) {
		//callback.updatePosition(-speed, 0);
	}

	public void moveRight(float speed) {
		//callback.updatePosition(speed, 0);
	}

	public void moveUp(float speed) {
		callback.updatePosition(0, speed);
	}

	public void moveDown(float speed) {
		//callback.updatePosition(0, -speed);
	}
}
