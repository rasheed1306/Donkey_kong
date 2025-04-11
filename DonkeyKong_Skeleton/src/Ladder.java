import bagel.*;
import java.util.Properties;

public class Ladder {
    private final Image ladder = new Image("res/ladder.png");

    private int ladderCount;
    private String[] ladders = {"ladder.1", "ladder.2", "ladder.3", "ladder.4", "ladder.5"};
    private int[][] ladderCoords;

    public Ladder(Properties gameProps) {
        this.ladderCount = Integer.parseInt(gameProps.getProperty("ladder.count"));
        ladderCoords = new int[ladderCount][2];
        for (int i = 0; i < ladderCount; i++) {
            String[] parts = gameProps.getProperty(ladders[i]).split(",");
            ladderCoords[i][0] = Integer.parseInt(parts[0]);
            ladderCoords[i][1] = Integer.parseInt(parts[1]);
        }
    }

        public void renderLadder() {
            for (int i = 0; i < ladderCount; i++) {
                ladder.draw(ladderCoords[i][0], ladderCoords[i][1]);
            }
        }
    }

