import bagel.*;
import java.util.Properties;
import bagel.util.Point;


public class EndGamePage {
    private final String won;
    private final String score;
    private final String _continue;
    private final String lost;
    private final Font statusFont;
    private final Font scoresFont;
    private final Point statusPoint;
    private final Point scoresPoint;


    public EndGamePage(Properties gameProps, Properties messageProps) {
        won = messageProps.getProperty("gameEnd.won");
        lost = messageProps.getProperty("gameEnd.lost");
        _continue = messageProps.getProperty("gameEnd.continue");
        score = messageProps.getProperty("gameEnd.score");

        statusFont = new Font(gameProps.getProperty("font"), Integer.parseInt(gameProps.getProperty("gameEnd.status.fontSize")));
        scoresFont = new Font(gameProps.getProperty("font"), Integer.parseInt(gameProps.getProperty("gameEnd.scores.fontSize")));

        statusPoint = new Point((Window.getHeight() / 2), Double.parseDouble(gameProps.getProperty("gameEnd.status.y")));
        scoresPoint = new Point((Window.getHeight() / 2), Double.parseDouble(gameProps.getProperty("gameEnd.scores.y")));

    }

    public void renderLostGame(int score) {
        statusFont.drawString(lost, statusPoint.x, statusPoint.y);
        scoresFont.drawString((this.score + " " + score), scoresPoint.x, scoresPoint.y);
        statusFont.drawString(_continue, statusPoint.x, statusPoint.y + 100.);
    }
    public void renderWonGame(int score) {
        statusFont.drawString(won, statusPoint.x, statusPoint.y);
        scoresFont.drawString((this.score + " " + score), scoresPoint.x, scoresPoint.y);


    }


}
