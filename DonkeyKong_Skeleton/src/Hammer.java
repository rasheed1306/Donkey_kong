import bagel.*;
import java.util.Properties;


public class Hammer {
    private final Image hammer = new Image("res/hammer.png");
    private int[] hammerStartCoords;

    public Hammer(Properties gameProps) {
        hammerStartCoords = new int[2];
        hammerStartCoords[0] = Integer.parseInt(gameProps.getProperty("hammer.start.x"));
        hammerStartCoords[1] = Integer.parseInt(gameProps.getProperty("hammer.start.y"));
    }
    public void renderHammer() {
        hammer.draw(hammerStartCoords[0], hammerStartCoords[1]);
    }
}
