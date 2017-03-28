package dropper.window;

import java.util.ArrayList;

import dropper.datastructures.Level;
import dropper.entities.*;

public class Levels {

	private ArrayList<ArrayList<Coin>> coins = new ArrayList<ArrayList<Coin>>();
	private ArrayList<ArrayList<Powerup>> powers = new ArrayList<ArrayList<Powerup>>();

	private Level[] levels = new Level[] {
			new Level(new Platform[] {},
					new MovingPlatform[] {}, 
					new SpinningPlatform[] {}, 
					new Bucket[] {},
					coins.get(0), powers.get(0)),
			new Level(new Platform[] {}, 
					new MovingPlatform[] {}, 
					new SpinningPlatform[] {}, 
					new Bucket[] {},
					coins.get(1), powers.get(1)),
			new Level(new Platform[] {}, 
					new MovingPlatform[] {}, 
					new SpinningPlatform[] {}, 
					new Bucket[] {},
					coins.get(2), powers.get(2)),
			new Level(new Platform[] {}, 
					new MovingPlatform[] {}, 
					new SpinningPlatform[] {}, 
					new Bucket[] {},
					coins.get(3), powers.get(3)), };

	public Level[] getLevels() {
		return levels;
	}

	public void setLevelOne() {

	}

	public void setLevelTwo() {

	}

	public void setLevelThree() {

	}

	public void setLevelFour() {

	}

}
