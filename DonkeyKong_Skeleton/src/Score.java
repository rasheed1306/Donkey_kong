import bagel.*;
import bagel.util.*;
import java.util.Properties;

/**
 * Handles the display of scoring in the game
 */
public class Score {
    private final Point score;
    private final Font font;

    public Score(Properties gameProps) {
        // Position and location of where score will be displayed
        int fontSize = Integer.parseInt(gameProps.getProperty("gamePlay.score.fontSize"));
        this.font = new Font(gameProps.getProperty("font"), fontSize);
        score = new Point(Double.parseDouble(gameProps.getProperty("gamePlay.score.x")), Double.parseDouble(gameProps.getProperty("gamePlay.score.y")));

    }

    /**
     * Renders current score at the desired position
     * @param scorePoints Integer representation of score
     */
    public void getScore(int scorePoints) {
        font.drawString(("Score " + scorePoints),score.x,score.y);
    }
}
