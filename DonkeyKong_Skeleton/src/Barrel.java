import bagel.*;
import java.util.Properties;
import bagel.util.*;

public class Barrel extends GameScreenObject{
    protected boolean[] isBarrelDestroyed;
    protected boolean[] isBarrelScoreAdded;
    static final double BARREL_HEIGHT = 32.5;

    public Barrel(Properties gameProps) {
        super(new Image("res/barrel.png"), initCoords(gameProps));
        initBarrelStatus();
    }
    public static Point[] initCoords(Properties gameProps) {
        int barrelCount = Integer.parseInt(gameProps.getProperty("barrel.count"));
        Point[] coords = new Point[barrelCount];


        String[] barrels = {"barrel.1", "barrel.2", "barrel.3", "barrel.4", "barrel.5"};
        for (int i = 0; i < barrelCount; i++) {
            String[] parts = gameProps.getProperty(barrels[i]).split(",");
            coords[i] = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
        }

        return coords;
    }

    public void initBarrelStatus() {
        isBarrelDestroyed = new boolean[objCount];
        isBarrelScoreAdded = new boolean[objCount];

        for (int i = 0; i < objCount; i++) {
            isBarrelDestroyed[i] = false;
            isBarrelScoreAdded[i] = false;
        }
    }

    public void updateBarrelStatus(boolean isDestroyed, int index) {
        isBarrelDestroyed[index] = isDestroyed;
    }

    @Override
    public void renderObj(Input input) {
        for (int i = 0; i < objCount; i++) {
            if (!isBarrelDestroyed[i]) {
                image.draw(objCoords[i].x, objCoords[i].y);

            }
        }
    }

    public Rectangle[] getJumpingBarrelBounds() {
        Rectangle[] jumpingBarrelBounds = new Rectangle[objCount];
        double[] jumpingBarrelHeights = new double[objCount];

        for (int i = 0; i < objCount; i++) {
            jumpingBarrelHeights[i] = objCoords[i].y - BARREL_HEIGHT;
            jumpingBarrelBounds[i] = new Rectangle(objCoords[i].x, jumpingBarrelHeights[i], image.getWidth(), BARREL_HEIGHT);
        }
        return jumpingBarrelBounds;
    }


}

//public class Barrel {
//    private final Image barrel = new Image("res/barrel.png");
//    protected int barrelCount;
//    private final Point[] barrelCoords;
//    private final Rectangle[] barrelBounds;
//
//    protected boolean[] isbarrelDestroyed;
//    protected boolean[] isBarrelScoreAdded;
//    double[] jumpingBarrelHeights;
//    Rectangle[] jumpingBarrelBounds;
//
//
//    public Barrel(Properties gameProps) {
//        barrelCount = Integer.parseInt(gameProps.getProperty("barrel.count"));
//        barrelCoords = new Point[barrelCount];
//        barrelBounds = new Rectangle[barrelCount];
//        isBarrelScoreAdded = new boolean[barrelCount];
//        isbarrelDestroyed = new boolean[barrelCount];
//        for (int i = 0; i < barrelCount; i++) {
//            String[] barrels = {"barrel.1", "barrel.2", "barrel.3", "barrel.4", "barrel.5"};
//            String[] parts = gameProps.getProperty(barrels[i]).split(",");
//            barrelCoords[i] = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
//            barrelBounds[i] = new Rectangle(barrelCoords[i].x, barrelCoords[i].y, barrel.getWidth(), barrel.getHeight());
//            isbarrelDestroyed[i] = false;
//            isBarrelScoreAdded[i] = false;
//        }
//        System.out.println(barrel.getWidth() + " Rasheed " + barrel.getHeight());
//
//    }
//
//    public void renderBarrel() {
//        for (int i = 0; i < barrelCount; i++) {
//            if (!isbarrelDestroyed[i]) {
//                barrel.draw(barrelCoords[i].x, barrelCoords[i].y);
//            }
//        }
//    }
//
//    public Rectangle[] getBarrelBounds() {
//        return barrelBounds;
//    }
//
//    public Rectangle[] getJumpingBarrelBounds() {
//        jumpingBarrelBounds = new Rectangle[barrelCount];
//        jumpingBarrelHeights = new double[barrelCount];
//
//        for (int i = 0; i < barrelCount; i++) {
//            jumpingBarrelHeights[i] = barrelBounds[i].top() - 32.5;
//            jumpingBarrelBounds[i] = new Rectangle(barrelCoords[i].x, jumpingBarrelHeights[i], barrel.getWidth(), 32.5);
//        }
//        return jumpingBarrelBounds;
//    }
//}
