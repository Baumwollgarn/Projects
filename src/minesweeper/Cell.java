package minesweeper;

public class Cell {
    private int x;
    private int y;
    private boolean isMine;
    private int number;
    private boolean revealed;
    private boolean flagged;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isMine = false;
        this.number = 0;
        this.revealed = false;
        this.flagged = false;
    }

    public void setMine() {
        this.isMine = true;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Method to print the cell.
     *
     * @return
     */

    public void printCell() {

        if (this.revealed) {
            if (isMine) {
                System.out.print(" * ");
            } else {
                System.out.print(" " + number + " ");
            }
        } else if (this.flagged) {
            System.out.print(" F ");
        } else {
            System.out.print("[ ]");
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setRevealed() {
        this.revealed = true;
    }

    public void setNumberOfMines(int numberOfMines) {
        this.isMine = false;
    }

    public void setFlagged() {
        this.flagged = true;
    }

    public void setUnflagged() {
        this.flagged = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public boolean isFlagged() {
        return flagged;
    }


}
