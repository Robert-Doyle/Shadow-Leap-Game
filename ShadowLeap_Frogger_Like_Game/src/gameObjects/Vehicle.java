package gameObjects;
import org.newdawn.slick.SlickException;

/**
 * Class for the in-game vehicles
 */
public class Vehicle extends MovingObject {

	/**
	 * Generic constructor for a vehicle
	 * 
	 * @param imageSrc  The string source for the image file
	 * @param x         The x coordinate of the vehicle
	 * @param y         The y coordinate for the vehicle
	 * @param direction The direction of the vehicle
	 * @throws SlickException
	 */
	public Vehicle(String imageSrc, float x, float y, Boolean direction) throws SlickException {
		super(imageSrc, x, y, direction);
	}
}
