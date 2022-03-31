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

        for (int r = 0; r < row ; r++) {
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

    /** Method to check it there are 4 in a row
     * @param board
     * @param player
     * @return
     * */
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

    public void makeTurn(char[][] board, char player) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter a column (1-6): ");
        int nextTurn = in.nextInt() - 1;
        isFull(board,nextTurn);
        board[returnLowest(board,nextTurn)][nextTurn] = player;
    }

    /* public boolean checkWin(char[][] board) {
        final int HEIGHT = board.length;
        final int WIDTH = board[0].length;
        final int EMPTY_SLOT = 0;
        for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
            for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
                char player = board[r][c];
                if (player == EMPTY_SLOT)
                    continue; // don't check empty slots

                if (c + 3 < WIDTH &&
                        player == board[r][c+1] && // look right
                        player == board[r][c+2] &&
                        player == board[r][c+3])
                    return true;
                if (r + 3 < HEIGHT) {
                    if (player == board[r+1][c] && // look up
                            player == board[r+2][c] &&
                            player == board[r+3][c])
                        return true;
                    if (c + 3 < WIDTH &&
                            player == board[r+1][c+1] && // look up & right
                            player == board[r+2][c+2] &&
                            player == board[r+3][c+3])
                        return true;
                    if (c - 3 >= 0 &&
                            player == board[r+1][c-1] && // look up & left
                            player == board[r+2][c-2] &&
                            player == board[r+3][c-3])
                        return true;
                }
            }
        }
        return false; // no winner found
    } */


    /** Method to check if board is full */
    public boolean checkFull(char[][] board) {
        int row = board[0].length;

        for (int r = 0; r < row; r++) {
            for (char[] chars : board) {
                if (chars[r] == '.') {
                    return false;
                }
            }
        }
        return true;
    }


    public void play(char[][] board) {
        char player1 = PLAYERS[0];
        char player2 = PLAYERS[1];

        char turn = ' ';

        while (!checkFull(board) || !checkWin(board,turn)) {
            printBoard(board);
            if (turn == player1) {
                makeTurn(board, player1);
                if (checkWin(board,player1)) {
                    printBoard(board);
                    System.out.println("Player " + turn + " won!");
                    break;
                }
                turn = player2;
            } else {
                makeTurn(board, player2);
                if (checkWin(board,player2)) {
                    printBoard(board);
                    System.out.println("Player " + turn + " won!");
                    break;
                }
                turn = player1;
            }
        }
    }

}