package connect4;

public class Main {
    public static void main(String[] args) {

        Board game = new Board();

        char[][] play = game.fillBoard();

        game.playGame();

    }
}
