package domino;

public class Tile {

    // Attributes
    private int left;
    private int right;

    // Constructor
    public Tile(int left, int right) {
        this.left = left;
        this.right = right;
    }

    // Getters
    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    // Setters
    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    // Methods
    public boolean isDouble() {
        return left == right;
    }

    public void printHorizontalTile(Tile tile) {
        System.out.println("*********");
        System.out.println("* " + tile.getLeft() + " | " + tile.getRight() + " *");
        System.out.println("*********");
    }

    public void printVerticalTile(Tile tile) {
        System.out.println("*****");
        System.out.println("* " + tile.getLeft() + " *");
        System.out.println("* - *");
        System.out.println("* " + tile.getRight() + " *");
        System.out.println("*****");
    }

    // Print simple tile
    public void printTile() {
        System.out.println(left + " | " + right);
    }

    public Tile get(int tileIndex) {
        return this;
    }
}