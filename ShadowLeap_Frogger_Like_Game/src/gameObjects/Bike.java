package gameObjects;
import org.newdawn.slick.SlickException;

/**
 * A hazardous vehicle with unique movement mechanics
 */
public class Bike extends Vehicle {

	private static final String IMAGESRC = "assets/bike.png";
	private static final float SPEED = (float) 0.2;
	private static final int TURNPOINT1 = 24;
	private static final int TURNPOINT2 = 1000;

	/**
	 * Constructor for the bike class
	 * 
	 * @param x         The x coordinate of the object
	 * @param y         The y coordinate of the object
	 * @param direction The direction fo the object's movement
	 * @throws SlickException
	 */
	public Bike(float x, float y, Boolean direction) throws SlickException {
		super(IMAGESRC, x, y, direction);
		this.setHazard(true);
		this.setSpeed(SPEED);
	}

	/**
	 * Updates the position of the bike @ delta The time elapsed since the last
	 * update (milliseconds)
	 */
	public void update(int delta) {
		this.move(delta);

		// Reverse direction if the turn point is reached
		if (this.getX() < TURNPOINT1) {
			this.setDirection(true);
		}

		if (this.getX() > TURNPOINT2) {
			this.setDirection(false);
		}

		this.Offscreen();
	}
}
