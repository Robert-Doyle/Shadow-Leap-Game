package gameObjects;
import org.newdawn.slick.SlickException;

/**
 * A simple rideable object
 */
public class Log extends RideableObject {

	private static final String IMAGESRC = "assets/log.png";
	private static final float SPEED = (float) 0.1;

	/**
	 * Constructor for the lLg class
	 * 
	 * @param x         The x coordinate of the object
	 * @param y         The y coordinate of the object
	 * @param direction The direction of the object's movement
	 * @throws SlickException
	 */
	public Log(float x, float y, Boolean direction) throws SlickException {
		super(IMAGESRC, x, y, direction);
		this.setSpeed(SPEED);
	}

	/**
	 * Updates the position of the log @ delta The time elapsed since the last
	 * update (milliseconds)
	 */
	public void update(int delta) {
		this.move(delta);
		this.Offscreen();
	}
}
