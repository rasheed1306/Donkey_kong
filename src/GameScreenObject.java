import bagel.*;
import bagel.util.*;

public abstract class GameScreenObject {
    protected final Image image;
    protected final Point[] objCoords;
    protected final int objCount;
    protected Rectangle[] objBounds;

    protected static boolean isHammerHeld;
    private Point[] position;
    private static double fallingVelocity = 0;
    private static final double GRAVITY = 0.2;
    private static double fallingDisplacement = 0;
    private final static double FALLING_DISP = 75;

    public GameScreenObject(Image image, Point[] objCoords) {
        this.image = image;
        this.objCoords = objCoords;
        this.objCount = objCoords.length;
        this.objBounds = new Rectangle[objCount];

        isHammerHeld = false;

        position = new Point[objCount];
        for (int i = 0; i < objCount; i++) {
            position[i] = new Point(objCoords[i].x, objCoords[i].y - FALLING_DISP);
        }
    }

    public void renderObj(Input input) {
        for (int i = 0; i < objCount; i++) {
            image.draw(objCoords[i].x, objCoords[i].y);
        }
    }

    public void renderObj() {
        for (int i = 0; i < objCount; i++) {
            image.draw(objCoords[i].x, objCoords[i].y);
        }
    }

    public boolean renderFallingObj() {
        fallingVelocity = fallingVelocity - GRAVITY;

        fallingDisplacement -= fallingVelocity;
        if (fallingDisplacement >= FALLING_DISP) {
            return false;
        }

        for (int i = 0; i < objCount; i++) {
            position[i] = new Point(position[i].x, position[i].y - fallingVelocity);
            image.draw(position[i].x, position[i].y);
        }
        return true;
    }

    public static void resetFallingObj() {
        fallingDisplacement = 0;
        fallingVelocity = 0;
    }


    public Rectangle[] getObjBounds() {
        for (int i = 0; i < objCount; i++) {
            objBounds[i] = new Rectangle(objCoords[i].x, objCoords[i].y, image.getWidth(), image.getHeight());
        }
        return objBounds;
    }


}