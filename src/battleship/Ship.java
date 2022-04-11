package battleship;

public class Ship {
    private int size;
    private int hits;
    private boolean sunk;
    private boolean horizontal;
    private int x;
    private int y;

    public Ship(int size, int x, int y, boolean horizontal) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.hits = 0;
        this.sunk = false;
    }

    public int getSize() {
        return size;
    }

    public int getHits() {
        return hits;
    }

    public boolean isSunk() {
        return sunk;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addHit() {
        this.hits++;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public void setSize(int size) {
        this.size = size;
    }


}
