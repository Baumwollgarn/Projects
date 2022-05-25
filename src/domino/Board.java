package domino;
import java.util.ArrayList;

public class Board {

    public static ArrayList<Tile> createTiles() {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 7; j++) {
                tiles.add(new Tile(i, j));
            }
        }
        return tiles;
    }



}
