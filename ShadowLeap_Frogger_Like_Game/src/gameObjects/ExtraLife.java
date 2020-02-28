package gameObjects;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;
import gameFrameWork.Level;

/**
 * Extra life that the player can collect in the game Can move on its own
 */
public class ExtraLife extends MovingObject {

	public static final int LIFESPAN = 14000;
	public static final int MOVESEPARATION = 2000;
	public static final int[] LIFESPAWNINTERVAL = { 25000, 35000 };

	private static final String IMAGESRC = "assets/extralife.png";

	private RideableObject log;
	private int timeCounter = 0;

	// Attributes for the extralive's left/right single tile movement
	private int hopCounter = 0;

	/**
	 * Constructor for an ExtraLife object
	 * 
	 * @param x     the x coordinate
	 * @param y     the y coordinate
	 * @param level the current level in which the life is spawned
	 * @throws SlickException
	 */
	public ExtraLife(RideableObject log) throws SlickException {
		super(IMAGESRC, log.getX(), log.getY(), true);
		this.log = log;
	}

	/**
	 * Updates the status of the extraLife
	 * 
	 * @param delta
	 */
	public void update(int delta) {
		timeCounter += delta;
		log.carrySprite(delta, this);
		hopManagement(delta);
	}

	/**
	 * Selects and returns a log to spawn on from the level
	 * 
	 * @param level the from which to select a log to spawn on
	 * @return
	 */
	public static RideableObject logToSpawnOn(Level level) {
		return selectLog(lifeSpawnPoints(level));
	}

	// Returns an array list of the possible objects to spawn an extralife on in a
	// level
	private static ArrayList<RideableObject> lifeSpawnPoints(Level level) {
		ArrayList<RideableObject> logs = new ArrayList<RideableObject>();

		for (Sprite sprite : level.getSprites()) {
			if (sprite instanceof Log || sprite instanceof Longlog) {
				logs.add(((RideableObject) sprite));
			}
		}

		return logs;
	}

	// Selects a random rideable object to use the coordinates of for spawning
	private static RideableObject selectLog(ArrayList<RideableObject> logs) {
		int randIndex = (int) (Math.random() * logs.size());
		RideableObject log = logs.get(randIndex);
		return log;
	}

	// Runs through the left/right hop cycle for moving on the log
	private void hopManagement(int delta) {
		hopCounter += delta;
		if (hopCounter > MOVESEPARATION) {
			hopCounter = 0;
			hop();
		}
	}

	// Hops 1 tile width to the left or right, remaining on the log
	private void hop() {
		if (this.getDirection()) {
			this.setX(this.getX() + this.TILEDIMENSIONS);
			if (this.getBound().intersects(log.getBound())) {
				return;
			} else {
				this.setDirection(false);
				this.setX(this.getX() - 2 * this.TILEDIMENSIONS);
			}
		} else {
			this.setX(this.getX() - this.TILEDIMENSIONS);
			if (this.getBound().intersects(log.getBound())) {
				return;
			} else {
				this.setDirection(true);
				this.setX(this.getX() + 2 * this.TILEDIMENSIONS);
			}
		}
	}

	/**
	 * Generates a random number between 25000 and 35000 (milliseconds)
	 * 
	 * @return
	 */
	public static float spawnTimeGenerator() {
		float spawnTime = ((float) (Math.random() * (LIFESPAWNINTERVAL[1] - LIFESPAWNINTERVAL[0])
				+ LIFESPAWNINTERVAL[0]));
		return spawnTime;
	}

	/**
	 * Returns the current total time for which this extraLife has existed
	 * 
	 * @return
	 */
	public int getTime() {
		return this.timeCounter;
	}

}
