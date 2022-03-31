package connect4;

import java.util.Scanner;

public class Board {
    private final char[] PLAYERS = {'Y', 'R'};

    //private int lastCol = -1, lastRow = -1;

    public char[][] fillBoard() {
        int width = 7;
        int height = 6;
        char[][] board = new char[width][height];

        int col = board[0].length;
        int row = board.length;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                board[r][c] = '.';
            }
        }

        return board;
    }

    public void printBoard(char[][] board) {
        int col = board[0].length;

        for (char[] chars : board) {
            for (int c = 0; c < col; c++) {
                System.out.print(chars[c]);
            }
            System.out.println();
        }
    }

    /*
    Method column full
    Method inserts from below till find an empty space
     */

    public boolean isFull(char[][] board, int column) {
        for (int c = 0; c < board.length; c++) {
            if (board[column][c] == '.') return false;
        }
        return true;
    }

    public int returnLowest(char[][] board, int column) {
        int lowest = board[0].length;
        if (!isFull(board, column)) {
            for (int c = lowest; c > 0; c--) {
                if (board[c][column] == 'Y' || board[c][column] == 'R') {
                    lowest = c - 1;
                }
            }
        }
        return lowest;
    }

    public boolean checkWin(char[][] board, char player) {
        int col = board[0].length;
        int row = board.length;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[r][c] == player) {
                    if (checkHorizontal(board, r, c, player) || checkVertical(board, r, c, player) || checkDiagonal(board, r, c, player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkHorizontal(char[][] board, int row, int column, char player) {
        int count = 0;
        int col = board[0].length;

        for (int c = 0; c < col; c++) {
            if (board[row][c] == player) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean checkVertical(char[][] board, int row, int column, char player) {
        int count = 0;
        int rowLength = board.length;

        for (int r = row; r < rowLength; r++) {
            if (board[r][column] == player) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonal(char[][] board, int row, int column, char player) {
        int count = 0;
        int rowLength = board.length;
        int colLength = board[0].length;

        for (int r = row, c = column; r < rowLength && c < colLength; r++, c++) {
            if (board[r][c] == player) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return true;
            }
        }
        for (int r = row, c = column; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == player) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDraw(char[][] board) {
        int col = board[0].length;

        for (char[] chars : board) {
            for (int c = 0; c < col; c++) {
                if (chars[c] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public void insert(char[][] board, char player, int column) {
        int row = returnLowest(board, column);
        board[row][column] = player;
    }

    public void playGame() {
        char[][] board = fillBoard();
        Scanner scanner = new Scanner(System.in);
        int column = 0;
        char player = PLAYERS[0];

        while (!checkWin(board, player) && !checkDraw(board)) {
            printBoard(board);
            System.out.println("Player " + player + " turn");
            System.out.print("Enter column number: ");
            column = scanner.nextInt() - 1;
            if (isFull(board, column)) {
                System.out.println("Column full, try again");
            } else {
                insert(board, player, column);
                if (player == PLAYERS[0]) {
                    player = PLAYERS[1];
                } else {
                    player = PLAYERS[0];
                }
            }
        }
        printBoard(board);
        if (checkWin(board, player)) {
            System.out.println("Player " + player + " wins!");
        } else {
            System.out.println("Draw!");
        }
    }
}