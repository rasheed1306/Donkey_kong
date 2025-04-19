import bagel.*;
import java.util.Properties;
import bagel.util.*;

/**
 * Represents platform object in game.
 */
public class Platform extends GameScreenObject{

    public Platform(Properties gameProps) {
        super(new Image("res/platform.png"), initCoords(gameProps));
    }

    /**
     * Initialises platform coordinates
     * @param gameProps Property object containing platform coordinates
     * @return Platform coordinates
     */
    public static Point[] initCoords(Properties gameProps) {
        final int platformCount = gameProps.getProperty("platforms").split(";").length;
        Point[] coords = new Point[platformCount];

        for (int i = 0; i < platformCount; i++) {
            String[] parts = gameProps.getProperty("platforms").split(";");
            coords[i] = new Point(Double.parseDouble(parts[i].split(",")[0].trim()), Double.parseDouble(parts[i].split(",")[1].trim()));
        }
        return coords;
    }

    /**
     * Custom platform boundaries with point being top left oriented
     * @return Array of Rectangles objects
     */
    @Override
    public Rectangle[] getObjBounds() {
        for (int i = 0; i < objCount; i++) {
            objBounds[i] = new Rectangle(objCoords[i].x - image.getWidth() / 2, objCoords[i].y, image.getWidth(), image.getHeight());
        }
        return objBounds;
    }
}
