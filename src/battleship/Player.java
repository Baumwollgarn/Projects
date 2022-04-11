package battleship;

import java.util.Scanner;

public class Player {
    private String name;
    private int score;
    private int shipsLeft;
    private int[][] board;
    private int[][] hits;
    private int[][] misses;

    public Player(String name, int size) {
        this.name = name;
        this.score = 0;
        this.shipsLeft = 5;
        this.board = new int[10][10];
        this.hits = new int[10][10];
        this.misses = new int[10][10];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getShipsLeft() {
        return shipsLeft;
    }

    public void setShipsLeft(int shipsLeft) {
        this.shipsLeft = shipsLeft;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getHits() {
        return hits;
    }

    public void setHits(int[][] hits) {
        this.hits = hits;
    }

    public int[][] getMisses() {
        return misses;
    }

    public void setMisses(int[][] misses) {
        this.misses = misses;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void addShip() {
        this.shipsLeft++;
    }

    public void removeShip() {
        this.shipsLeft--;
    }

    public void addHit(int x, int y) {
        this.hits[x][y] = 1;
    }

    public void addMiss(int x, int y) {
        this.misses[x][y] = 1;
    }

    public boolean isHit(int x, int y) {
        return this.hits[x][y] == 1;
    }

    public boolean isMiss(int x, int y) {
        return this.misses[x][y] == 1;
    }

    /** Print the board */
    public void printBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 10; j++) {
                if (this.board[i][j] == 1) {
                    System.out.print("X ");
                } else if (this.board[i][j] == 2) {
                    System.out.print("O ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    /** Method to take a shot at a coordinate */
    public boolean takeShot(int x, int y) {
        if (this.board[x][y] == 1) {
            this.addHit(x, y);
            this.removeShip();
            return true;
        } else {
            this.addMiss(x, y);
            return false;
        }
    }

    /** Method to take turn */
    public void takeTurn(Player opponent) {
        System.out.println("\n" + this.name + "'s turn");
        System.out.println("Enter coordinates to fire at: ");
        int x = Battleship.readInt("x: ");
        int y = Battleship.readInt("y: ");
        if (opponent.takeShot(x - 1, y - 1)) {
            System.out.println("Hit!");
        } else {
            System.out.println("Miss!");
        }
    }

    /** Method to place ships */
    public void placeShips() {
        Scanner julio = new Scanner(System.in);
        System.out.println("\n" + this.name + "'s turn to place ships");
        System.out.println("Enter coordinates to place ship: ");
        int x = julio.nextInt();
        int y = julio.nextInt();
        int orientation = Battleship.readInt("Orientation (1 = horizontal, 2 = vertical): ");
        if (orientation == 1) {
            for (int i = 0; i < 5; i++) {
                this.board[x - 1][y - 1] = 1;
                x++;
            }
        } else {
            for (int i = 0; i < 5; i++) {
                this.board[x - 1][y - 1] = 1;
                y++;
            }
        }
    }

    /** Method to check if all ships are sunk */
    public boolean isSunk() {
        return this.shipsLeft == 0;
    }

}


