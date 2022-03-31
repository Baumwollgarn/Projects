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

        int col = board[0].length;
        int row = board.length;

        for (int r = 0; r < row ; r++) {
            for (int c = 0; c < col; c++) {
                board[r][c] = '.';
            }
        }

        return board;
    }

    public void printBoard(char[][] board) {
        int col = board[0].length;
        int row = board.length;

        for (int r = 0; r < row ; r++) {
            for (int c = 0; c < col; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println();
        }
    }

    /*public int checkLowest(char[][] board) {

        int col = board[0].length;
        int row = board.length;

        for (int r = 0 ; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[r][c] == 'Y' || board[r][c] == 'R') {
                    return r;
                }
            }
        }
        return 0;
    }*/

    /*
    Method column full
    Method insert from below till find a empty space
     */

    public boolean isFull (char[][] board, int column) {
        for (int c = 0; c < board.length; c++ ){
            if (board[column][c] == '.') return false;
        }
        return true;
    }

    public int returnLowest(char[][] board, int column) {
        int lowest = board[0].length;
        for (int c = lowest; c > 0; c--){
            if (board[c][column] == 'Y' || board[c][column] == 'R') {
                lowest = c - 1;
            }
        }
        return lowest;
    }

    public void makeTurn(char[][] board, char player) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter a column (1-6): ");
        int nextTurn = in.nextInt() - 1;
        isFull(board,nextTurn);
        board[returnLowest(board,nextTurn)][nextTurn] = player;
    }

    /** Method to check if 4 in a row */
    public boolean checkWin(char[][] board, char player) {
        int col = board.length;
        int row = board[0].length;

        for (int r = 0; r < row -1; r++) {
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


    public void play (char[][] board, char player1 , char player2) {
        player1 = PLAYERS[0];
        player2 = PLAYERS[1];

        char turn = ' ';

        while (!checkWin(board,player1) || !checkWin(board,player2) || !checkFull(board)) {
            if (turn == player1) {
                printBoard(board);
                makeTurn(board, player1);
                turn = player2;
            } else {
                printBoard(board);
                makeTurn(board, player2);
                turn = player1;
            }
        }

        System.out.println("Player " + turn + " won!");
    }

}