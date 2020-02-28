package gameObjects;
import org.newdawn.slick.SlickException;

/**
 * Simple vehicle type Moves fast
 */
public class Racecar extends Vehicle {

	private static final String IMAGESRC = "assets/racecar.png";
	private static final float SPEED = (float) 0.5;

	/**
	 * Constructor for the Racecar class
	 * 
	 * @param x         The x coordinate of the object
	 * @param y         The y coordinate of the object
	 * @param direction The direction of the object's movement
	 * @throws SlickException
	 */
	public Racecar(float x, float y, Boolean direction) throws SlickException {
		super(IMAGESRC, x, y, direction);
		this.setHazard(true);
		this.setSpeed(SPEED);
	}

	/**
	 * Updates the position of the Racecar @ delta The time elapsed since the last
	 * update (milliseconds)
	 */
	public void update(int delta) {
		this.move(delta);
		this.Offscreen();
	}

}
