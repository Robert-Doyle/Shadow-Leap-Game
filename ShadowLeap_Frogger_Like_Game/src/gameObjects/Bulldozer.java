package gameObjects;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * ' A non-hazardous vehicle that pushes the player
 */
public class Bulldozer extends Vehicle {

	private static final String IMAGESRC = "assets/bulldozer.png";
	private static final float SPEED = (float) 0.05;

	/**
	 * Constructor for the Bulldozer class
	 * 
	 * @param x         The x coordinate of the object
	 * @param y         The y coordinate of the object
	 * @param direction The direction of the object's movement
	 * @throws SlickException
	 */
	public Bulldozer(float x, float y, Boolean direction) throws SlickException {
		super(IMAGESRC, x, y, direction);
		this.setSolid(true);
		this.setSpeed(SPEED);
	}

	/**
	 * Moves the bulldozer and ensures that it wraps around the screen
	 * 
	 * @param delta The time elapsed since the last update
	 */
	public void update(Input input, int delta) {
		this.move(delta);

		this.Offscreen();
	}

	/**
	 * Pushes the player in the direction the bulldozer is moving
	 * 
	 * @param player The player being pushed
	 */
	public void pushPlayer(Player player) {
		if (this.getDirection()) {
			player.setX(this.getX() + this.getImgWidth());
		} else {
			player.setX(this.getX() - this.getImgWidth());
		}
	}
}
