import bagel.*;
import java.util.Properties;
import bagel.util.*;

public class Player  {
    private final Image playerRight = new Image("res/mario_right.png");
    private final Image playerLeft = new Image("res/mario_left.png");

    private final Image playerHeldHammerRight = new Image("res/mario_hammer_right.png");
    private final Image playerHeldHammerLeft = new Image("res/mario_hammer_left.png");

    protected boolean hasHammer = false;

    private Point player;
    private Image picture = playerRight;

    // these are static because they should exist regardless of whether the game is being played
    private static final double STEP_SIZE = 3.5;
    private static final double LADDER_STEP_SIZE = 2;
    private static final double GRAVITY = 0.2;
    private static final double TERMINAL_VELOCITY = -10;
    private static final double JUMP_VELOCITY = 5;

    protected boolean isJumping = false;
    protected boolean isClimbing = false;
    private boolean moved = false;

    private double velocityY = 0;

    private final Point OriginalPosition;

    public Player(Properties gameProps) {
        player = new Point(Double.parseDouble(gameProps.getProperty("mario.start.x")),Double.parseDouble(gameProps.getProperty("mario.start.y")));
        OriginalPosition = player;
    }


    public void renderPlayer(Input input) {
        picture.draw(player.x,player.y);

        if (input.isDown(Keys.LEFT)) {
            player = new Point(Math.max(player.x - STEP_SIZE, 0), player.y);
            if (hasHammer) {
                picture = playerHeldHammerLeft;
            } else {
                picture = playerLeft;
            }
        }

        if (input.isDown(Keys.RIGHT)) {
            player = new Point(Math.min(player.x + STEP_SIZE, Window.getWidth()), player.y);
            if (hasHammer) {
                picture = playerHeldHammerRight;
            } else {
                picture = playerRight;
            }
        }

        if (input.isDown(Keys.SPACE) && !isJumping && !isClimbing) {
            isJumping = true;
            System.out.println("Jumping");
            velocityY = JUMP_VELOCITY;
        }

        if (input.isDown(Keys.SPACE) && isClimbing) {
            isJumping = false;
            player = new Point(player.x, Math.max(player.y - LADDER_STEP_SIZE,0));
            System.out.println("is climbing");
            moved = true;
        }

        if (isJumping) {
            velocityY = Math.max(velocityY - GRAVITY, TERMINAL_VELOCITY);
            player = new Point(player.x, Math.max(player.y - velocityY,0));
            isJumping = false;
        }

        if (!moved) {
            isClimbing = false;
        }

    }

    public Rectangle getPlayerBounds() {
        if (!hasHammer) {
            return new Rectangle(player.x, player.y, playerRight.getWidth(), playerRight.getHeight());
        } else {
            return new Rectangle(player.x, player.y + 10, playerRight.getWidth(), playerRight.getHeight());
        }

    }

    public void restartToStart() {
        player = OriginalPosition;
        isJumping = false;
        isClimbing = false;
        hasHammer = false;
    }
}


