package netwar;
// NetWarWatcher.java

// written by mike slattery - mar 2007
//
// Based on FBFWatcher.java by
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* NetWarWatcher monitors the stream coming from the server
   which will contain messages that must be processed by
   the client (a NetTankWar object)

   Incoming Messages:
    ok <playerID>           -- connection accepted; include player ID
    full                    -- connection refused; server has enough players
    added <player>          -- other player added to server
    removed <player>        -- other player removed
    rocks <rock-string>     -- description of rock field
    turnL <boolean> <x> <y> <angle>
    turnR <boolean> <x> <y> <angle> -- begin or end turn
    forth <boolean> <x> <y> <angle> -- begin or end forward movement
*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class NetWarWatcher extends Thread {
	private NetTankWar ntw; // ref back to client
	private BufferedReader in;

	public NetWarWatcher(NetTankWar ntw, BufferedReader i) {
		this.ntw = ntw;
		in = i;
	}

	public void run()
	// Read server messages and act on them
	{
		String line;
		try {
			while ((line = in.readLine()) != null) {
				System.out.println("Watcher: player " + ntw.getPlayerID() + " got " + line);
				if (line.startsWith("begin")) {
					ntw.setPlayerID(0);
					ntw.sendRocks();
				} else if (line.startsWith("rocks")) {
					ntw.setPlayerID(1);
					ntw.setRocks(line.substring(6));
				} else if (line.startsWith("turn") || line.startsWith("forth"))
					ntw.processMove(line);
				else if (line.startsWith("shoot"))
					ntw.processShot(line);
				else if (line.startsWith("destroy")) {
					String words[] = line.split(" ");
					int rock = Integer.parseInt(words[2]);
					int tank = Integer.parseInt(words[4]);
					int bullet = Integer.parseInt(words[6]);
					NetTankWar.bulletHitRock(rock, tank, bullet);
				} 
				else if(line.startsWith("hit"))
					NetTankWar.tankHit(1 - ntw.playerID);
				else // anything else
					System.out.println("ERR: " + line + "\n");
			}
		} catch (Exception e) // socket closure will cause termination of while
		{
			System.out.println("NetWarWatcher: Socket closed");
		}
	} // end of run()

} // end of NetWarWatcher
