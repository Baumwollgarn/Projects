package domino;

public class Player {

    private String name;
    private Hand hand;
    private int score;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.score = 0;
    }

    public String getName() {
        return this.name;
    }

    public Hand getHand() {
        return this.hand;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }
    public void addTile(Tile tile) {
        this.hand.addTile(tile);
    }

    public void removeTile(Tile tile) {
    }
}
