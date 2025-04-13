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
    private final Image background = new Image("res/background.png");

    private boolean lostScreenShown = false;  // Track if lost screen was displayed
    private boolean waitingForSpace = false;  // Track if waiting for player input


    public EndGamePage(Properties gameProps, Properties messageProps) {
        won = messageProps.getProperty("gameEnd.won");
        lost = messageProps.getProperty("gameEnd.lost");
        _continue = messageProps.getProperty("gameEnd.continue");
        score = messageProps.getProperty("gameEnd.score");

        statusFont = new Font(gameProps.getProperty("font"), Integer.parseInt(gameProps.getProperty("gameEnd.status.fontSize")));
        scoresFont = new Font(gameProps.getProperty("font"), Integer.parseInt(gameProps.getProperty("gameEnd.scores.fontSize")));

        statusPoint = new Point( ((Window.getWidth() - statusFont.getWidth(lost)) / 2), Double.parseDouble(gameProps.getProperty("gameEnd.status.y")));
        scoresPoint = new Point( ((Window.getWidth() - scoresFont.getWidth(score))/ 2), Double.parseDouble(gameProps.getProperty("gameEnd.scores.y")));
    }

    public boolean renderLostGame(int score, Input input) {
        background.draw(Window.getWidth() / 2., Window.getHeight() / 2.);
        statusFont.drawString(lost, statusPoint.x, statusPoint.y);
        scoresFont.drawString((this.score + " " + score), scoresPoint.x, scoresPoint.y);
        statusFont.drawString(_continue, ((Window.getWidth() - statusFont.getWidth(_continue))/ 2), Window.getHeight() - 100);

        if (input.isDown(Keys.SPACE)) {
            return false;
        }
        return true;
    }
    public boolean renderWonGame(int score, Input input) {
        background.draw(Window.getWidth() / 2., Window.getHeight() / 2.);
        statusFont.drawString(won, statusPoint.x, statusPoint.y);
        scoresFont.drawString((this.score + " " + score), scoresPoint.x, scoresPoint.y);
        statusFont.drawString(_continue, ((Window.getWidth() - statusFont.getWidth(_continue))/ 2), Window.getHeight() - 100);

        if (input.isDown(Keys.SPACE)) {
            return false;
        }
        return true;
    }
}
