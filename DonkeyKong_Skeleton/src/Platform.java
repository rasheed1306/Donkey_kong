import bagel.*;
import java.util.Properties;
import bagel.util.*;

public class Platform extends GameScreenObject{

    public Platform(Properties gameProps) {
        super(new Image("res/platform.png"), initCoords(gameProps));
    }

    public static Point[] initCoords(Properties gameProps) {
        final int platformCount = gameProps.getProperty("platforms").split(";").length;
        Point[] coords = new Point[platformCount];

        for (int i = 0; i < platformCount; i++) {
            String[] parts = gameProps.getProperty("platforms").split(";");
            coords[i] = new Point(Double.parseDouble(parts[i].split(",")[0].trim()), Double.parseDouble(parts[i].split(",")[1].trim()));
        }
        return coords;
    }

    @Override
    public Rectangle[] getObjBounds() {
        for (int i = 0; i < objCount; i++) {
            objBounds[i] = new Rectangle(objCoords[i].x - image.getWidth() / 2, objCoords[i].y, image.getWidth(), image.getHeight());
        }
        return objBounds;
    }
}

//public class Platform {
//    private final Image platform = new Image("res/platform.png");
//    private final Point[] platformCoords;
//    private int platformCount;
//    private final Rectangle[] platformBounds;
//
//    public Platform(Properties gameProps) {
//        this.platformCount = gameProps.getProperty("platforms").split(";").length;
//        String[] platformStrings = gameProps.getProperty("platforms").split(";");
//        this.platformCoords = new Point[platformCount];
//        this.platformBounds = new Rectangle[platformCount];
//
//        for (int i = 0; i < platformCount; i++) {
//            String[] coords = platformStrings[i].split(",");
//            this.platformCoords[i] = new Point(Double.parseDouble(coords[0].trim()), Double.parseDouble(coords[1].trim()));
//        }
//    }
//    public void renderPlatform() {
//        for (int i = 0; i < platformCount; i++) {
//            platform.draw(platformCoords[i].x, platformCoords[i].y);
//        }
//    }
//
//    public Rectangle[] getPlatformBounds() {
//        for (int i = 0; i < platformCount; i++) {
//            platformBounds[i] = new Rectangle(platformCoords[i].x - platform.getWidth() / 2, platformCoords[i].y, platform.getWidth(), platform.getHeight());
//        }
//        return platformBounds;
//    }
//
//}