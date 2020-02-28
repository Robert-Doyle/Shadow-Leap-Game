package gameObjects;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;

/**
 * Entity that the user controls Responds to key input Interacts with other in
 * game entities
 */
public class Player extends Sprite {

	public static final int STARTINGLIVES = 3;
	private int livesLeft;

	// Source for player image
	private static final String IMAGESRC = "assets/frog.png";

	// Starting coordinates of the player
	public static final int[] PLAYERSPAWN = { 512, 720 };

	/**
	 * Constructor for the player class
	 * 
	 * @throws SlickException
	 */
	public Player() throws SlickException {
		super(IMAGESRC, PLAYERSPAWN[0], PLAYERSPAWN[1]);
		livesLeft = STARTINGLIVES;
	}

	/**
	 * Updates the player's status based on input and sprite locations
	 * 
	 * @param input   Input into the game
	 * @param delta   Game time passed (milliseconds)
	 * @param sprites Sprites in the current level
	 */
	public void update(Input input, int delta, ArrayList<Sprite> sprites) {

		// The player's location at the start of the update
		float startingX = this.getX();
		float startingY = this.getY();

		// Check for direction key input and change player's and its bounds coordinates
		// appropriately
		// Player will move in the specified direction a distance equivalent to its
		// height or width
		if (input.isKeyPressed(Input.KEY_UP)) {
			if (this.getBound().getTop() - this.getImgHeight() > 0) {
				this.setY(this.getY() - this.getImgHeight());
				destinationChecker(sprites, startingX, startingY);
			}
		}

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			if (this.getBound().getBottom() + this.getImgHeight() < SCREEN_HEIGHT) {
				this.setY(this.getY() + this.getImgHeight());
				destinationChecker(sprites, startingX, startingY);
			}
		}

		if (input.isKeyPressed(Input.KEY_LEFT)) {
			if (this.getBound().getLeft() - this.getImgWidth() > 0) {
				this.setX(this.getX() - this.getImgWidth());
				destinationChecker(sprites, startingX, startingY);
			}
		}

		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			if (this.getBound().getRight() + this.getImgWidth() < SCREEN_WIDTH) {
				this.setX(this.getX() + this.getImgWidth());
				destinationChecker(sprites, startingX, startingY);
			}
		}
		// Die if offscreen
		if (this.isOffscreen()) {
			this.loseLife();
		}

	}

	// Returns true if the player is intersecting a solid object in the game level
	private Boolean onSolidTile(ArrayList<Sprite> sprites) {
		for (Sprite sprite : sprites) {
			if (sprite.getBound().intersects(this.getBound())) {
				return sprite.getSolid();
			}
		}
		return false;
	}

	// Checks if player location intersects a solid object, returns it to original
	// position if it does
	private void destinationChecker(ArrayList<Sprite> sprites, float oldX, float oldY) {
		if (onSolidTile(sprites)) {
			this.setX(oldX);
			this.setY(oldY);
		}
	}

	/**
	 * The player loses a life and respawns/exits the game based on the lives left
	 */
	public void loseLife() {
		livesLeft -= 1;

		// Quit if player is dead
		if (livesLeft == 0) {
			System.exit(0);
		}

		this.setX(Player.PLAYERSPAWN[0]);
		this.setY(Player.PLAYERSPAWN[1]);
	}

	/**
	 * Player gains a life
	 */
	public void gainLife() {
		livesLeft += 1;
	}

	/**
	 * Returns current lives left
	 * 
	 * @return
	 */
	public int getLives() {
		return this.livesLeft;
	}

}
