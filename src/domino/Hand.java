package domino;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Tile> hand;

    public Hand() {
        this.hand = new ArrayList<Tile>();
    }

    public void addTile(Tile tile) {
        this.hand.add(tile);
    }

    public void removeTile(Tile tile) {
        this.hand.remove(tile);
    }

    public void printHand() {
        for (Tile tile : this.hand) {
            tile.printVerticalTile(tile);
        }
    }

}
