import bagel.*;
import java.util.Properties;
import bagel.util.*;


public class Donkey {
    private final Image donkey = new Image("res/donkey_kong.png");
    private Point donkeyStartCoords;

    public Donkey(Properties gameProps) {
        donkeyStartCoords = new Point(Double.parseDouble(gameProps.getProperty("donkey.start.x")), Double.parseDouble(gameProps.getProperty("donkey.start.y")));
    }

    public void renderDonkey() {
        donkey.draw(donkeyStartCoords.x, donkeyStartCoords.y);
    }

    public Rectangle getDonkeyBounds() {
        return new Rectangle(donkeyStartCoords.x, donkeyStartCoords.y, donkey.getWidth(), donkey.getHeight());
    }
}
