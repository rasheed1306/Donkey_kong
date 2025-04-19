import bagel.*;
import java.util.Properties;
import bagel.util.*;


public class Hammer extends GameScreenObject{

    public Hammer(Properties gameProps) {
        super(new Image("res/hammer.png"), initCoords(gameProps));
    }

    public static Point[] initCoords(Properties gameProps) {
        Point[] coords = new Point[1];
        coords[0] = new Point(Double.parseDouble(gameProps.getProperty("hammer.start.x")), Double.parseDouble(gameProps.getProperty("hammer.start.y")));
        return coords;
    }

    @Override
    public void renderObj(Input input) {
        if (!isHammerHeld) {
            super.renderObj(input);
        }
    }
}

//public class Hammer {
//    private final Image hammer = new Image("res/hammer.png");
//    private final Point hammerStartCoords;
//
//    protected boolean isHammerHeld = false;
//
//    public Hammer(Properties gameProps) {
//        hammerStartCoords = new Point(Double.parseDouble(gameProps.getProperty("hammer.start.x")), Double.parseDouble(gameProps.getProperty("hammer.start.y")));
//    }
//    public void renderHammer() {
//        if (!isHammerHeld) {
//            hammer.draw(hammerStartCoords.x, hammerStartCoords.y);
//        }
//    }
//
//    public Rectangle getHammerBounds() {
//        return new Rectangle(hammerStartCoords.x , hammerStartCoords.y, hammer.getWidth(), hammer.getHeight());
//
//    }
//
//}
