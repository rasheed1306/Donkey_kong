import bagel.*;
import java.util.Properties;
import bagel.util.Point;

public class Player  {
    private final Image playerRight = new Image("res/mario_right.png");
    private final Image playerLeft = new Image("res/mario_left.png");
    private Point player;
    private Image picture = playerRight;

    // these are static because they should exist regardless of whether the game is being played
    private static final double STEP_SIZE = 3.5;

    public Player(Properties gameProps) {
        player = new Point(Double.parseDouble(gameProps.getProperty("mario.start.x")),Double.parseDouble(gameProps.getProperty("mario.start.y")));
    }

    protected void renderPlayer(Input input) {
        picture.draw(player.x,player.y);
        if (input.isDown(Keys.LEFT)) {
            player = new Point(player.x - STEP_SIZE, player.y);
            picture = playerLeft;
        }
        if (input.isDown(Keys.RIGHT)) {
            player = new Point(player.x + STEP_SIZE, player.y);
            picture = playerRight;
        }
        if (input.isDown(Keys.UP)) {
            player = new Point(player.x, player.y - STEP_SIZE);
        }
        if (input.isDown(Keys.DOWN)) {
            player = new Point(player.x, player.y + STEP_SIZE);
        }
    }

}
