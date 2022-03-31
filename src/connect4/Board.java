package connect4;

import java.util.Arrays;
import java.util.Scanner;

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

        for (int r = 0; r < row - 1 ; r++) {
            for (int c = 0; c < col; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println();
        }
    }

    public int checkLowest(char[][] board) {
        /** Check lowest row for empty space */
        int col = board.length;
        int row = board[0].length;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[c][r] == '.') {
                    return r;
                }
            }
        }
        return -1;
    }

    public void makeTurn(char[][] board, char player) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter a column (1-7): ");
        int nextTurn = in.nextInt();

        board[nextTurn][checkLowest(board)] = player;

    }
}