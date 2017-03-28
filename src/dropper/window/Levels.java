package dropper.window;

import java.util.ArrayList;

import dropper.datastructures.Level;
import dropper.entities.*;

public class Levels {

	private static ArrayList<ArrayList<Coin>> coins = new ArrayList<ArrayList<Coin>>();
	private static ArrayList<ArrayList<Powerup>> powers = new ArrayList<ArrayList<Powerup>>();

	private static Level[] levels(ArrayList<ArrayList<Coin>> coins, ArrayList<ArrayList<Powerup>> powers) {
		return new Level[] {
			new Level(new Platform[] {
					//left side platforms
					new Platform(150, 375, 50, 110, 110), new Platform(150, 375, 110, 50, -110), 
					//right side platforms
					new Platform(450, 375, -110, 50, 110), new Platform(450, 375, -50, 110, -110),
					//bottom platforms
					new Platform(185, 475, 90, 50, 45), new Platform(410, 475, -90, 50, -45)
					},
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
	}

	public static Level[] getLevels() {
		ArrayList<Coin> coin1 = new ArrayList<Coin>();
		ArrayList<Coin> coin2 = new ArrayList<Coin>();
		ArrayList<Coin> coin3 = new ArrayList<Coin>();
		ArrayList<Coin> coin4 = new ArrayList<Coin>();
		coins.add(coin1);
		coins.add(coin2);
		coins.add(coin3);
		coins.add(coin4);
		ArrayList<Powerup> power1 = new ArrayList<Powerup>();
		ArrayList<Powerup> power2 = new ArrayList<Powerup>();
		ArrayList<Powerup> power3 = new ArrayList<Powerup>();
		ArrayList<Powerup> power4 = new ArrayList<Powerup>();
		powers.add(power1);
		powers.add(power2);
		powers.add(power3);
		powers.add(power4);
		return levels(coins, powers);
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
