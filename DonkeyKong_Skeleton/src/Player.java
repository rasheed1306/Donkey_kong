import bagel.*;
import java.util.Properties;
import bagel.util.*;

public class Player extends GameScreenObject {

    private static final Image pictureRight = new Image("res/mario_right.png");
    private static final Image pictureLeft = new Image("res/mario_left.png");
    private static final Image pictureHammerRight = new Image("res/mario_hammer_right.png");
    private static final Image pictureHammerLeft = new Image("res/mario_hammer_left.png");

    private Point position;
    private Image picture;
    private double velocityY = 0;

    protected boolean isJumping = false;
    protected boolean isClimbing = false;


    private static final double STEP_SIZE = 3.5;
    private static final double LADDER_STEP_SIZE = 2;
    private static final double GRAVITY = 0.2;
    private static final double TERMINAL_VELOCITY = -10;
    private static final double JUMP_VELOCITY = 5;

    public Player(Properties gameProps) {
        super(pictureRight, initCoords(gameProps));
        position = objCoords[0];
        picture = pictureRight;
    }

    public static Point[] initCoords(Properties gameProps) {
        Point[] coords = new Point[1];
        coords[0] = new Point(Double.parseDouble(gameProps.getProperty("mario.start.x")), Double.parseDouble(gameProps.getProperty("mario.start.y")));
        return coords;
    }

    @Override
    public void renderObj(Input input) {
        picture.draw(position.x, position.y);

        if (input.isDown(Keys.LEFT)) {
            position = new Point(Math.max(position.x - STEP_SIZE, 0), position.y);
            if (isHammerHeld) {
                picture = pictureHammerLeft;
            } else {
                picture = pictureLeft;
            }
        }
        if (input.isDown(Keys.RIGHT)) {
            position = new Point(Math.min(position.x + STEP_SIZE, Window.getWidth()), position.y);
            if (isHammerHeld) {
                picture = pictureHammerRight;
            } else {
                picture = pictureRight;
            }
        }

        if (input.isDown(Keys.SPACE) && !isJumping && !isClimbing) {
            isJumping = true;
            System.out.println("Jumping");
            velocityY = JUMP_VELOCITY;
        }

        if (input.isDown(Keys.SPACE) && isClimbing) {
            isJumping = false;
            position = new Point(position.x, Math.max(position.y - LADDER_STEP_SIZE,0));
            System.out.println("Is climbing");
        }

        if (isJumping) {
            velocityY = Math.max(velocityY - GRAVITY, TERMINAL_VELOCITY);
            position = new Point(position.x, Math.max(position.y - velocityY,0));
        }
    }

    @Override
    public Rectangle[] getObjBounds() {
        Rectangle[] playerBounds = new Rectangle[1];
        if (!isHammerHeld) {
            playerBounds[0] =  new Rectangle(position.x, position.y, pictureRight.getWidth(), pictureRight.getHeight());
        } else {
            playerBounds[0] = new Rectangle(position.x, position.y + 10 , pictureRight.getWidth(), pictureRight.getHeight());
        }
        return playerBounds;

    }

    public void restartToStart() {
        position = objCoords[0];
        isJumping = false;
        isClimbing = false;
        isHammerHeld = false;
    }

}