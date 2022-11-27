// Holds all of the rooms which make up the game space.

package src;

import src.tiles.Rock;
import src.tiles.Room;
import src.tiles.Tile;

public class Map {
    private Tile[][] mapArray;
    private int xDimensions;
    private int yDimensions;

    // Constructor
    public Map(int xDimensions, int yDimensions) {
        this.xDimensions = xDimensions;
        this.yDimensions = yDimensions;
        generateMap();
        mapArray[0][0].isPlayerPresent(true);
    }
    
    // General Methods
    private void generateMap() {
        mapArray = new Tile[][] {
            {new Room(false), new Rock(), new Rock(), new Rock()},
            {new Room(false), new Rock(), new Room(false), new Rock()},
            {new Room(false), new Room(false), new Room(false), new Room(false)},
            {new Rock(), new Rock(), new Rock(), new Room(false)}};
    }

    // Generate a map using the Recursive Backtracking algorithm
    // https://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking
    private void generateMapRecursiveBacktracking() {
        
    }

    // Generate a map using the Hunt-And-Kill Algorithm
    // https://weblog.jamisbuck.org/2011/1/24/maze-generation-hunt-and-kill-algorithm
    private void generateMapHuntAndKill() {
        
    }

    // Gets
    public Tile[][] getMap() {
        return mapArray;
    }

    // toString
    @Override
    public String toString() {
        String output = "== M A P ==\n";
        for(int y = 0; y < mapArray.length; y++) {
            for(int x = 0; x < mapArray[0].length; x++) {
                output += mapArray[y][x].getActiveIcon() + " ";
            }
            output += "\n";
        }

        return output;
    }
}
