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

    public boolean isLeftDouble() {
        return left == right;
    }

    public boolean isRightDouble() {
        return left == right;
    }

    public boolean isLeftDouble(int left) {
        return left == right;
    }

    public boolean isRightDouble(int right) {
        return left == right;
    }

    public boolean isLeftDouble(Tile tile) {
        return left == right;
    }

    public boolean isRightDouble(Tile tile) {
        return left == right;
    }

    public boolean isLeftDouble(int left, int right) {
        return left == right;
    }
}