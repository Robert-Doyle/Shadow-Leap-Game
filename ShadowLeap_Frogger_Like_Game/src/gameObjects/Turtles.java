package gameObjects;
import org.newdawn.slick.SlickException;

/**
 * Rideable platform object that submerges and resurfaces for set time periods
 */
public class Turtles extends RideableObject {

	private static final String IMAGESRC = "assets/turtles.png";
	private static final float SPEED = (float) 0.085;

	// Time is in milliseconds
	private static final int SURFACETIME = 7000;
	private static final int SUBMERGEDTIME = 2000;

	private int timeCounter = 0;

	/**
	 * Constructor for the Turtles class
	 * 
	 * @param x         The x coordinate of the object
	 * @param y         The y coordinate of the object
	 * @param direction The direction of the object's movement
	 * @throws SlickException
	 */
	public Turtles(float x, float y, Boolean direction) throws SlickException {
		super(IMAGESRC, x, y, direction);
		this.setSpeed(SPEED);
	}

	/**
	 * Updates the position of the Turtles and manages their submerging/surfacing @
	 * delta The time elapsed since the last update (milliseconds)
	 */
	public void update(int delta) {
		this.move(delta);
		this.Offscreen();
		timeCounter += delta;
		submerge();
	}

	/**
	 * Renders the turtles if they're currently rideable
	 */
	public void render() {
		if (this.getRideable()) {
			this.getImg().drawCentered(this.getX(), this.getY());
		}
	}

	// Submerges/raises the turtles after the required time
	private void submerge() {
		if (timeCounter > SURFACETIME && this.getRideable()) {
			timeCounter = 0;
			this.setRideable(false);
		} else if (timeCounter > SUBMERGEDTIME && !(this.getRideable())) {
			timeCounter = 0;
			this.setRideable(true);
		}
	}

}
