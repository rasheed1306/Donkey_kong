import bagel.*;
import java.util.Properties;


public class Donkey {
    private final Image donkey = new Image("res/donkey_kong.png");
    private int[] donkeyStartCoords;

    public Donkey(Properties gameProps) {
        donkeyStartCoords = new int[2];
        donkeyStartCoords[0] = Integer.parseInt(gameProps.getProperty("donkey.start.x"));
        donkeyStartCoords[1] = Integer.parseInt(gameProps.getProperty("donkey.start.y"));

    }

    public void renderDonkey() {
        donkey.draw(donkeyStartCoords[0], donkeyStartCoords[1]);
    }
}
