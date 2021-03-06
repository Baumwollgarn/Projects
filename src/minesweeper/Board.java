package minesweeper;

import java.util.Scanner;

public class Board {
    /** Two-dimensional array of cells. */
    private final Cell[][] cells;
    /** Number of rows. */
    private final int rows;
    /** Number of columns. */
    private final int columns;
    /** Number of mines. */
    private final int mines;
    /** Number of cells revealed. */
    private int revealed;
    /** Number of cells flagged. */
    private final int flagged;

    /**
     * Constructor.
     * @param rows number of rows
     * @param columns number of columns
     * @param mines number of mines
     */
    public Board(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        this.cells = new Cell[rows][columns];
        this.revealed = 0;
        this.flagged = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }
        placeMines();
        calculateNumbers();
    }

    /** Method to place mines. */
    private void placeMines() {
        int minesPlaced = 0;
        while (minesPlaced < mines) {
            int row = (int) (Math.random() * rows);
            int column = (int) (Math.random() * columns);
            if (!cells[row][column].isMine()) {
                cells[row][column].setMine();
                minesPlaced++;
            }
        }
    }

    /** Method to calculate numbers. */
    private void calculateNumbers() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!cells[i][j].isMine()) {
                    int mines = 0;
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (k >= 0 && k < rows && l >= 0 && l < columns) {
                                if (cells[k][l].isMine()) {
                                    mines++;
                                }
                            }
                        }
                    }
                    cells[i][j].setNumber(mines);
                }
            }
        }
    }

    /** Method to reveal all cells. */
    private void revealAll() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].setRevealed();
            }
        }
    }

    /** Method to set revealed. */
    public void setRevealed() {
        revealed++;
    }

    /** Method to get number of cells. */
    public int getNumberOfCells() {
        return rows * columns;
    }

    /** Method to get number of mines. */
    public int getNumberOfMines() {
        return mines;
    }

    /** Method to get number of cells revealed. */
    public int getNumberOfRevealed() {
        return revealed;
    }

    /** Method to get number of cells flagged. */
    public int getNumberOfFlagged() {
        return flagged;
    }

    /** Method to get number of cells left. */
    public int getNumberOfCellsLeft() {
        return getNumberOfCells() - getNumberOfRevealed();
    }

    /** Method to get number of mines left. */
    public int getNumberOfMinesLeft() {
        return getNumberOfMines() - getNumberOfFlagged();
    }

    /** Method to print the board and each cell. */
    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].printCell();
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /** method to play game. */
    public void playGame() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper!");
        System.out.println("What do you want to do?");
        System.out.println("1. Play");
        System.out.println("2. Quit");
        int choice = input.nextInt();
        if (choice == 1) {
                int row;
                int column;
                while (getNumberOfMinesLeft() > 0) {
                    printBoard();
                    System.out.println("Enter row and column of cell: ");
                    row = input.nextInt();
                    column = input.nextInt();
                    System.out.println("What do you want to do?");
                    System.out.println("1. Reveal");
                    System.out.println("2. Flag");
                    choice = input.nextInt();
                    if (choice == 1) {
                        if (row >= 0 && row < rows && column >= 0 && column < columns) {
                            if (cells[row][column].isRevealed()) {
                                System.out.println("Cell is already revealed.");
                            } else if (cells[row][column].isFlagged()) {
                                System.out.println("Cell is flagged and can't be revealed.");
                            } else {
                                if (cells[row][column].isMine()) {
                                    System.out.println("You lose.");
                                    revealAll();
                                    printBoard();
                                    System.exit(0);
                                } else {
                                    cells[row][column].setRevealed();
                                    setRevealed();
                                    revealAllToNextNumber(column, row);
                                    if (getNumberOfCellsLeft() == getNumberOfMinesLeft()) {
                                        System.out.println("You win.");
                                        revealAll();
                                        System.exit(0);
                                    }
                                }
                            }
                        } else {
                            System.out.println("Invalid input.");
                        }
                    } else if (choice == 2) {
                        if (cells[row][column].isFlagged()) {
                            System.out.println("Cell is already flagged and will now be unflagged");
                            cells[row][column].setUnflagged();
                        } else {
                            cells[row][column].setFlagged();
                        }
                    }
                }
        }
        if (choice == 2) {
            System.out.println("Goodbye!");
            System.exit(0);
        }
    }

    /** Method to reveal all cells to next number. */
    public void revealAllToNextNumber(int column, int row) {
        if (cells[row][column].getNumber() == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column + 1; j++) {
                    if (i >= 0 && i < rows && j >= 0 && j < columns) {
                        if (!cells[i][j].isRevealed() && !cells[i][j].isFlagged()) {
                            cells[i][j].setRevealed();
                            setRevealed();
                            revealAllToNextNumber(j, i);
                        }
                    }
                }
            }
        }
    }

}
