package gameFrameWork;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import gameObjects.Bike;
import gameObjects.Bulldozer;
import gameObjects.Bus;
import gameObjects.GoalTile;
import gameObjects.GrassTile;
import gameObjects.Log;
import gameObjects.Longlog;
import gameObjects.Player;
import gameObjects.Racecar;
import gameObjects.RideableObject;
import gameObjects.Sprite;
import gameObjects.TreeTile;
import gameObjects.Turtles;
import gameObjects.WaterTile;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;

/**
 * Container for each individual level or realm Contains sprites for the player
 * to interact with
 */
public class Level {

	public static final int TILEDIMENSIONS = 48;
	public static final int SCREEN_WIDTH = 1024;

	public static final int[] HOLEXLOC = { 120, 312, 504, 696, 888 };
	public static final int HOLEYLOC = 48;

	private ArrayList<Sprite> sprites = new ArrayList<>();

	private int goalsFilled = 0;

	/**
	 * Constructor for a level, generates a sprite for each line in the lvl file
	 * 
	 * @param levelFile Source for the level file
	 * @throws SlickException
	 */
	public Level(String levelFile) throws SlickException {
		String[] currentLine;

		// Read the level file line by line
		try (Scanner levelReader = new Scanner(new FileReader(levelFile))) {
			while (levelReader.hasNextLine()) {

				// Initialise the entity defined in the current line and add it to sprites
				currentLine = levelReader.nextLine().split(",");
				generateSprite(currentLine);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Initialise goaltiles for the level
		for (int i = 0; i < HOLEXLOC.length; i++) {
			sprites.add(new GoalTile(HOLEXLOC[i], HOLEYLOC));
		}

	}

	// Generate a new sprite based on the input parameters
	private void generateSprite(String[] currentLine) throws SlickException {
		String currentSpriteType = currentLine[0];
		switch (currentSpriteType) {
		case "water":
			sprites.add(new WaterTile(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2])));
			break;
		case "grass":
			sprites.add(new GrassTile(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2])));
			break;
		case "tree":
			sprites.add(new TreeTile(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2])));
			break;
		case "bike":
			sprites.add(new Bike(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
					Boolean.parseBoolean(currentLine[3])));
			break;
		case "bulldozer":
			sprites.add(new Bulldozer(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
					Boolean.parseBoolean(currentLine[3])));
			break;
		case "racecar":
			sprites.add(new Racecar(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
					Boolean.parseBoolean(currentLine[3])));
			break;
		case "bus":
			sprites.add(new Bus(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
					Boolean.parseBoolean(currentLine[3])));
			break;
		case "log":
			sprites.add(new Log(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
					Boolean.parseBoolean(currentLine[3])));
			break;
		case "longLog":
			sprites.add(new Longlog(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
					Boolean.parseBoolean(currentLine[3])));
			break;
		case "turtle":
			sprites.add(new Turtles(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
					Boolean.parseBoolean(currentLine[3])));
			break;
		}
	}

	/**
	 * Render all sprites contained within the level
	 */
	public void render() {
		for (Sprite sprite : sprites) {
			sprite.render();
		}
	}

	/**
	 * Updates the sprites in the game and resolves interactions with the player
	 * 
	 * @param input  The current input into the game
	 * @param delta  The time elapsed since the last update (in milliseconds)
	 * @param player The player object in the level
	 */
	public void update(Input input, int delta, Player player) {
		updateSprites(sprites, input, delta, player);
		player.update(input, delta, sprites);
	}

	// Updates the sprites provided as input
	private void updateSprites(ArrayList<Sprite> sprites, Input input, int delta, Player player) {

		for (Sprite sprite : sprites) {

			// Carry player if they're on a rideable object
			if (sprite instanceof RideableObject) {
				if (sprite.getBound().intersects(player.getBound())) {
					((RideableObject) sprite).carrySprite(delta, player);
				}
			}
			sprite.update(delta);
			if (sprite.getBound().intersects(player.getBound())) {
				collision(player, sprite);
			}
		}
	}

	// Resolve an individual collision between the player and an object
	private void collision(Player player, Sprite object) {

		// Check if player is riding if over water
		if (object instanceof WaterTile) {
			for (Sprite sprite : sprites) {
				if (sprite.getBound().intersects(player.getBound()) && (sprite.getRideable())) {
					return;
				}
			}
		}
		// Bulldozer pushes player
		else if (object instanceof Bulldozer) {
			((Bulldozer) object).pushPlayer(player);
		}

		// If player reaches goal
		else if (object instanceof GoalTile) {
			((GoalTile) object).fill(player);
			this.goalsFilled += 1;
		}

		object.contactSprite(player);
	}

	/**
	 * Returns the number of filled goals in the level.
	 * 
	 * @return
	 */
	public int getGoalsFilled() {
		return this.goalsFilled;
	}

	/**
	 * Returns an arraylist of the sprites in the level
	 * 
	 * @return
	 */
	public ArrayList<Sprite> getSprites() {
		return this.sprites;
	}
}
