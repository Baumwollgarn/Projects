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

    /** Method to check if 4 in a row */
    public boolean checkWin(char[][] board, char player) {
        int col = board.length;
        int row = board[0].length;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[c][r] == player) {
                    if (checkHorizontal(board, player, r, c)) {
                        return true;
                    }
                    if (checkVertical(board, player, r, c)) {
                        return true;
                    }
                    if (checkDiagonal(board, player, r, c)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Method to check horizontal */
    public boolean checkHorizontal(char[][] board, char player, int row, int col) {
        if (col + 3 < board.length) {
            if (board[col][row] == player && board[col + 1][row] == player && board[col + 2][row] == player && board[col + 3][row] == player) {
                return true;
            }
        }
        return false;
    }

    /** Method to check vertical */
    public boolean checkVertical(char[][] board, char player, int row, int col) {
        if (row + 3 < board[0].length) {
            if (board[col][row] == player && board[col][row + 1] == player && board[col][row + 2] == player && board[col][row + 3] == player) {
                return true;
            }
        }
        return false;
    }

    /** Method to check diagonal */
    public boolean checkDiagonal(char[][] board, char player, int row, int col) {
        if (row + 3 < board[0].length && col + 3 < board.length) {
            if (board[col][row] == player && board[col + 1][row + 1] == player && board[col + 2][row + 2] == player && board[col + 3][row + 3] == player) {
                return true;
            }
        }
        if (row + 3 < board[0].length && col - 3 >= 0) {
            if (board[col][row] == player && board[col - 1][row + 1] == player && board[col - 2][row + 2] == player && board[col - 3][row + 3] == player) {
                return true;
            }
        }
        return false;
    }

    /** Method to check if board is full */
    public boolean checkFull(char[][] board) {
        int col = board.length;
        int row = board[0].length;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[c][r] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    /** Method to check if the game is won */
    public boolean isWon(char[][] board) {
        int col = board.length;
        int row = board[0].length;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[c][r] == '.') {
                    return false;
                }
            }
        }
        return true;
    }


    public void play (char[][] board, char player1 , char player2) {

    }

}