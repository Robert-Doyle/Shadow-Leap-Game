package gameObjects;
import org.newdawn.slick.SlickException;

/**
 * Provides functionality for a moving object to carry another sprite
 */
public class RideableObject extends MovingObject {

	/**
	 * Constructor for the RideableObject class
	 * 
	 * @param imagesrc  The string path to the object's image file
	 * @param x         The x coordinate of the object
	 * @param y         The y coordinate of the object
	 * @param direction The direction of the object's movement
	 * @throws SlickException
	 */
	public RideableObject(String imageSrc, float x, float y, Boolean direction) throws SlickException {
		super(imageSrc, x, y, direction);
		this.setRideable(true);
	}

	/**
	 * Moves the player in the same direction and at the same right as the object is
	 * moving
	 * 
	 * @param delta  The elapsed game time since the game's last update call
	 * @param player The player being carried
	 */
	public void carrySprite(int delta, Player other) {
		float startingX = other.getX();

		if (this.getDirection()) {
			other.setX(other.getX() + delta * this.getSpeed());
			if (other.isOffscreen()) {
				other.setX(startingX);
			}
		} else {
			other.setX(other.getX() - delta * this.getSpeed());
			if (other.isOffscreen()) {
				other.setX(startingX);
			}
		}
	}

	/**
	 * Moves the sprite riding the object along with it
	 * 
	 * @param delta  The elapsed game time since the game's last update call
	 * @param player The sprite being carried
	 */
	public void carrySprite(int delta, Sprite other) {
		if (this.getDirection()) {
			other.setX(other.getX() + delta * this.getSpeed());
			if (this.isFullyOffscreen()) {
				((MovingObject) other).Offscreen();
			}
		} else {
			other.setX(other.getX() - delta * this.getSpeed());
			if (this.isFullyOffscreen()) {
				((MovingObject) other).Offscreen();
			}
		}
	}
}
