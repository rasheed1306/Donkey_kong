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
    private static final double GRAVITY = 0.2;
    private static final double TERMINAL_VELOCITY = -10;
    private static final double JUMP_VELOCITY = 5;

    private boolean isJumping = false;


    private double velocityY = 0;
    private final double groundLevel;

    public Player(Properties gameProps) {
        player = new Point(Double.parseDouble(gameProps.getProperty("mario.start.x")),Double.parseDouble(gameProps.getProperty("mario.start.y")));
        groundLevel = Double.parseDouble(gameProps.getProperty("mario.start.y"));
    }

    public void renderPlayer(Input input) {
        picture.draw(player.x,player.y);
        if (input.isDown(Keys.LEFT)) {
            player = new Point(player.x - STEP_SIZE, player.y);
            picture = playerLeft;
        }
        if (input.isDown(Keys.RIGHT)) {
            player = new Point(player.x + STEP_SIZE, player.y);
            picture = playerRight;
        }
        if (input.isDown(Keys.SPACE) && !isJumping) {
            isJumping = true;
            velocityY = JUMP_VELOCITY;
        }
        if (isJumping) {
            velocityY = Math.max(velocityY - GRAVITY, TERMINAL_VELOCITY);
            player = new Point(player.x, player.y - velocityY);

            if (player.y >= groundLevel) {
                player = new Point(player.x, groundLevel);
                velocityY = 0;
                isJumping = false;
            }
        }
    }

    public Point getPlayerPosition() {
        return player;
    }
}
