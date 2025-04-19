import bagel.*;
import java.util.Properties;
import bagel.util.*;

// Represents ladder object in game. Includes custom collision bounds for precise interaction detection.
public class Ladder extends GameScreenObject{

    public Ladder(Properties gameProps) {
        super(new Image("res/ladder.png"), initCoords(gameProps)); }

    // initialises coordinates of ladder from game properties
    public static Point[] initCoords(Properties gameProps) {
        int ladderCount = Integer.parseInt(gameProps.getProperty("ladder.count"));
        Point[] coords = new Point[ladderCount];
        String[] ladders = {"ladder.1", "ladder.2", "ladder.3", "ladder.4", "ladder.5"};

        for (int i = 0; i < ladderCount; i++) {
            String[] parts = gameProps.getProperty(ladders[i]).split(",");
            coords[i] = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
        }
        return coords;
    }

    // returns custom ladder boundaries. The ladder visual representation is slightly smaller than the image boundaries
    @Override
    public Rectangle[] getObjBounds() {
        for (int i = 0; i < objCount; i++) {
            objBounds[i] = new Rectangle(objCoords[i].x + 10, objCoords[i].y, image.getWidth() - 15, image.getHeight() - 30);
        }
        return objBounds;
    }



}

