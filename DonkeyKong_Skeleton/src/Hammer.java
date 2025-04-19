import bagel.*;
import java.util.Properties;
import bagel.util.*;

// Represents hammer object with can be picked up by player in game
public class Hammer extends GameScreenObject{

    public Hammer(Properties gameProps) {
        // Initialise with hammer image and starting coordinates
        super(new Image("res/hammer.png"), initCoords(gameProps));
    }

    // Initialise hammer starting coordinates from game properties
    public static Point[] initCoords(Properties gameProps) {
        Point[] coords = new Point[1];
        coords[0] = new Point(Double.parseDouble(gameProps.getProperty("hammer.start.x")), Double.parseDouble(gameProps.getProperty("hammer.start.y")));
        return coords;
    }

    // Renders hammer only if not picked up by object
    @Override
    public void renderObj(Input input) {
        if (!isHammerHeld) {
            super.renderObj(input);
        }
    }
}
