package minesweeper;

import java.util.Scanner;

public class Board {
    /** Two dimensional array of cells. */
    private Cell[][] cells;
    /** Number of rows. */
    private int rows;
    /** Number of columns. */
    private int columns;
    /** Number of mines. */
    private int mines;
    /** Number of cells revealed. */
    private int revealed;
    /** Number of cells flagged. */
    private int flagged;

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

    /**
     * Method to reveal cell.
     * @param row row of cell
     * @param column column of cell
     */
    public void revealCell(int row, int column) {
        if (cells[row][column].isMine()) {
            cells[row][column].setRevealed();
            revealAll();
        } else {
            revealCell(row, column);
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

    /** Method to set flagged. */
    public void setFlagged() {
        flagged++;
    }

    /** Method to get number of rows. */
    public int getRows() {
        return rows;
    }

    /** Method to get number of columns. */
    public int getColumns() {
        return columns;
    }

    /** Method to get number of mines. */
    public int getMines() {
        return mines;
    }

    /** Method to get number of cells revealed. */
    public int getRevealed() {
        return revealed;
    }

    /** Method to get number of cells flagged. */
    public int getFlagged() {
        return flagged;
    }

    /** Method to get cell. */
    public Cell getCell(int row, int column) {
        return cells[row][column];
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

    /** Method to get number of cells left. */
    public int getNumberOfCellsLeft(int row, int column) {
        return getNumberOfCellsLeft() - getCell(row, column).getNumber();
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
        int row = 0;
        int column = 0;
        while (getNumberOfMinesLeft() > 0) {
            printBoard();
            System.out.println("Enter row and column of cell: ");
            row = input.nextInt();
            column = input.nextInt();
            if (row >= 0 && row < rows && column >= 0 && column < columns) {
                if (cells[row][column].isRevealed()) {
                    System.out.println("Cell is already revealed.");
                } else if (cells[row][column].isFlagged()) {
                    System.out.println("Cell is already flagged.");
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
                        }
                    }
                }
            } else {
                System.out.println("Invalid input.");
            }
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
