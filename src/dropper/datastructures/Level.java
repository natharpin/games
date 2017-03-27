package dropper.datastructures;

import java.util.ArrayList;

import dropper.entities.Bucket;
import dropper.entities.Coin;
import dropper.entities.MovingPlatform;
import dropper.entities.Platform;
import dropper.entities.Powerup;
import dropper.entities.SpinningPlatform;

/**
 * Simple level class to hold all the information about the level layouts
 * @author Nathan and Andrew
 *
 */
public class Level {
	
	public Level(Platform platforms[], MovingPlatform movers[], SpinningPlatform spinners[], Bucket buckets[], ArrayList<Coin> coins, ArrayList<Powerup> powerups){
		this.platforms = platforms;
		this.movers = movers;
		this.spinners = spinners;
		this.buckets = buckets;
		this.coins = coins;
		this.powerups = powerups;
	}

	public Platform platforms[];
	public MovingPlatform movers[];
	public SpinningPlatform spinners[];
	public Bucket buckets[];
	public ArrayList<Coin> coins;
	public ArrayList<Powerup> powerups;
	
}
