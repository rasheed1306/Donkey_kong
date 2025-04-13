import bagel.*;
import java.util.Properties;
import bagel.util.*;


public class Hammer {
    private final Image hammer = new Image("res/hammer.png");
    private Point hammerStartCoords;

    protected boolean isHammerHeld = false;

    public Hammer(Properties gameProps) {
        hammerStartCoords = new Point(Double.parseDouble(gameProps.getProperty("hammer.start.x")), Double.parseDouble(gameProps.getProperty("hammer.start.y")));
    }
    public void renderHammer() {
        if (!isHammerHeld) {
            hammer.draw(hammerStartCoords.x, hammerStartCoords.y);
        }
    }

    public Rectangle getHammerBounds() {
//        return new Rectangle(hammerStartCoords.x - hammer.getWidth() / 2, hammerStartCoords.y - hammer.getHeight() / 2, hammer.getWidth(), hammer.getHeight());
        return new Rectangle(hammerStartCoords.x , hammerStartCoords.y, hammer.getWidth(), hammer.getHeight());

    }

}
