import bagel.*;
import java.util.Properties;
import bagel.util.*;

public class Barrel {
    private final Image barrel = new Image("res/barrel.png");
    protected int barrelCount;
    private Point[] barrelCoords;
    private Rectangle[] barrelBounds;
    private String[] barrels = {"barrel.1", "barrel.2", "barrel.3", "barrel.4", "barrel.5"};

    protected boolean[] barrelDestroyed;

    public Barrel(Properties gameProps) {
        barrelCount = Integer.parseInt(gameProps.getProperty("barrel.count"));
        barrelCoords = new Point[barrelCount];
        barrelBounds = new Rectangle[barrelCount];
        barrelDestroyed = new boolean[barrelCount];
        for (int i = 0; i < barrelCount; i++) {
            String[] parts = gameProps.getProperty(barrels[i]).split(",");
            barrelCoords[i] = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            barrelBounds[i] = new Rectangle(barrelCoords[i].x, barrelCoords[i].y, barrel.getWidth(), barrel.getHeight());
            barrelDestroyed[i] = false;
        }
    }

    public void renderBarrel() {
        for (int i = 0; i < barrelCount; i++) {
            if (!barrelDestroyed[i]) {
                barrel.draw(barrelCoords[i].x, barrelCoords[i].y);
            }
        }
    }

    public Point[] getBarrelPositions() {
        return barrelCoords;
    }

    public Rectangle[] getBarrelBounds() {
        return barrelBounds;
    }
}
