package gameObjects;
import org.newdawn.slick.SlickException;

/**
 * A simple tile with a grass-like image
 */
public class GrassTile extends Tile {

	private static final String IMAGESRC = "assets/grass.png";

	/**
	 * Constructor for a GrassTile object
	 * 
	 * @param x The tile's x coordinate
	 * @param y The tile's y coordinate
	 * @throws SlickException
	 */
	public GrassTile(float x, float y) throws SlickException {
		super(IMAGESRC, x, y);
	}
}
