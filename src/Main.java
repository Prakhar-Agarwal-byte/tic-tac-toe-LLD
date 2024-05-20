import models.Game;
import models.Player;
import models.pieces.Circle;
import models.pieces.Cross;
import models.pieces.Piece;

public class Main {
    public static void main(String[] args) {
        Piece cross = new Cross();
        Piece circle = new Circle();
        Player p1 = new Player("Prakhar", cross);
        Player p2 = new Player("Prachi", circle);
        Game game = new Game(3, p1, p2);
        game.startGame();
    }
}
