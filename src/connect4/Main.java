package connect4;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Board game = new Board();

        char[][] play = game.fillBoard();

        game.printBoard(play);


    }
}
