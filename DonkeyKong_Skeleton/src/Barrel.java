import bagel.*;
import java.util.Properties;

public class Barrel {
    private final Image barrel = new Image("res/barrel.png");
    private int barrelCount;
    private int[][] barrelCoords;
    private String[] barrels = {"barrel.1", "barrel.2", "barrel.3", "barrel.4", "barrel.5"};

    public Barrel(Properties gameProps) {
        barrelCount = Integer.parseInt(gameProps.getProperty("barrel.count"));
        barrelCoords = new int[barrelCount][2];
        for (int i = 0; i < barrelCount; i++) {
            String[] parts = gameProps.getProperty(barrels[i]).split(",");
            barrelCoords[i][0] = Integer.parseInt(parts[0]);
            barrelCoords[i][1] = Integer.parseInt(parts[1]);
        }
    }

    public void renderBarrel() {
        for (int i = 0; i < barrelCount; i++) {
            barrel.draw(barrelCoords[i][0], barrelCoords[i][1]);
        }
    }

}
