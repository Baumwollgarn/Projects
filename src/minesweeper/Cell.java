package minesweeper;

public class Cell {
    private boolean isMine;
    private int number;
    private boolean revealed;
    private boolean flagged;

    public Cell(int x, int y) {
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

    public boolean isMine() {
        return isMine;
    }

    public void setRevealed() {
        this.revealed = true;
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
