import bagel.*;
import bagel.util.*;
import java.util.Properties;

public class Score {
    private final int fontSize;
    private final Point score;
    private final Font font;

    public Score(Properties gameProps) {
        this.fontSize = Integer.parseInt(gameProps.getProperty("gamePlay.score.fontSize"));
        this.font = new Font(gameProps.getProperty("font"), fontSize);
        score = new Point(Double.parseDouble(gameProps.getProperty("gamePlay.score.x")), Double.parseDouble(gameProps.getProperty("gamePlay.score.y")));

    }
    public void getScore(int scorePoints) {
        font.drawString(("Score " + scorePoints),score.x,score.y);

    }


}
