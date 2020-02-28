package gameObjects;
import org.newdawn.slick.SlickException;

public class MovingObject extends Sprite {

	// Direction to travel in, right is TRUE, left is FALSE
	private Boolean direction;
	private float speed;

	/**
	 * Constructor for a MovingObject
	 * 
	 * @param imageSrc  the string path to the image file
	 * @param x         the x coordinate of the object
	 * @param y         the y coordinate of the object
	 * @param direction the direction of the object's movement
	 * @throws SlickException
	 */
	public MovingObject(String imageSrc, float x, float y, Boolean direction) throws SlickException {
		super(imageSrc, x, y);
		this.direction = direction;
	}

	/**
	 * Updates the object in the game
	 * 
	 * @param delta The time elapsed since the last update (milliseconds)
	 */
	public void update(int delta) {
		move(delta);
		Offscreen();
	}

	/**
	 * Moves the instance of this class
	 * 
	 * @param delta The time elapsed since the last update (milliseconds)
	 */
	public void move(int delta) {
		if (this.getDirection()) {
			this.setX(this.getX() + delta * speed);
			this.getBound().setX(this.getX() + delta * speed);
		} else {
			this.setX(this.getX() - delta * speed);
			this.getBound().setX(this.getX() - delta * speed);
		}
	}

	/**
	 * Checks if object has moved "offscreen" and if so, starts it again from the
	 * other side, "offscreen is defined as being completely out of view, they will
	 * come onscreen as though they have begun completely out of view".
	 */
	public void Offscreen() {

		// Object is moving right, so it's returned to the left side of the screen
		if (this.getDirection() & this.getBound().getLeft() > SCREEN_WIDTH) {
			this.setX(0 - this.getImgWidth() / 2);
			this.getBound().setX(0 - this.getImgWidth() / 2);
		}
		// Object going left is returned to the right side of the screen
		else if (!(getDirection()) & this.getBound().getRight() < 0) {
			this.setX(SCREEN_WIDTH + this.getImgWidth() / 2);
			this.getBound().setX(SCREEN_WIDTH + this.getImgWidth() / 2);
		}
	}

	// Getters & setters

	/**
	 * Returns the object's direction
	 * 
	 * @return
	 */
	public Boolean getDirection() {
		return direction;
	}

	/**
	 * Setter for the direction
	 * 
	 * @param direction
	 */
	public void setDirection(Boolean direction) {
		this.direction = direction;
	}

	/**
	 * Returns the objects speed.
	 * 
	 * @return
	 */
	public float getSpeed() {
		return this.speed;
	}

	/**
	 * Sets the speed of the object
	 * 
	 * @param speed
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
