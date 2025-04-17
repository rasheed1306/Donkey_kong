import bagel.*;
import java.util.Properties;
import bagel.util.*;

public class Barrel {
    private final Image barrel = new Image("res/barrel.png");
    protected int barrelCount;
    private final Point[] barrelCoords;
    private final Rectangle[] barrelBounds;

    protected boolean[] barrelDestroyed;
    protected boolean[] isBarrelScoreAdded;
    double[] jumpingBarrelHeights;
    Rectangle[] jumpingBarrelBounds;


    public Barrel(Properties gameProps) {
        barrelCount = Integer.parseInt(gameProps.getProperty("barrel.count"));
        barrelCoords = new Point[barrelCount];
        barrelBounds = new Rectangle[barrelCount];
        isBarrelScoreAdded = new boolean[barrelCount];
        barrelDestroyed = new boolean[barrelCount];
        for (int i = 0; i < barrelCount; i++) {
            String[] barrels = {"barrel.1", "barrel.2", "barrel.3", "barrel.4", "barrel.5"};
            String[] parts = gameProps.getProperty(barrels[i]).split(",");
            barrelCoords[i] = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            barrelBounds[i] = new Rectangle(barrelCoords[i].x, barrelCoords[i].y, barrel.getWidth(), barrel.getHeight());
            barrelDestroyed[i] = false;
            isBarrelScoreAdded[i] = false;
        }
        System.out.println(barrel.getWidth() + " Rasheed " + barrel.getHeight());

    }

    public void renderBarrel() {
        for (int i = 0; i < barrelCount; i++) {
            if (!barrelDestroyed[i]) {
                barrel.draw(barrelCoords[i].x, barrelCoords[i].y);
            }
        }
    }

    public Rectangle[] getBarrelBounds() {
        return barrelBounds;
    }

    public Rectangle[] getJumpingBarrelBounds() {
        jumpingBarrelBounds = new Rectangle[barrelCount];
        jumpingBarrelHeights = new double[barrelCount];

        for (int i = 0; i < barrelCount; i++) {
            jumpingBarrelHeights[i] = barrelBounds[i].top() - 32.5;
            jumpingBarrelBounds[i] = new Rectangle(barrelCoords[i].x, jumpingBarrelHeights[i], barrel.getWidth(), 32.5);
        }
        return jumpingBarrelBounds;
    }
}
