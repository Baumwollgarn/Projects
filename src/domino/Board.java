package domino;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    public static ArrayList<Tile> board = new ArrayList<>();
    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<Tile> createTiles() {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 7; j++) {
                tiles.add(new Tile(i, j));
            }
        }
        return tiles;
    }

    public static void printTiles(ArrayList<Tile> tiles) {
        for (Tile t : tiles) {
            if (t.isDouble()) {
                t.printHorizontalTile(t);
            } else {
                t.printVerticalTile(t);
            }
        }
    }

    public static void addRandomTiles(ArrayList<Tile> tiles, Hand hand) {
        for (int i = 0; i < 7; i++) {
            int randomIndex = (int) (Math.random() * tiles.size());
            hand.addTile(tiles.get(randomIndex));
            tiles.remove(randomIndex);
        }
    }

    //Method to add random tiles from stack till the hand is full
    public static void addRandomTiles(ArrayList<Tile> tiles, Hand hand, int handSize) {
        for (int i = 0; i < handSize; i++) {
            int randomIndex = (int) (Math.random() * tiles.size());
            hand.addTile(tiles.get(randomIndex));
            tiles.remove(randomIndex);
        }
    }

    public static void addOneRandomTiles(ArrayList<Tile> tiles, Hand hand) {
        int randomIndex = (int) (Math.random() * tiles.size());
        hand.addTile(tiles.get(randomIndex));
        tiles.remove(randomIndex);
    }

    // Method to check every tile in the hand and check if any is playable on the board

    public static void play() {
        ArrayList<Tile> tiles = createTiles();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Hand hand1 = player1.getHand();
        Hand hand2 = player2.getHand();
        addRandomTiles(tiles, hand1);
        addRandomTiles(tiles, hand2);
        Player currentPlayer;
        Hand starter = highestTileOnHand(hand1, hand2);
        if (starter.equals(hand1)) {
            System.out.println("Player 1 starts and start withs the highest tile.");
            board.add(hand1.getHand().get(hand1.getIndexOf6Double()));
            hand1.removeTile(hand1.getHand().get(hand1.getIndexOf6Double()));
            for (Tile t : hand1.getHand()) t.printTile();
            printTiles(board);
            currentPlayer = player2;
        } else {
            System.out.println("Player 2 starts and starts with the highest tile.");
            board.add(hand2.getHand().get(hand1.getIndexOf6Double()));
            hand2.removeTile(hand2.getHand().get(hand2.getIndexOf6Double()));
            for (Tile t : hand2.getHand()) t.printTile();
            printTiles(board);
            currentPlayer = player1;
        }
        boolean gameIsOver = false;
        while (!gameIsOver) {
            System.out.println("It's " + currentPlayer.getName() + "'s turn");
            System.out.println("Your hand:");
            currentPlayer.getHand().printHand();
            System.out.println("Your board:");
            printTiles(board);
            System.out.println("Which tile do you want to play?");
            Scanner scanner = new Scanner(System.in);
            int tileIndex = scanner.nextInt();
            Tile tile = currentPlayer.getHand().getHand().get(tileIndex);
            // Check if any tile of the hand is playable and if not, remove a tile from the stack and add it to the hand
            if (!isPlayable(tile)) {
                System.out.println("You can't play this tile");
                System.out.println("You have to remove a tile from the stack");
                addOneRandomTiles(tiles, currentPlayer.getHand());
                tiles.remove(0);
                continue;
            }
            checkIfPlayedTilesMatch(tile);
            currentPlayer.getHand().removeTile(tile);

            // Check if the game is over
            if (currentPlayer.getHand().getHand().size() == 0) {
                System.out.println("Game is over");
                System.out.println("The winner is " + currentPlayer.getName());
                gameIsOver = true;
            }

        }
    }

    // Method to check if a tile is playable on the current board situation. If not, the player has to remove a tile from the stack and add it to the hand.
    public static boolean isPlayable(Tile tile) {
        if (board.size() == 0) {
            return true;
        } else if (board.get(0).getLeft() == tile.getLeft()) {
            return true;
        } else if (board.get(0).getLeft() == tile.getRight()) {
            return true;
        } else if (board.get(board.size() - 1).getRight() == tile.getLeft()) {
            return true;
        } else return board.get(board.size() - 1).getRight() == tile.getRight();
    }

    public static void switchSides(Tile tile) {
        int temp = tile.getLeft();
        tile.setLeft(tile.getRight());
        tile.setRight(temp);
    }
    public static void addTileToBoard(Tile tile) {
        // Get the left and right values of the last tile on the board
        int left = board.get(board.size() - 1).getLeft();
        int right = board.get(board.size() - 1).getRight();
        // If the tile is a double, add it to the board
        if (tile.isDouble()) {
            board.add(tile);
        }
        /* If the tile is not a dobule but is playable, add it to either the last
        position of the board or the first position, depending on the left and right values of the last tile on the board */
        else if (tile.getLeft() == left || tile.getRight() == right) {
            board.add(tile);
        } else {
            board.add(0, tile);
        }
    }

    // Method to check if played tiles number matches the number of the first or last tile on the board. If so, add the tile to the board. If not swap the numbers of the tiles.
    public static void checkIfPlayedTilesMatch(Tile tile) {
        if (board.size() == 0) {
            board.add(tile);
        } else {
            if (board.get(0).getLeft() == tile.getRight()) {
                board.add(0, tile);
            } else if (board.get(0).getLeft() == tile.getLeft()) {
                switchSides(tile);
                board.add(0, tile);
            } else if (board.get(board.size() - 1).getRight() == tile.getLeft()){
                board.add(tile);
            } else if (board.get(board.size() - 1).getRight() == tile.getRight()) {
                switchSides(tile);
                board.add(tile);
            }
        }
    }
    public static Hand highestTileOnHand(Hand hand1, Hand hand2) {
        Hand handStart = null;

        for (Tile tile : hand1.getHand()) {
            if (tile.getLeft() == 6 && tile.isDouble()) {
                handStart = hand1;
                break;
            }
            else {
                handStart = hand2;
            }
        }
        int highestTile = 0;
        for (Tile tile : hand1.getHand()) {
            if (tile.getLeft() > highestTile) {
                highestTile = tile.getLeft();
            }
        }

        for (Tile tile : hand2.getHand()) {
            if (tile.getLeft() > highestTile) {
                highestTile = tile.getLeft();
            }
        }

        // If nobody has the 6:6, return the hand with the highest tile
        if (highestTile == 0) {
            return hand1.getHand().get(0).getLeft() > hand2.getHand().get(0).getLeft() ? hand1 : hand2;
        }

        return handStart;
    }
}