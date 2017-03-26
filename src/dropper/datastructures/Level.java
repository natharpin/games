package dropper.datastructures;

import java.util.ArrayList;

import dropper.entities.Bucket;
import dropper.entities.Coin;
import dropper.entities.Platform;

/**
 * Simple level class to hold all the information about the level layouts
 * @author Nathan and Andrew
 *
 */
public class Level {
	
	public Level(Platform platforms[], Bucket buckets[], ArrayList<Coin> coins){
		this.platforms = platforms;
		this.buckets = buckets;
		this.coins = coins;
	}

	public Platform platforms[];
	public Bucket buckets[];
	public ArrayList<Coin> coins;
	
}
