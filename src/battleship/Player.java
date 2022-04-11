package battleship;

public class Player {
    private String name;
    private int score;
    private int shipsLeft;
    private int[][] board;
    private int[][] hits;
    private int[][] misses;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.shipsLeft = 5;
        this.board = new int[10][10];
        this.hits = new int[10][10];
        this.misses = new int[10][10];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getShipsLeft() {
        return shipsLeft;
    }

    public void setShipsLeft(int shipsLeft) {
        this.shipsLeft = shipsLeft;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getHits() {
        return hits;
    }

    public void setHits(int[][] hits) {
        this.hits = hits;
    }

    public int[][] getMisses() {
        return misses;
    }

    public void setMisses(int[][] misses) {
        this.misses = misses;
    }


}
