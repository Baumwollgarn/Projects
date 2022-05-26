package battleship;

import java.util.Scanner;

public class Game {
    private static boolean gameOver;
    private Player player1;
    private Player player2;

    public Game() {
        Board board = new Board(10);
        int turn = 1;
        int player1Ships = 0;
        int player2Ships = 0;
        int player1Hits = 0;
        int player2Hits = 0;
        int player1Misses = 0;
        int player2Misses = 0;
        boolean gameOver = false;
    }

    public void setPlayer1(Player player) {
        player1 = player;
    }

    public void setPlayer2(Player player) {
        player2 = player;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    /** Method to play the game */
    public void play() {
        while (!gameOver) {
            player1.takeTurn(player2);
            printBoard(player1);
            player2.takeTurn(player1);
            printBoard(player2);
        }
    }


    /** Method to check if the game is over */
    public boolean gameOver() {
        return false;
    }

    /** Method to print the game board */
    public void printBoard(Player player) {
        if (player == player1) player1.printBoard();
        if (player == player2) player2.printBoard();
    }

    /** Method to print the game status */
    public void printStatus() {
        System.out.println("Player 1: " + player1.getName() + " has " + player1.getShipsLeft() + " ships left.");
        System.out.println("Player 2: " + player2.getName() + " has " + player2.getShipsLeft() + " ships left.");
    }

    /** Method to start the game */
    public void start() {
        Scanner paco = new Scanner(System.in);
        System.out.println("Welcome to Battleship!");
        System.out.println("Player 1, enter your name: ");
        String name1 = paco.nextLine();
        System.out.println("Player 2, enter your name: ");
        String name2 = paco.nextLine();
        System.out.println("Player 1, enter your board size: ");
        int size1 = paco.nextInt();
        System.out.println("Player 2, enter your board size: ");
        int size2 = paco.nextInt();
        player1 = new Player(name1, size1);
        player2 = new Player(name2, size2);
        System.out.println("Player 1, enter your ships: ");
        player1.placeShips();
        System.out.println("Player 2, enter your ships: ");
        player2.placeShips();
        System.out.println("Game is starting!");
        play();
        if (player1.getShipsLeft() == 0) {
            System.out.println("Player 2 wins!");
        } else if (player2.getShipsLeft() == 0) {
            System.out.println("Player 1 wins!");
        } else {
            System.out.println("Game is a draw!");
        }
    }






}
