package gameObjects;
import org.newdawn.slick.SlickException;

/**
 * A simple hazardous vehicle
 */
public class Bus extends MovingObject {

	// Source for bus graphics
	private static final String IMAGESRC = "assets/bus.png";

	// The speed of the bus in pixels per millisecond
	private static final float SPEED = (float) 0.15;

	/**
	 * Constructor for the Bus class
	 * 
	 * @param x         The x coordinate of the object
	 * @param y         The y coordinate of the object
	 * @param direction The direction of the object's movement
	 * @throws SlickException
	 */
	public Bus(float x, float y, Boolean direction) throws SlickException {
		super(IMAGESRC, x, y, direction);
		this.setHazard(true);
		this.setSpeed(SPEED);
	}

	/**
	 * Updates the position of the bus @ delta The time elapsed since the last
	 * update (milliseconds)
	 */
	public void update(int delta) {
		this.move(delta);
		this.Offscreen();
	}
}
