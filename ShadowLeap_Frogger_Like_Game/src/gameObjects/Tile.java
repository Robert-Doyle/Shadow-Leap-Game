package gameObjects;
import org.newdawn.slick.SlickException;

/**
 * Basic tile class
 */
public class Tile extends Sprite {

	/**
	 * Constructor for a Tile object
	 * 
	 * @param imageSrc The location of the sprite's image file
	 * @param x        The tile's x coordinate
	 * @param y        The tile's y coordinate
	 * @throws SlickException
	 */
	public Tile(String imageSrc, float x, float y) throws SlickException {
		super(imageSrc, x, y);
	}

}
