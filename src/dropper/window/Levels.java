package dropper.window;

import java.util.ArrayList;
import java.util.Random;

import dropper.datastructures.Level;
import dropper.datastructures.Point;
import dropper.entities.*;

public class Levels {

	private static ArrayList<ArrayList<Coin>> coins = new ArrayList<ArrayList<Coin>>();
	private static ArrayList<ArrayList<Powerup>> powers = new ArrayList<ArrayList<Powerup>>();

	private static Level[] levels(ArrayList<ArrayList<Coin>> coins, ArrayList<ArrayList<Powerup>> powers) {
		return new Level[] {
			//level 1
			new Level(new Platform[] {
					//left side platforms
					//new Platform(150, 375, 50, 110, 110), 
					new Platform(150, 235, 110, 50, -110), 
					//right side platforms
					//new Platform(450, 375, -110, 50, 110), 
					new Platform(450, 385, -50, 110, -110),
					//bottom platforms
					new Platform(205, 675, 150, 50, 160), 
					//new Platform(410, 475, -90, 50, -45)
					new Platform(450, 585, 50, 110, -70),
					},
					new MovingPlatform[] {}, 
					new SpinningPlatform[] {}, 
					new Bucket[] {
							new Bucket(20, WindowSettings.HEIGHT - 30, (WindowSettings.WIDTH / 3)-30, 30, 50),
							new Bucket((WindowSettings.WIDTH / 3)+10, WindowSettings.HEIGHT - 30, (WindowSettings.WIDTH / 3)-20, 30, 100),
							new Bucket(((WindowSettings.WIDTH / 3) * 2)+10, WindowSettings.HEIGHT - 30, (WindowSettings.WIDTH / 3)-30, 30,50)
							
					},
					coins.get(0), powers.get(0)),
			//level 2
			new Level(new Platform[] {
					new Platform(50, 500, 150, 50, 45),
					new Platform(290, 370, 15, 190, 70),
					new Platform(400, 640, 140, 20, 20)
			}, 
					new MovingPlatform[] {
							 new MovingPlatform(10, 190, 70, 20, -30, new Point(WindowSettings.WIDTH - 70 - 10, 0), new Point(1, 0)),
							 //new MovingPlatform(WindowSettings.WIDTH-80, 190, -20, 70, -30, new Point(100, 0), new Point(-1, 0)),
					}, 
					new SpinningPlatform[] {}, 
					new Bucket[] {
							new Bucket(20, WindowSettings.HEIGHT - 30, (WindowSettings.WIDTH / 3)-30, 30, 75),
							new Bucket((WindowSettings.WIDTH / 3)+10, WindowSettings.HEIGHT - 30, (WindowSettings.WIDTH / 3)-20, 30, 75),
							new Bucket(((WindowSettings.WIDTH / 3) * 2)+10, WindowSettings.HEIGHT - 30, (WindowSettings.WIDTH / 3)-30, 30, 75)
					},
					coins.get(1), powers.get(1)),
			//level 3
			new Level(new Platform[] {}, 
					new MovingPlatform[] {
							new MovingPlatform(10, 690, 70, 20, -30, new Point(WindowSettings.WIDTH - 70 - 10, 0), new Point(1, 0))
					}, 
					new SpinningPlatform[] {
							new SpinningPlatform(WindowSettings.WIDTH / 2, 400, 250, 10)
					}, 
					new Bucket[] {
							new Bucket(20, WindowSettings.HEIGHT - 30, (WindowSettings.WIDTH / 3)-30, 30, 25),
							new Bucket((WindowSettings.WIDTH / 3)+10, WindowSettings.HEIGHT - 30, (WindowSettings.WIDTH / 3)-20, 30, 100),
							new Bucket(((WindowSettings.WIDTH / 3) * 2)+10, WindowSettings.HEIGHT - 30, (WindowSettings.WIDTH / 3)-30, 30, 25)
					},
					coins.get(2), powers.get(2)),
			//level 4
			new Level(new Platform[] {
					new Platform(10, 350, 20, 290, 45),
					new Platform(400, 550, 20, 290, 145),
			}, 
					new MovingPlatform[] {
					}, 
					new SpinningPlatform[] {
							new SpinningPlatform(WindowSettings.WIDTH / 2, 600, 75, 10)
					}, 
					new Bucket[] {
							new Bucket(20, WindowSettings.HEIGHT - 30, (WindowSettings.WIDTH / 3)-30, 30, 100),
							new Bucket((WindowSettings.WIDTH / 3)+10, WindowSettings.HEIGHT - 30, (WindowSettings.WIDTH / 3)-20, 30, 50),
							new Bucket(((WindowSettings.WIDTH / 3) * 2)+10, WindowSettings.HEIGHT - 30, (WindowSettings.WIDTH / 3)-30, 30, 100)
					},
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
		setLevelOne();
		return levels(coins, powers);
	}

	public static void setLevelOne() {
		for(int i = 0; i <10; i++){
			Random randx = new Random();
			Random randy = new Random();
			int xr,yr;
			xr = randx.nextInt(600 - 1 + 1) + 1;
			yr = randy.nextInt(800 - 100 + 1) + 100;
			coins.get(0).add(new Coin(xr, yr, 50));
		}
	}

	public static  void setLevelTwo() {
        for(int i = 0; i <10; i++){
            Random randx = new Random();
            Random randy = new Random();
            int xr,yr;
            xr = randx.nextInt(600 - 1 + 1) + 1;
            yr = randy.nextInt(800 - 100 + 1) + 100;
            coins.get(1).add(new Coin(xr, yr, 50));
        }
	}

	public static  void setLevelThree() {
        for(int i = 0; i <10; i++){
            Random randx = new Random();
            Random randy = new Random();
            int xr,yr;
            xr = randx.nextInt(600 - 1 + 1) + 1;
            yr = randy.nextInt(800 - 100 + 1) + 100;
            coins.get(2).add(new Coin(xr, yr, 50));
        }
	}

	public static  void setLevelFour() {
        for(int i = 0; i <10; i++){
            Random randx = new Random();
            Random randy = new Random();
            int xr,yr;
            xr = randx.nextInt(600 - 1 + 1) + 1;
            yr = randy.nextInt(800 - 100 + 1) + 100;
            coins.get(3).add(new Coin(xr, yr, 50));
        }
	}

}
