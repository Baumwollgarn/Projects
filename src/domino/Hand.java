package domino;

import battleship.Board;

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
            tile.printTile();
        }
    }

    public ArrayList<Tile> getHand() {
        return this.hand;
    }

    public int getIndexOf6Double() {
        for (int i = 0; i < this.hand.size(); i++) {
            if (this.hand.get(i).isDouble() && this.hand.get(i).getLeft() == 6) {
                return i;
            }
        }
        return 0;
    }
}
