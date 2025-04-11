import bagel.*;
import java.util.Properties;

public class Platform {
    public final Image platform = new Image("res/platform.png");
    private int[][] platformCoords;
    private String[] platformStrings;
    private int platformCount;
    private String[] coords;

    public Platform(Properties gameProps) {
        this.platformCount = gameProps.getProperty("platforms").split(";").length;
        platformStrings = gameProps.getProperty("platforms").split(";");
        this.platformCoords = new int[platformCount+1][2];

        for (int i = 0; i < platformCount; i++) {
            coords = platformStrings[i].split(",");
            this.platformCoords[i][0] = Integer.parseInt(coords[0].trim());
            this.platformCoords[i][1] = Integer.parseInt(coords[1].trim());
        }
    }

    public void renderPlatform() {
        for (int i = 0; i < platformCount; i++) {
            platform.draw(platformCoords[i][0], platformCoords[i][1]);
        }
    }
}