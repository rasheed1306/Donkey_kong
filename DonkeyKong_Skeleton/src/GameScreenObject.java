import bagel.*;
import bagel.util.*;
import java.util.Properties;

public abstract class GameScreenObject {
    protected final Image image;
    protected final Point[] objCoords;
    protected final int objCount;
    protected Rectangle[] objBounds;

    protected boolean isHammerHeld;

    public static Point[] initCoords(Properties gameProps) {
        return new Point[2];
    }


    public GameScreenObject(Image image, Point[] objCoords) {
        this.image = image;
        this.objCoords = objCoords;
        this.objCount = objCoords.length;
        this.objBounds = new Rectangle[objCount];
        this.isHammerHeld = false;

    }



    public boolean isHammerHeld() {
        return isHammerHeld;
    }

    public void setHammerHeld(boolean held) {this.isHammerHeld = held; }

    public void renderObj(Input input) {
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
