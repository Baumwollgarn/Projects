package battleship;

public class Board {
    private int[][] board;
    private int size;

    public Board(int size) {
        this.size = size;
        board = new int[size][size];
    }

    public int getSize() {
        return size;
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public boolean isEmpty(int x, int y) {
        return board[x][y] == 0;
    }

    public boolean isHit(int x, int y) {
        return board[x][y] == 1;
    }


}
