package dropper.datastructures;

import dropper.entities.Bucket;
import dropper.entities.Platform;

/**
 * Simple level class to hold all the information about the level layouts
 * @author Nathan and Andrew
 *
 */
public class Level {
	
	public Level(Platform platforms[], Bucket buckets[]){
		this.platforms = platforms;
		this.buckets = buckets;
	}

	public Platform platforms[];
	public Bucket buckets[];
	//Coin coins[];
	
}
