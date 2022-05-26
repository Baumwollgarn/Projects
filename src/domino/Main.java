package domino;

public class Main {

    public static void main(String[] args) {
        Player Alex = new Player("Alex");
        Player Bob = new Player("Bob");
        Board.play(Alex.getHand(),Alex);
    }

}
