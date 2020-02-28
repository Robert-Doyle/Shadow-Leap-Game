package gameObjects;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * Handles the standard attributes and methods for in game sprites
 */
public class Sprite {

	/** screen width, in pixels */
	public static final int SCREEN_WIDTH = 1024;
	/** screen height, in pixels */
	public static final int SCREEN_HEIGHT = 768;
	
	public static final int TILEDIMENSIONS = 48;

	// Coordinates for sprites placement on screen
	private float xLocation;
	private float yLocation;

	// Default solidity is not solid, solid sprites will be overwritten in
	// subclasses
	private Boolean isSolid = false;

	// Default hazard status is false, hazards will cause the player to lose a life
	// on contact
	private Boolean isHazard = false;

	// Default rideable state if false
	private Boolean isRideable = false;

	// Path to sprite's image
	private Image spriteImage;

	// Sprite's bounding box
	private BoundingBox spriteBounds;

	/**
	 * Constructor for the Sprite class
	 * 
	 * @param imagesrc  The string path to the sprite's image file
	 * @param x         The x coordinate of the object
	 * @param y         The y coordinate of the object
	 * @param direction The direction of the object's movement
	 * @throws SlickException
	 */
	public Sprite(String imageSrc, float x, float y) throws SlickException {
		this.xLocation = x;
		this.yLocation = y;
		this.spriteImage = new Image(imageSrc);
		spriteBounds = new BoundingBox(this.getImg(), x, y);
	}

	/**
	 * Updates the sprite
	 * 
	 * @param delta The time elapsed since the last update (milliseconds)
	 */
	public void update(int delta) {
	}

	/**
	 * Draws the sprite, centring it's image file on the object's x and y
	 * coordinates
	 */
	public void render() {
		spriteImage.drawCentered(xLocation, yLocation);
	}

	/**
	 * Sprites behaviour upon contacting another sprite
	 * 
	 * @param other The other sprite involved in the collision
	 */
	public void contactSprite(Sprite other) {

		// Hazard objects cause player to lose a life
		if (other instanceof Player) {
			if (isHazard) {
				((Player) other).loseLife();
			}
		}
	}

	/**
	 * Returns true if part of the sprite is offscreen
	 * 
	 * @return
	 */
	public Boolean isOffscreen() {
		if ((this.getBound().getRight() > SCREEN_WIDTH) || (this.getBound().getLeft() < 0)
				|| (this.getBound().getBottom() > SCREEN_HEIGHT) || (this.getBound().getTop() < 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if the sprite is fully offscreen
	 * 
	 * @return
	 */
	public Boolean isFullyOffscreen() {

		if ((this.getBound().getLeft() > SCREEN_WIDTH) || (this.getBound().getRight() < 0)
				|| (this.getBound().getTop() > SCREEN_HEIGHT) || (this.getBound().getBottom() < 0)) {
			return true;
		} else {
			return false;
		}
	}

	/** Getters and Setters **/

	/**
	 * Returns the x coordinate
	 * 
	 * @return
	 */
	public float getX() {
		return xLocation;
	}

	/**
	 * Returns the objects y coordinate
	 * 
	 * @return
	 */
	public float getY() {
		return yLocation;
	}

	/**
	 * Returns the sprite's image
	 * 
	 * @return
	 */
	public Image getImg() {
		return spriteImage;
	}

	/**
	 * Returns the height of the sprite's image
	 * 
	 * @return
	 */
	public int getImgHeight() {
		return spriteImage.getHeight();
	}

	/**
	 * Returns the width of the sprite's image
	 * 
	 * @return
	 */
	public int getImgWidth() {
		return spriteImage.getWidth();
	}

	/**
	 * Returns the sprite's bounding box
	 * 
	 * @return
	 */
	public BoundingBox getBound() {
		return this.spriteBounds;
	}

	/**
	 * Gets the solidity status of the sprite
	 * 
	 * @return
	 */
	public Boolean getSolid() {
		return this.isSolid;
	}

	/**
	 * Gets the hazard status of the sprite
	 * 
	 * @return
	 */
	public Boolean getHazard() {
		return this.isHazard;
	}

	/**
	 * Gets the rideable status of the sprite
	 * 
	 * @return
	 */
	public Boolean getRideable() {
		return this.isRideable;
	}

	/**
	 * Sets the sprites x coordinate
	 * 
	 * @param x The new x coordinate
	 */
	public void setX(float x) {
		xLocation = x;
		spriteBounds.setX(x);
	}

	/**
	 * Sets the sprite's y coordinate
	 * 
	 * @param y The new y coordinate
	 */
	public void setY(float y) {
		yLocation = y;
		spriteBounds.setY(y);
	}

	/**
	 * Sets the solidity of the sprite
	 * 
	 * @param solidity The new soldity status
	 */
	public void setSolid(Boolean solidity) {
		this.isSolid = solidity;
	}

	/**
	 * Sets the hazard status of the sprite
	 * 
	 * @param hazard The new hazard status
	 */
	public void setHazard(Boolean hazard) {
		this.isHazard = hazard;
	}

	/**
	 * Sets the rideability of the sprite
	 * 
	 * @param rideability The new rideability status
	 */
	public void setRideable(Boolean rideability) {
		this.isRideable = rideability;
	}
}
