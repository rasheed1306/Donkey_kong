import bagel.*;
import java.util.Properties;
import bagel.util.*;

public class Platform {
    private final Image platform = new Image("res/platform.png");
    private Point[] platformCoords;
    private String[] platformStrings;
    private int platformCount;
    private String[] coords;
    private Rectangle[] platformBounds;

    public Platform(Properties gameProps) {
        this.platformCount = gameProps.getProperty("platforms").split(";").length;
        platformStrings = gameProps.getProperty("platforms").split(";");
        this.platformCoords = new Point[platformCount];
        this.platformBounds = new Rectangle[platformCount];

        for (int i = 0; i < platformCount; i++) {
            coords = platformStrings[i].split(",");
            this.platformCoords[i] = new Point(Double.parseDouble(coords[0].trim()), Double.parseDouble(coords[1].trim()));
        }
    }
    public void renderPlatform() {
        for (int i = 0; i < platformCount; i++) {
            platform.draw(platformCoords[i].x, platformCoords[i].y);
        }
    }

    public Rectangle[] getPlatformBounds() {
        for (int i = 0; i < platformCount; i++) {
            platformBounds[i] = new Rectangle(platformCoords[i].x - platform.getWidth() / 2, platformCoords[i].y, platform.getWidth(), platform.getHeight());
        }
        return platformBounds;
    }

}