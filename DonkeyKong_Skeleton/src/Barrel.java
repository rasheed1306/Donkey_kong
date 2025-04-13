import bagel.*;
import java.util.Properties;
import bagel.util.*;

public class Barrel {
    private final Image barrel = new Image("res/barrel.png");
    private int barrelCount;
    private Point[] barrelCoords;
    private Rectangle[] barrelBounds;
    private String[] barrels = {"barrel.1", "barrel.2", "barrel.3", "barrel.4", "barrel.5"};

    public Barrel(Properties gameProps) {
        barrelCount = Integer.parseInt(gameProps.getProperty("barrel.count"));
        barrelCoords = new Point[barrelCount];
        barrelBounds = new Rectangle[barrelCount];
        for (int i = 0; i < barrelCount; i++) {
            String[] parts = gameProps.getProperty(barrels[i]).split(",");
            barrelCoords[i] = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            barrelBounds[i] = new Rectangle(barrelCoords[i].x - barrel.getWidth() / 2, barrelCoords[i].y - barrel.getHeight() / 2, barrel.getWidth(), barrel.getHeight());
        }
    }

    public void renderBarrel() {
        for (int i = 0; i < barrelCount; i++) {
            barrel.draw(barrelCoords[i].x, barrelCoords[i].y);
        }
    }

    public Point[] getBarrelPositions() {
        return barrelCoords;
    }

    public Rectangle[] getBarrelBounds() {
        return barrelBounds;
    }
}
