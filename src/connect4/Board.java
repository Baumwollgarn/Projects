package connect4;

import java.util.Scanner;

public class Board {
    private final char[] PLAYERS = {'O', 'X'};

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

    public static void printBoard(char[][] board){
        System.out.println(" 1 2 3 4 5 6");
        for (char[] chars : board) {
            System.out.print("|");
            for (int col = 0; col < board[0].length; col++) {
                System.out.print(chars[col]);
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println(" 1 2 3 4 5 6");
        System.out.println();
    }

    /*
    Method column full
    Method inserts from below till find an empty space
     */

    /* public boolean isFull(char[][] board, int column) {
        for (int c = 0; c < board.length; c++) {
            if (board[column][c] == PLAYERS[1] || board[column][c] != PLAYERS[0]) return false;
        }
        return true;
    } */


    public boolean isFull(char[][] board, int column) {
        return board[0][column] == PLAYERS[0] || board[0][column] == PLAYERS[1];
    }

    public int returnLowest(char[][] board, int column) {
        int lowest = board[0].length;
        if (!isFull(board, column)) {
            for (int c = lowest; c > 0; c--) {
                if (board[c][column] == PLAYERS[0] || board[c][column] == PLAYERS[1]) {
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
                    if (checkHorizontal(board, r, player) || checkVertical(board, r, c, player) || checkDiagonal(board, player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkHorizontal(char[][] board, int row, char player) {
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

    public boolean checkDiagonal(char[][] board, char player) {
        for(int row = 3; row < board.length; row++){
            for(int col = 0; col < board[0].length - 3; col++){
                if (board[row][col] == player   &&
                        board[row-1][col+1] == player &&
                        board[row-2][col+2] == player &&
                        board[row-3][col+3] == player){
                    return true;
                }
            }
        }
        for(int row = 0; row < board.length - 3; row++){
            for(int col = 0; col < board[0].length - 3; col++){
                if (board[row][col] == player   &&
                        board[row+1][col+1] == player &&
                        board[row+2][col+2] == player &&
                        board[row+3][col+3] == player){
                    return true;
                }
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
        int column;
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