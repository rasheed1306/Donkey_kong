import bagel.*;
import java.util.Properties;
import bagel.util.*;

public class Donkey extends GameScreenObject{
    public Donkey(Properties gameProps) {

        // Initialises donkey based on image and starting location
        super(new Image("res/donkey_kong.png"), initCoords(gameProps)); }

    // Initialises Donkey Kong starting coordinates from game properties
    public static Point[] initCoords(Properties gameProps) {
        Point[] coords = new Point[1];
        coords[0] = new Point(Double.parseDouble(gameProps.getProperty("donkey.start.x")), Double.parseDouble(gameProps.getProperty("donkey.start.y")));
        return coords;
    }

    @Override
    public void renderObj() {
        if (!renderFallingObj()) {
            super.renderObj();
        }
    }
}
