import bagel.*;
import java.util.Properties;
import bagel.util.*;

public class Barrel extends GameScreenObject {

    // Arrays which track the status of each barrel
    protected static boolean[] isBarrelDestroyed;
    protected static boolean[] isBarrelScoreAdded;

    // Constant Height of Barrel
    static final double BARREL_HEIGHT = 32.5;

    private Point[] position;
    private double fallingVelocity = 0;
    private static final double GRAVITY = 0.2;
    private static final double TERMINAL_VELOCITY = -10;
    private static double fallingDisplacement;

    public Barrel(Properties gameProps) {
        super(new Image("res/barrel.png"), initCoords(gameProps));
        initBarrelStatus();

        position = new Point[objCount];
        for (int i = 0; i < objCount; i++) {
            position[i] = new Point(objCoords[i].x, objCoords[i].y - 75);
        }
    }

    // Initialises barrel coordinates from Game Properties
    public static Point[] initCoords(Properties gameProps) {
        int barrelCount = Integer.parseInt(gameProps.getProperty("barrel.count"));
        Point[] coords = new Point[barrelCount];

        // Parses and return coordinates for each barrel
        String[] barrels = {"barrel.1", "barrel.2", "barrel.3", "barrel.4", "barrel.5"};
        for (int i = 0; i < barrelCount; i++) {
            String[] parts = gameProps.getProperty(barrels[i]).split(",");
            coords[i] = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
        }

        return coords;
    }

    // Initialises Barrel status of it not being destroyed and score not added initially
    public void initBarrelStatus() {
        isBarrelDestroyed = new boolean[objCount];
        isBarrelScoreAdded = new boolean[objCount];

        for (int i = 0; i < objCount; i++) {
            isBarrelDestroyed[i] = false;
            isBarrelScoreAdded[i] = false;
        }
    }

    // Updates the status of a specific barrel
    public void updateBarrelStatus(boolean isDestroyed, int index) {
        isBarrelDestroyed[index] = isDestroyed;
    }


//     Renders all non-destroyed barrels
    @Override
    public void renderObj() {
        if (!renderFallingObj()) {
            for (int i = 0; i < objCount; i++) {
                if (!isBarrelDestroyed[i]) {
                    image.draw(objCoords[i].x, objCoords[i].y);

                }
            }
        }
    }


    // Gets collision bounds for barrels (used for jumping detection)
    public Rectangle[] getJumpingBarrelBounds() {
        Rectangle[] jumpingBarrelBounds = new Rectangle[objCount];
        double[] jumpingBarrelHeights = new double[objCount];

        for (int i = 0; i < objCount; i++) {
            jumpingBarrelHeights[i] = objCoords[i].y - BARREL_HEIGHT;
            jumpingBarrelBounds[i] = new Rectangle(objCoords[i].x, jumpingBarrelHeights[i], image.getWidth(), BARREL_HEIGHT);
        }
        return jumpingBarrelBounds;
    }
}