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

    public static void printTiles(ArrayList<Tile> tiles) {
        for (Tile t : tiles) {
            if (t.isDouble()) {
                t.printHorizontalTile(t);
            }
            else {
                t.printVerticalTile(t);
            }
        }
    }
    public static void addRandomTiles(ArrayList<Tile> tiles, Hand hand) {
        for (int i = 0; i < 7; i++) {
            int randomIndex = (int) (Math.random() * tiles.size());
            hand.addTile(tiles.get(randomIndex));
            tiles.remove(randomIndex);
        }
    }

    public static void play(Hand hand, Player player) {
        ArrayList<Tile> tiles = createTiles();
        addRandomTiles(tiles, hand);
        printTiles(tiles);
    }

}
