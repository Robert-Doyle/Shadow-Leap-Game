package gameObjects;
import org.newdawn.slick.SlickException;

/**
 * Long but slow rideable object
 */
public class Longlog extends RideableObject {

	private static final String IMAGESRC = "assets/longlog.png";
	private static final float SPEED = (float) 0.07;

	/**
	 * Constructor for the Longlog class
	 * 
	 * @param x         The x coordinate of the object
	 * @param y         The y coordinate of the object
	 * @param direction The direction of the object's movement
	 * @throws SlickException
	 */
	public Longlog(float x, float y, Boolean direction) throws SlickException {
		super(IMAGESRC, x, y, direction);
		this.setSpeed(SPEED);
	}

	/**
	 * Updates the position of the Longlog @ delta The time elapsed since the last
	 * update (milliseconds)
	 */
	public void update(int delta) {
		this.move(delta);
		this.Offscreen();
	}
}
