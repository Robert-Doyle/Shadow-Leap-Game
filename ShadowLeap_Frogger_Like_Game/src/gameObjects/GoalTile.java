package gameObjects;
import org.newdawn.slick.SlickException;

/**
 * The player's goal in the game Becomes solid upon contact with the player
 */
public class GoalTile extends Tile {

	private static final String IMAGESRC = "assets/frog.png";
	
	/** The Starting coordinates of the player */
	public static final int[] PLAYERSPAWN = { 512, 720 };

	/**
	 * Constructor for a GoalTile object
	 * 
	 * @param x The tile's x coordinate
	 * @param y The tile's y coordinate
	 * @throws SlickException
	 */
	public GoalTile(float x, float y) throws SlickException {
		super(IMAGESRC, x, y);
	}

	/**
	 * Displays a frog if filled, otherwise nothing
	 */
	public void render() {
		if (this.getSolid()) {
			this.getImg().drawCentered(this.getX(), this.getY());
		}
	}

	/**
	 * Fills itself if unfilled, otherwise acts as an impassable object
	 * 
	 * @param player The player that filled the tile
	 */
	public void fill(Player player) {
		this.setSolid(true);
		player.setX(PLAYERSPAWN[0]);
		player.setY(PLAYERSPAWN[1]);
	}
}
