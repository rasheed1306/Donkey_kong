import bagel.*;
import java.util.Properties;
import bagel.util.*;

public class Ladder extends GameScreenObject{

    public Ladder(Properties gameProps) {
        super(new Image("res/ladder.png"), initCoords(gameProps)); }

    public static Point[] initCoords(Properties gameProps) {
        int ladderCount = Integer.parseInt(gameProps.getProperty("ladder.count"));
        Point[] coords = new Point[ladderCount];
        String[] ladders = {"ladder.1", "ladder.2", "ladder.3", "ladder.4", "ladder.5"};

        for (int i = 0; i < ladderCount; i++) {
            String[] parts = gameProps.getProperty(ladders[i]).split(",");
            coords[i] = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
        }
        return coords;
    }

    @Override
    public Rectangle[] getObjBounds() {
        for (int i = 0; i < objCount; i++) {
            objBounds[i] = new Rectangle(objCoords[i].x + 10, objCoords[i].y, image.getWidth() - 15, image.getHeight() - 30);
        }
        return objBounds;
//        return super.getObjBounds();
    }



}
//
//this.ladderCount = Integer.parseInt(gameProps.getProperty("ladder.count"));
//ladderCoords = new Point[ladderCount];
//ladderBounds = new Rectangle[ladderCount];
//
//        for (int i = 0; i < ladderCount; i++) {
//String[] ladders = {"ladder.1", "ladder.2", "ladder.3", "ladder.4", "ladder.5"};
//String[] parts = gameProps.getProperty(ladders[i]).split(",");
//ladderCoords[i] = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
//ladderBounds[i] = new Rectangle(ladderCoords[i].x + 10, ladderCoords[i].y, ladder.getWidth() - 15, ladder.getHeight() - 30);
//        }
//        }
//
//
//public void renderLadder() {
//    for (int i = 0; i < ladderCount; i++) {
//        ladder.draw(ladderCoords[i].x, ladderCoords[i].y);
//    }
//}
//
//public Rectangle[] getLadderBounds() {
//    return ladderBounds;
//}
