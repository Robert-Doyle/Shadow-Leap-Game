package gameObjects;
import org.newdawn.slick.SlickException;

public class WaterTile extends Tile {

	private static final String IMAGESRC = "assets/water.png";

	/**
	 * Constructor for a WaterTile object
	 * 
	 * @param x The tile's x coordinate
	 * @param y The tile's y coordinate
	 * @throws SlickException
	 */
	public WaterTile(float x, float y) throws SlickException {
		super(IMAGESRC, x, y);
		this.setHazard(true);
	}
}
