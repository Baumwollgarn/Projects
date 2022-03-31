package connect4;

import java.util.Arrays;

public class Board {
    private char[][] board;
    private final char[] PLAYERS = {'Y', 'R'};

    //private int lastCol = -1, lastRow = -1;

    private final int width = 7, height = 6;

    public char[][] fillBoard() {
        board = new char[width][height];

        for (int i = 0; i < height; i++) {
            Arrays.fill(board[i] = new char[width], '.');
        }
        return board;
    }

    public void printBoard(char[][] board) {
        int col = board.length;
        int row = board[0].length;

        for (int r = 0; r < row ; r++) {
            for (char[] chars : board) {
                System.out.println(chars[r]);
            }
        }
    }
}