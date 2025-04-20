import bagel.*;
import bagel.util.*;

public abstract class GameScreenObject {
    protected final Image image;
    protected final Point[] objCoords;
    protected final int objCount;
    protected Rectangle[] objBounds;

    protected static boolean isHammerHeld;

    public GameScreenObject(Image image, Point[] objCoords) {
        this.image = image;
        this.objCoords = objCoords;
        this.objCount = objCoords.length;
        this.objBounds = new Rectangle[objCount];
        isHammerHeld = false;

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

    public Rectangle[] getObjBounds() {
        for (int i = 0; i < objCount; i++) {
            objBounds[i] = new Rectangle(objCoords[i].x, objCoords[i].y, image.getWidth(), image.getHeight());
        }
        return objBounds;
    }


}