package gameFrameWork;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import gameObjects.ExtraLife;
import gameObjects.Player;
import gameObjects.Sprite;

/**
 * The container for the game's levels and overarching player/life mechanics
 */
public class World {

	/** Hard-coded parameters **/

	/** The number of starting lives */
	public static final int STARTINGLIVES = 3;
	/** The coordinates where the player's lives are displayed */
	public static final int[] LIFECOORDINATES = { 24, 744 };
	/** The separation (in pixels) of the lives in the life display */
	public static final int LIFESEPARATION = 32;

	/** The prefix for the file path for the levels */
	public static final String LEVELLOCS = "assets/levels/";
	/** The suffix for level file paths */
	public static final String LEVELSUFFIX = ".lvl";
	/** The number of levels in this iteration of the game */
	public static final int NUMOFLEVELS = 2;

	/** The Starting coordinates of the player */
	public static final int[] PLAYERSPAWN = { 512, 720 };

	// Level to play in
	private Level level;
	// Keeps track of current level, and start with the first
	public static int levelTracker = 0;

	// Player to play as, and use in the levels
	private Player player;

	// Initialise the remaining lives sprite
	private int livesLeft;
	private Sprite hudLife = new Sprite("assets/lives.png", LIFECOORDINATES[0], LIFECOORDINATES[1]);

	// Attributes for dealing with the extraLife object.
	private ExtraLife extraLife;
	private int timeCounter;
	private float lifeSpawnTime;

	/**
	 * Initialises the world
	 * 
	 * @throws SlickException
	 */
	public World() throws SlickException {
		player = new Player();
		livesLeft = STARTINGLIVES;
		level = new Level(LEVELLOCS + levelTracker + LEVELSUFFIX);
		resetExtraLife();
	}

	/**
	 * Updates the current level, player's lives and checks if a new level needs to
	 * be loaded
	 * 
	 * @param input input from the player
	 * @param delta time elapsed (milliseconds)
	 * @throws SlickException
	 */
	public void update(Input input, int delta) throws SlickException {

		level.update(input, delta, player);
		livesLeft = player.getLives();
		if (level.getGoalsFilled() == Level.HOLEXLOC.length) {
			nextLevel();
		}

		extraLifeManagement(input, delta);

	}

	/**
	 * Renders the graphics in the level
	 * 
	 * @param g The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g) {
		level.render();
		player.render();
		// render the remaining lives
		for (int i = 0; i < livesLeft; i++) {
			hudLife.getImg().drawCentered(LIFECOORDINATES[0] + i * LIFESEPARATION, LIFECOORDINATES[1]);
		}

		if (timeCounter > lifeSpawnTime) {
			extraLife.render();
		}

	}

	/**
	 * Loads the next level
	 */
	public void nextLevel() throws SlickException {
		levelTracker += 1;
		if (levelTracker == NUMOFLEVELS) {
			System.exit(0);
		}
		level = new Level(LEVELLOCS + levelTracker + LEVELSUFFIX);
		resetExtraLife();
	}

	// Controls the generation of the extra-lives
	private void extraLifeManagement(Input input, int delta) throws SlickException {
		timeCounter += delta;

		if (timeCounter > lifeSpawnTime && extraLife == null) {
			extraLife = new ExtraLife(ExtraLife.logToSpawnOn(this.level));
		}

		if (extraLife != null) {

			extraLife.update(delta);

			if (extraLife.getTime() > ExtraLife.LIFESPAN) {
				resetExtraLife();
			}
			// Restart the extraLife process with a new random spawn time if player gets the
			// life
			else if (player.getBound().intersects(extraLife.getBound())) {
				resetExtraLife();
				player.gainLife();
			}
		}
	}

	// Destroys the extra life and resets the spawntime and time counter.
	private void resetExtraLife() {
		extraLife = null;
		timeCounter = 0;
		lifeSpawnTime = ExtraLife.spawnTimeGenerator();
	}

}
