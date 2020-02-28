package gameObjects;
import org.newdawn.slick.SlickException;

/**
 * Solid tile with image of a tree
 */
public class TreeTile extends Tile {

	private static final String IMAGESRC = "assets/tree.png";

	/**
	 * Constructor for a TreeTile object
	 * 
	 * @param x The tile's x coordinate
	 * @param y The tile's y coordinate
	 * @throws SlickException
	 */
	public TreeTile(float x, float y) throws SlickException {
		super(IMAGESRC, x, y);
		this.setSolid(true);
	}

}
