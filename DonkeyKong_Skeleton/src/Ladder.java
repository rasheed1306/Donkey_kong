import bagel.*;
import java.util.Properties;
import bagel.util.*;

public class Ladder {
    private final Image ladder = new Image("res/ladder.png");

    private int ladderCount;
    private String[] ladders = {"ladder.1", "ladder.2", "ladder.3", "ladder.4", "ladder.5"};
    private Point[] ladderCoords;
    private Rectangle[] ladderBounds;

    public Ladder(Properties gameProps) {
        this.ladderCount = Integer.parseInt(gameProps.getProperty("ladder.count"));
        ladderCoords = new Point[ladderCount];
        ladderBounds = new Rectangle[ladderCount];

        for (int i = 0; i < ladderCount; i++) {
            String[] parts = gameProps.getProperty(ladders[i]).split(",");
            ladderCoords[i] = new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
            ladderBounds[i] = new Rectangle(ladderCoords[i].x + 10, ladderCoords[i].y, ladder.getWidth() - 15, ladder.getHeight() - 30);
        }
    }


        public void renderLadder() {
            for (int i = 0; i < ladderCount; i++) {
                ladder.draw(ladderCoords[i].x, ladderCoords[i].y);
            }
        }

        public Rectangle[] getLadderBounds() {
            return ladderBounds;
        }
    }

